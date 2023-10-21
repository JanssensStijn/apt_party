package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Artist;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ArtistRepository extends CrudRepository<Artist, Integer> {
    Optional<Artist> findFirstByIdGreaterThanOrderByIdAsc(Integer id);

    Optional<Artist> findFirstByOrderByIdAsc();

    Optional<Artist> findFirstByIdLessThanOrderByIdDesc(Integer id);

    Optional<Artist> findFirstByOrderByIdDesc();

    Iterable<Artist> findByArtistNameContainsIgnoreCase(String keyword);
    List<Artist> findAllByArtistNameContainsIgnoreCase(String keyword);
}
