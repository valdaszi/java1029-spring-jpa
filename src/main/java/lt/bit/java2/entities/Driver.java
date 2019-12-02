package lt.bit.java2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "drivers")
@NamedEntityGraph(
        name = Driver.GRAPH_RADARS,
        attributeNodes = {
                @NamedAttributeNode(
                    value = "radars",
                    subgraph = Driver.GRAPH_RADARS_COMMENTS),
                @NamedAttributeNode("address")
        },
        subgraphs = @NamedSubgraph(
                name = Driver.GRAPH_RADARS_COMMENTS,
                attributeNodes = @NamedAttributeNode("comments"))
)
@NamedQuery(
        name = Driver.QUERY_ALL,
        query = "SELECT d FROM Driver d"
)
@NamedQuery(
        name = Driver.QUERY_BY_NAME,
        query = "SELECT d FROM Driver d WHERE d.firstName = :name"
)
@Data
public class Driver extends Base {
    public static final String QUERY_ALL = "query.driver.all";
    public static final String QUERY_BY_NAME = "query.driver.byName";

    public static final String GRAPH_RADARS = "graph.driver.radars";
    static final String GRAPH_RADARS_COMMENTS = "graph.driver.radars.comments";

    @Column(length = 11)
    private String pid;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @JsonIgnore
    @OneToMany(mappedBy = "driver",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<Radar> radars;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "driver_address",
            joinColumns = @JoinColumn(name = "driver_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id"))
    private Set<Address> address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return Objects.equals(id, driver.id) &&
                Objects.equals(pid, driver.pid) &&
                Objects.equals(firstName, driver.firstName) &&
                Objects.equals(lastName, driver.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pid, firstName, lastName);
    }
 }
