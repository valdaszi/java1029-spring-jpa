package lt.bit.java2.api;

import lt.bit.java2.entities.Driver;
import lt.bit.java2.repositories.DriverRepository;
import org.hibernate.StaleObjectStateException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/driver")
public class DriverApi {

    private final DriverRepository driverRepository;

    public DriverApi(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @RolesAllowed("DRIVER_READ")
    @GetMapping("/{id}")
    ResponseEntity<Driver> getDriverById(@PathVariable int id) {
        Optional<Driver> driver = driverRepository.findById(id);
        if (!driver.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok(driver.get());
        }
    }

    @RolesAllowed("DRIVER_WRITE")
    @DeleteMapping("/{id}")
    ResponseEntity deleteById(@PathVariable int id) {
        if (driverRepository.existsById(id)) {
            driverRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @RolesAllowed("DRIVER_WRITE")
    @PostMapping
    ResponseEntity<Driver> create(@RequestBody Driver driver) {
        return ResponseEntity.ok(driverRepository.save(driver));
    }

    @RolesAllowed("DRIVER_WRITE")
    @PutMapping("/{id}")
    ResponseEntity<Driver> update(@PathVariable int id, @RequestBody Driver driver) {
        Optional<Driver> d = driverRepository.findById(id);
        if (!d.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
//        Driver drv = d.get();
//        drv.setPid(driver.getPid());
//        drv.setFirstName(driver.getFirstName());
//        drv.setLastName(driver.getLastName());
//        return ResponseEntity.ok(driverRepository.save(drv));

        try {
            driver.setId(id);
            return ResponseEntity.ok(driverRepository.save(driver));
        } catch (ObjectOptimisticLockingFailureException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @RolesAllowed("DRIVER_READ")
    @GetMapping()
    ResponseEntity<Page<Driver>> getPage(
            @RequestParam(defaultValue = "10", required = false) int size,
            @RequestParam(defaultValue = "1", required = false) int page) {
        if (size < 3) size = 3;
        if (page < 1) page = 1;
        return ResponseEntity.ok(
                driverRepository.findAll(PageRequest.of(page - 1, size))
        );
    }


    @RolesAllowed("DRIVER_READ")
    @GetMapping("/find-by-pid/{pid}")
    Driver findByPid(@PathVariable String pid) {
        return driverRepository.findByPid(pid);
    }

    @RolesAllowed("DRIVER_READ")
    @GetMapping("/find-by-lastname/{lastname}")
    List<Driver> findByLastName(@PathVariable String lastname) {
        return driverRepository.findByLastName(lastname);
    }

}
