package systementor.pensionatapp.pensionat.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import systementor.pensionatapp.pensionat.booking.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {


}
