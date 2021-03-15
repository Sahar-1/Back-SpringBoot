package Esprit.PiDev.Service;

import Esprit.PiDev.Entity.Dbo_User;
import Esprit.PiDev.Entity.Event;
import Esprit.PiDev.Entity.RequestApiForm.MessageResponse;
import Esprit.PiDev.Exception.API_Request_Exception_NotFound;
import Esprit.PiDev.InterfaceService.Interface_Event_Service;
import Esprit.PiDev.Repository.Event_Repository;
import Esprit.PiDev.Repository.User_Repository;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("ALL")
@Service
public class Event_Service implements Interface_Event_Service {
    @Autowired
    private Event_Repository event_repository;
    @Autowired
    private User_Repository user_repository;
    private final org.jboss.logging.Logger logger = LoggerFactory.logger(Event_Service.class);

    @Override
    public Event create_Event(Event event) {

        return event_repository.save(event);
    }


    @Override
    public void delete_Event(Long id_event) {
        if(! event_repository.existsById(id_event)){
            throw new API_Request_Exception_NotFound("Event not found with identify : " +id_event);
        }
        event_repository.deleteById(id_event);
    }

    @Override
    public Optional<Event> getEventById(Long id_Event) {
        if( ! event_repository.existsById(id_Event)){
            throw  new API_Request_Exception_NotFound("Event not found with identify : " +id_Event);
        }
        return event_repository.findById(id_Event);
    }

    @Override
    public List<Event> findPaginated(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page <Event> pageResult= event_repository.getAllEvents(paging);;
        return  pageResult.toList();
    }

    @Override
    public ResponseEntity<?> affect_parent_child(Long event_id, Long id_user, Authentication authentication) {
        Event this_event = event_repository.findById(event_id).orElse(null);
        Dbo_User child_ToParticipate = user_repository.findById(id_user).orElse(null);
        Event existingEvent = event_repository.findById(event_id).orElseThrow(
                () -> new RuntimeException("Event not found ")
        );
        Session_UserDetails userDetails = (Session_UserDetails) authentication.getPrincipal();
        Dbo_User currentParent = user_repository.findByEmail(userDetails.getEmail());


        if (existingEvent.getCapacity() == 0) {
            return ResponseEntity.badRequest().body(new MessageResponse("no place available in this event"));
        }
        if (this_event.getChild_participant().contains(child_ToParticipate)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Child "+child_ToParticipate.getFullName()+" already exist"));
        } else {
            this_event.setCapacity(this_event.getCapacity() - 1);
            logger.debug(this_event.getChild_participant().add(child_ToParticipate));
            this_event.getChild_participant().add(child_ToParticipate);
            event_repository.save(this_event);
            return ResponseEntity.ok(new MessageResponse("Child "+child_ToParticipate.getFullName()+" has been registered to event "+existingEvent.getName()));
        }


    }
}
