package systementor.pensionatapp.pensionat.booking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import systementor.pensionatapp.pensionat.booking.BookingStatus;
import systementor.pensionatapp.pensionat.customer.model.Customer;
import systementor.pensionatapp.pensionat.room.model.Room;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class Booking {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(optional = false)
    private Customer customer;

    @ManyToOne(optional = false)
    private Room room;

    @NotNull(message = "StartDatum måste anges")
    @FutureOrPresent(message = "Startdatum kan inte vara bakåt i tiden")
    private LocalDate startDatum;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    protected Booking() {
    }


    public Booking(Customer customer, Room room, LocalDate startDatum, BookingStatus status) {
        this.customer = customer;
        this.room = room;
        this.startDatum = startDatum;
        this.status = status;
    }
}
