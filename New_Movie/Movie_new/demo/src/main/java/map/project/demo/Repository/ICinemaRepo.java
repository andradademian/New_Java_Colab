package map.project.demo.Repository;

import jakarta.transaction.Transactional;
import map.project.demo.Domain.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ICinemaRepo extends JpaRepository<Cinema, String> {
//    @Modifying
//    @Transactional
//    @Query("UPDATE Cinema c SET c.listOfRooms = CONCAT(c.listOfRooms, ?2) WHERE c.id = ?1")
//    void addRoom(String cinemaId, String roomId);
//
//    @Modifying
//    @Transactional
//    @Query("UPDATE Cinema c SET c.listOfRooms = REPLACE(c.listOfRooms, ?2, '') WHERE c.id = ?1")
//    void deleteRoom(String cinemaId, String roomId);
}
