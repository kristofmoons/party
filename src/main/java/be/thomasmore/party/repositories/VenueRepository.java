package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Venue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VenueRepository extends CrudRepository<Venue, Integer> {
    Iterable<Venue> findByOutdoor(boolean isOutdoor);
    Iterable<Venue> findByIndoor(boolean isIndoor);
 //  Iterable<Venue> findByCapacityBetween(int min, int max);
 //   Iterable<Venue> findByCapacityGreaterThan(int min);
//    @Query("SELECT v FROM Venue v WHERE (:min IS NULL OR:min <= v.capacity) AND ( :max IS NULL OR v.capacity <=  :max )")
//    List<Venue> findByCriteria(@Param("min") Integer minCapacity, @Param("max") Integer maxCapacity);
    @Query("select v from  Venue v where (?1 IS NULL or v.capacity >= ?1)and (?2 IS NULL or v.capacity <= ?2)and (?3 IS NULL or v.distanceFromPublicTransportInKm <= ?3)")
    List<Venue> findByCriteria(Integer jef,Integer joske, Integer josephienke);

}
