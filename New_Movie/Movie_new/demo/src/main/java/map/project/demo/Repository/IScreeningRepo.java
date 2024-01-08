package map.project.demo.Repository;

import map.project.demo.Domain.BaseScreening;
import map.project.demo.Strategy.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IScreeningRepo extends JpaRepository<BaseScreening, String> {
}
