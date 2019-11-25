package lt.bit.java2.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Data
public class Comment extends Base {
    @Column(length = 2048)
    private String comment;

    private LocalDateTime date;

    @ManyToOne
    Radar radar;
}
