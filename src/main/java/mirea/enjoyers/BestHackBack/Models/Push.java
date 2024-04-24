package mirea.enjoyers.BestHackBack.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "Pushes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Push {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "category")
    private String category;

    @Column(name = "image")
    private String image;

    @Column(name = "role_destination")
    private String roleDestination;

    @Column(name = "status")
    private String status;

    @Column(name = "datetime")
    @CreationTimestamp
    private LocalDateTime datetime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
