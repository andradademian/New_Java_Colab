package map.project.demo.Repository;

import map.project.demo.Domain.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IActorRepository extends JpaRepository<Actor, String> {
}
