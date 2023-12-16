package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Animal;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AnimalRepository extends CrudRepository<Animal, Integer> {
    Optional<Animal> findFirstByIdGreaterThanOrderByIdAsc(Integer id);

    Optional<Animal> findFirstByOrderByIdAsc();

    Optional<Animal> findFirstByIdLessThanOrderByIdDesc(Integer id);

    Optional<Animal> findFirstByOrderByIdDesc();

    Iterable<Animal> findByNameContainsIgnoreCase(String keyword);
    List<Animal> findAllByNameContainsIgnoreCase(String keyword);

    Animal findByUsername(String name);
}
