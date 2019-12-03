package lt.bit.java2.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
@Data
public class Account {

    @Id
    private String email;
    private String name;
    private String password;
}
