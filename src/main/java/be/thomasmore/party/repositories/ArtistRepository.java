package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Artist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ArtistRepository extends CrudRepository<Artist, Integer> {


    @Query("select a from Artist a where ?1 is null or (lower(a.artistName) like lower(concat('%', ?1,'%')))" +
            " or (lower(a.bio) like lower(concat('%', ?1,'%')))" +
           " or (lower(a.genre) like lower(concat('%', ?1,'%')))" +
           " or (lower(a.portfolio) like lower(concat('%', ?1,'%')))")
    Iterable<Artist> findByKeyword(String keyword);
}
