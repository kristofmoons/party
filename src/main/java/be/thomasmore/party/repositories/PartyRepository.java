package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Party;
import be.thomasmore.party.model.Venue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PartyRepository extends CrudRepository <Party, Integer > {
    @Query("select p from Party p where ?1 is null or p.id = ?1")
    Optional<Party> findById(Integer Femke);
    @Query("select p from Party p where ?1 = p.venue ")
    Iterable<Party>findPartiesByVenue(Venue verake);
}
