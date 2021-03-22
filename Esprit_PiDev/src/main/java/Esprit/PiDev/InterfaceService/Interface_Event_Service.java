package Esprit.PiDev.InterfaceService;

import Esprit.PiDev.Entity.Event;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface Interface_Event_Service {
    Event create_Event(Event event);

    void delete_Event(Long id);

    Optional<Event> getEventById(Long id);

    List<Event> findPaginated(int pageNo, int pageSize);

    ResponseEntity<?> affect_parent_child(Long event_id, Long id_user, Authentication authentication);
}
