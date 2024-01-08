package map.project.demo.Repository;

import jakarta.transaction.Transactional;
import map.project.demo.Domain.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICinemaRepo extends JpaRepository<Cinema, String> {

}
