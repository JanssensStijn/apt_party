package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Venue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

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


    @Query("SELECT v FROM Venue v WHERE ?1 IS NULL OR v.capacity >= ?1")
    List<Venue> findAllByCapacityGreaterThanEqual( Integer minCapacity );
    @Query("SELECT v FROM Venue v WHERE ?1 IS NULL OR v.capacity <= ?1")
    List<Venue> findAllByCapacityLessThanEqual( Integer maxCapacity );
    @Query("SELECT v FROM Venue v WHERE (?1 IS NULL OR v.capacity >= ?1) AND (?2 IS NULL OR v.capacity <= ?2)")
    List<Venue> findAllByCapacityBetween(Integer minCapacity, Integer maxCapacity);
}
