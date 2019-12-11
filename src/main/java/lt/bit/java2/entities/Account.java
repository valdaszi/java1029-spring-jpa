package lt.bit.java2.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "accounts")
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String email;

    private String name;
    private String password;

//    private String role;

    private Boolean disabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "users_roles",
        joinColumns = { @JoinColumn(name = "account_id") },
        inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    private List<Role> roles;
}
