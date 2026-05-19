package systementor.pensionatapp.pensionat.booking.service;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import systementor.pensionatapp.pensionat.booking.BookingStatus;
import systementor.pensionatapp.pensionat.booking.model.Booking;
import systementor.pensionatapp.pensionat.booking.model.CreateBookingRequest;
import systementor.pensionatapp.pensionat.booking.repository.BookingRepository;
import systementor.pensionatapp.pensionat.customer.model.Customer;
import systementor.pensionatapp.pensionat.customer.repository.CustomerRepository;
import systementor.pensionatapp.pensionat.error.BadRequestException;
import systementor.pensionatapp.pensionat.error.NotFoundException;
import systementor.pensionatapp.pensionat.room.model.Room;
import systementor.pensionatapp.pensionat.room.repository.RoomRepository;

import java.time.LocalDate;
import java.util.List;

import static systementor.pensionatapp.pensionat.room.utils.Validations.validateDateRange;

@Service
public class BookingService {




    private final BookingRepository bookingRepository;
    private final CustomerRepository customerRepository;
    private final RoomRepository roomRepository;

    public BookingService(BookingRepository bookingRepository, CustomerRepository customerRepository, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.customerRepository = customerRepository;
        this.roomRepository = roomRepository;
    }


    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }



    @Transactional
    public Booking createBooking(CreateBookingRequest request) {

        Customer customer = customerRepository.findById(request.customerId())
                .orElseThrow(() -> new NotFoundException("Kunden finns inte"));


        System.out.println("THIS IS THE ROOM ID" + request.roomId());
        Room room = roomRepository.findById(request.roomId())
                .orElseThrow(() -> new NotFoundException("Rummet finns inte"));

        validateDateRange(request.startDate(),request.endDate());
        validateRoomIsAvailable(request.roomId(),request.startDate(),request.endDate(), null);
        Booking booking = new Booking(customer,room,request.startDate(),request.endDate(),BookingStatus.ACTIVE);
        return bookingRepository.save(booking);
    }


    private void validateRoomIsAvailable(Long roomId, LocalDate startDate, LocalDate endDate, Long bookingIdToIgnore) {
        List<Booking> bookings = bookingRepository.findByRoomAndStatus(roomId, BookingStatus.ACTIVE);
        for (Booking existingBooking: bookings) {
            if(bookingIdToIgnore != null && bookingIdToIgnore.equals(existingBooking.getId()))  {
                continue;
            }

            boolean overlap = startDate.isBefore(existingBooking.getEndDatum()) && endDate.isAfter(existingBooking.getEndDatum());
            if (overlap) {
                throw new BadRequestException("Rummet är redan bokat under valt datum");
            }
        }

    }

}
