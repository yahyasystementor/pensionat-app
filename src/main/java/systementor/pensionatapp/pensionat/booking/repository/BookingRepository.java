package systementor.pensionatapp.pensionat.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import systementor.pensionatapp.pensionat.booking.BookingStatus;
import systementor.pensionatapp.pensionat.booking.model.Booking;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {


    List<Booking> findByCustomerIdAndStatus(Long customerId, BookingStatus status);

    List<Booking> findByRoomAndStatus(Long roomId, BookingStatus status);


}
