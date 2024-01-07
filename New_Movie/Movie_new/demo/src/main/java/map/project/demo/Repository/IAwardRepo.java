package map.project.demo.Repository;

import map.project.demo.Domain.Award;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAwardRepo extends JpaRepository<Award, String> {
}
