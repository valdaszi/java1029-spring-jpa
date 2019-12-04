package lt.bit.java2.repositories;

import lt.bit.java2.entities.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, String> {

    Account findByEmail(String email);
}
