package systementor.pensionatapp.pensionat.room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import systementor.pensionatapp.pensionat.room.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
