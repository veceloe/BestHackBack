package mirea.enjoyers.BestHackBack.Services.Impls;

import mirea.enjoyers.BestHackBack.Models.Event;
import mirea.enjoyers.BestHackBack.Services.EventService;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {
    // Inject EventRepository

    @Override
    public Event createEvent(Event event) {
        // Save event to the database
        return event;
    }

    @Override
    public Event getEventById(Long id) {
        // Retrieve event from the database
        return null;
    }
}