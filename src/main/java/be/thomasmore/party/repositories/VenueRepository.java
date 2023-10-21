package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Venue;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface VenueRepository extends CrudRepository<Venue, Integer> {
    Iterable<Venue> findByOutdoor(boolean outdoor);
    Iterable<Venue> findByIndoor(boolean indoor);



    Optional<Venue> findFirstByIdLessThanOrderByIdDesc(int id);
    Optional<Venue> findFirstByIdGreaterThanOrderByIdAsc(int id);

    Optional<Venue> findFirstByOrderByIdDesc();
    Optional<Venue> findFirstByOrderByIdAsc();

    Iterable<Venue> findByCapacityLessThan(Integer maxCapacity);
    Iterable<Venue> findByCapacityGreaterThanEqual(Integer minCapacity);
    Iterable<Venue> findByCapacityBetween(Integer minCapacity, Integer maxCapacity);


    List<Venue> findAllByCapacityGreaterThanEqual(Integer minCapacity);
    List<Venue> findAllByCapacityLessThan(Integer maxCapacity);
    List<Venue> findAllByCapacityBetween(Integer minCapacity, Integer maxCapacity);
}
