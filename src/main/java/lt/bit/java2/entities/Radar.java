package lt.bit.java2.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "radars")
public class Radar extends Base {
    private LocalDateTime date;

    @Column(length = 10)
    private String number;

    private double distance;
    private double time;

    @ManyToOne(fetch = FetchType.EAGER)
    private Driver driver;

    @Transient
    private double speed;

    @OneToMany(mappedBy = "radar", cascade = CascadeType.ALL)
    private List<Comment> comments;

    public double getSpeed() {
        return speed;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

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

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
