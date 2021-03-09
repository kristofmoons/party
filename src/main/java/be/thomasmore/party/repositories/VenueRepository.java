package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Venue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VenueRepository extends CrudRepository<Venue, Integer> {
    Iterable<Venue> findByOutdoor(boolean isOutdoor);
    Iterable<Venue> findByIndoor(boolean isIndoor);
 //  Iterable<Venue> findByCapacityBetween(int min, int max);
 //   Iterable<Venue> findByCapacityGreaterThan(int min);
    @Query("SELECT v FROM Venue v WHERE :min IS NULL OR:min <= v.capacity AND v.capacity <= :max OR :max IS NOT NULL")
    List<Venue> findByCriteria(@Param("min") Integer minCapacity, @Param("max") Integer maxCapacity);
}
