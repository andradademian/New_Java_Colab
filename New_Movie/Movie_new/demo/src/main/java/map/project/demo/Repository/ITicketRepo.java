package map.project.demo.Repository;

import map.project.demo.Domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITicketRepo extends JpaRepository<Ticket, String> {
}
