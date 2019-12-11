package lt.bit.java2.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "privileges")
@Data
public class Privilege {

    @Id
    private String name;

    @Column(length = 4096)
    private String description;

    @ManyToMany
    @JoinTable(
            name = "roles_privileges",
            joinColumns = { @JoinColumn(name = "privilege_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    private List<Role> roles;
}
