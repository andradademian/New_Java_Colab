package map.project.demo.Repository;

import map.project.demo.Domain.Spectator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISpectatorRepo extends JpaRepository<Spectator, String> {
}
