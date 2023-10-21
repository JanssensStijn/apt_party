package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Venue;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface VenueRepository extends CrudRepository<Venue, Integer> {
    Iterable<Venue> findByOutdoor(boolean outdoor);
    Iterable<Venue> findByIndoor(boolean indoor);

    Iterable<Venue> findByCapacityLessThan(int highestNumber);
    Iterable<Venue> findByCapacityBetween(int lowestNumber, int highestNumber);
    Iterable<Venue> findByCapacityGreaterThan(int lowestNumber);

    Optional<Venue> findFirstByIdLessThanOrderByIdDesc(int id);
    Optional<Venue> findFirstByIdGreaterThanOrderByIdAsc(int id);


    Optional<Venue> findFirstByOrderByIdDesc();
    Optional<Venue> findFirstByOrderByIdAsc();

}
