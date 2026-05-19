package systementor.pensionatapp.pensionat.room.service;

import org.springframework.stereotype.Service;
import systementor.pensionatapp.pensionat.error.NotFoundException;
import systementor.pensionatapp.pensionat.room.model.Room;
import systementor.pensionatapp.pensionat.room.repository.RoomRepository;

import java.util.List;

@Service
public class RoomService {

   private final RoomRepository repository;

    public RoomService(RoomRepository repository) {
        this.repository = repository;
    }

    public List<Room> getAllRooms() {
        return repository.findAll();
    }


    public Room getRoomById(Long id) {
        return repository.findById(id)
                .orElseThrow(()  -> new NotFoundException("Rummet hittades inte"));
    }



}
