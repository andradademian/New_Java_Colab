package map.project.demo.Repository;
import map.project.demo.Domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
public interface IGenreRepo extends JpaRepository<Genre, String>{
}
