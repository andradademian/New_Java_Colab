package map.project.demo.Repository;
import map.project.demo.Domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGenreRepo extends JpaRepository<Genre, String>{
}
