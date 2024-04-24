package mirea.enjoyers.BestHackBack.Repositories;

import mirea.enjoyers.BestHackBack.Models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
