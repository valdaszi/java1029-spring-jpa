package lt.bit.java2.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "radars")
public class Radar {
    private Integer id;
    private LocalDateTime date;
    private String number;
    private double distance;
    private double time;
    private Driver driver;
    private double speed;
    private List<Comment> comments;

    @Transient
    public double getSpeed() {
        return speed;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Column(length = 10)
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
        if (this.getTime() != 0) {
            this.speed = this.getDistance() / this.getTime() * 3.6;
        }
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
        if (this.getTime() != 0) {
            this.speed = this.getDistance() / this.getTime() * 3.6;
        }

    }

    @ManyToOne(fetch = FetchType.EAGER)
    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @OneToMany(mappedBy = "radar")
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
