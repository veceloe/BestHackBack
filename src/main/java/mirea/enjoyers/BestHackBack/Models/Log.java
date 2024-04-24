package mirea.enjoyers.BestHackBack.Models;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "logs")
@AllArgsConstructor
@NoArgsConstructor
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "timestamp")
    @CreationTimestamp
    private LocalDateTime timestamp;

    @Column(name = "level")
    private String level;

    @Column(name = "message")
    private String message;

    @Column(name = "session")
    private String session;

    @Column(name = "logger")
    private String logger;

}
