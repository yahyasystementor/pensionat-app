package systementor.pensionatapp.pensionat.booking.controller;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import systementor.pensionatapp.pensionat.booking.model.Booking;
import systementor.pensionatapp.pensionat.booking.model.CreateBookingRequest;
import systementor.pensionatapp.pensionat.booking.service.BookingService;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
public class BookingController {


    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Booking createBooking(@RequestBody @Valid CreateBookingRequest request) {
        return bookingService.createBooking(request);
    }


}
