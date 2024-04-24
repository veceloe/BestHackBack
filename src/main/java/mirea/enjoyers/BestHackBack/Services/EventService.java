package mirea.enjoyers.BestHackBack.Services;

import mirea.enjoyers.BestHackBack.Models.Event;

public interface EventService {
    Event createEvent(Event event);
    Event getEventById(Long id);
}