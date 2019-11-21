package lt.bit.java2.api;

import lt.bit.java2.entities.Driver;
import lt.bit.java2.repositories.DriverRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/driver")
public class DriverApi {

    private final DriverRepository driverRepository;

    public DriverApi(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @GetMapping("/{id}")
    Optional<Driver> getDriverById(@PathVariable int id) {
        Optional<Driver> driver = driverRepository.findById(id);
        return driver;
    }

}
