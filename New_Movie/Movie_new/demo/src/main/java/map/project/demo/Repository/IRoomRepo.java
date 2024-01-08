package map.project.demo.Repository;

import map.project.demo.Domain.Room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoomRepo extends JpaRepository<Room, String> {
}
