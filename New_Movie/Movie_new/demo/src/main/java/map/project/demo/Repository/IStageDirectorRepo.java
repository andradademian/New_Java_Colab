package map.project.demo.Repository;

import map.project.demo.Domain.StageDirector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStageDirectorRepo extends JpaRepository<StageDirector, String> {
}
