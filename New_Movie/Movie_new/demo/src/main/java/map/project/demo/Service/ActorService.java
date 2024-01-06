package map.project.demo.Service;

import jakarta.transaction.Transactional;
import map.project.demo.Domain.Actor;
import map.project.demo.Repository.IActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {

    @Autowired
    private IActorRepository actorRepository;

    @Transactional
    public void saveActor(Actor actor) {
        actorRepository.save(actor);
    }

    @Transactional
    public List<Actor> findAll() {
        return actorRepository.findAll();
    }

}
