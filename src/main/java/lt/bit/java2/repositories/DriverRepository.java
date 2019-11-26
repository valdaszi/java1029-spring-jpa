package lt.bit.java2.repositories;

import lt.bit.java2.entities.Driver;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DriverRepository extends PagingAndSortingRepository<Driver, Integer> {
}
