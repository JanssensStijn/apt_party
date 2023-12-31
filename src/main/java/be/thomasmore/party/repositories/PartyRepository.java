package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Party;
import be.thomasmore.party.model.Party;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PartyRepository extends CrudRepository<Party, Integer> {
    Optional<Party> findFirstByIdGreaterThanOrderByIdAsc(Integer id);

    Optional<Party> findFirstByOrderByIdAsc();

    Optional<Party> findFirstByIdLessThanOrderByIdDesc(Integer id);

    Optional<Party> findFirstByOrderByIdDesc();
}