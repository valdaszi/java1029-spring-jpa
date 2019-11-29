package lt.bit.java2.repositories;

import lt.bit.java2.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface DriverRepository extends JpaRepository<Driver, Integer> {

    Driver findByPid(String pid);

    List<Driver> findByLastName(String lastName);
}
