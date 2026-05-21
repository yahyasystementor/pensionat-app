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

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

import static systementor.pensionatapp.pensionat.room.utils.Validations.validateDateRange;

@Service
public class BookingService {


    Logger logger = Logger.getLogger(BookingService.class.getName());


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

        System.out.println("Booking " + request.customerId() + " Tiden den kom in är "+ Instant.now());
        System.out.println("Booking " + request.roomId());
        System.out.println("Booking " + request.startDate());
        System.out.println("Booking " + request.endDate());


        logger.info("booking " + request.customerId());
        logger.warning("booking " + request.customerId());

        logger.config("booking " + request.customerId());

        logger.severe("booking could not be created" );

        Customer customer = customerRepository.findById(request.customerId())
                .orElseThrow(() -> new NotFoundException("Kunden finns inte"));


        Room room = roomRepository.findById(request.roomId())
                .orElseThrow(() -> new NotFoundException("Rummet finns inte"));

        validateDateRange(request.startDate(),request.endDate());
        validateRoomIsAvailable(request.roomId(),request.startDate(),request.endDate(), null);
        Booking booking = new Booking(customer,room,request.startDate(),request.endDate(),BookingStatus.ACTIVE);
        return bookingRepository.save(booking);
    }


    private void validateRoomIsAvailable(Long roomId, LocalDate startDate, LocalDate endDate, Long bookingIdToIgnore) {
        List<Booking> bookings = bookingRepository.findByRoomIdAndStatus(roomId, BookingStatus.ACTIVE);
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
