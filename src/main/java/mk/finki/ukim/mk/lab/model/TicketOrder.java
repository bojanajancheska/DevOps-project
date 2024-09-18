package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name="productions")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TicketOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User user;
    @DateTimeFormat(pattern = "yyyy-MM-ddHH:mm:ss")
    private LocalDateTime orderDate;

    private String movieTitle;
    private Integer numberOfTickets;
}
