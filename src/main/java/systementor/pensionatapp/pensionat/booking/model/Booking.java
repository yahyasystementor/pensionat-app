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


    @NotNull(message = "Slutdatum måste anges")
    @FutureOrPresent(message = "Slutdatum kan inte vara bakåt i tiden")
    private LocalDate endDatum;


    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    protected Booking() {
    }


    public Booking(Customer customer, Room room, LocalDate startDatum,LocalDate endDatum, BookingStatus status) {
        this.customer = customer;
        this.room = room;
        this.startDatum = startDatum;
        this.endDatum = endDatum;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public @NotNull(message = "StartDatum måste anges") @FutureOrPresent(message = "Startdatum kan inte vara bakåt i tiden") LocalDate getStartDatum() {
        return startDatum;
    }

    public void setStartDatum(@NotNull(message = "StartDatum måste anges") @FutureOrPresent(message = "Startdatum kan inte vara bakåt i tiden") LocalDate startDatum) {
        this.startDatum = startDatum;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public @NotNull(message = "Slutdatum måste anges") @FutureOrPresent(message = "Slutdatum kan inte vara bakåt i tiden") LocalDate getEndDatum() {
        return endDatum;
    }

    public void setEndDatum(@NotNull(message = "Slutdatum måste anges") @FutureOrPresent(message = "Slutdatum kan inte vara bakåt i tiden") LocalDate endDatum) {
        this.endDatum = endDatum;
    }
}
