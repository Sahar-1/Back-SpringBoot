package Esprit.PiDev.RestController;

import Esprit.PiDev.Entity.Dbo_User;
import Esprit.PiDev.Entity.Enum_Event_Type;
import Esprit.PiDev.Entity.Event;
import Esprit.PiDev.Entity.RequestApiForm.EventRequest;
import Esprit.PiDev.Entity.RequestApiForm.MessageResponse;
import Esprit.PiDev.Exception.API_Request_Exception_UNAUTHORIZED_STATUS_403;
import Esprit.PiDev.InterfaceService.Interface_Event_Service;
import Esprit.PiDev.Repository.Event_Repository;
import Esprit.PiDev.Repository.User_Repository;
import Esprit.PiDev.Service.Session_UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

@SuppressWarnings({"UnnecessaryLocalVariable", "SpringJavaAutowiredFieldsWarningInspection"})
@RestController
public class Event_Controller_Rest_Web_Service {
    @Autowired
    private Interface_Event_Service eventService;
    @Autowired
    private Event_Repository event_repository;
    @Autowired
    private User_Repository user_repository;

    @Secured(value = {"ROLE_ADMIN , ROLE_DIRECTEUR"})
    @PostAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DIRECTEUR')")
    @RequestMapping("/Create-Event")
    public ResponseEntity<?> CreateEvent(@RequestBody EventRequest eventRequest, Authentication authentication) {
        //get the current user LoggedIn
        Session_UserDetails userDetails = (Session_UserDetails) authentication.getPrincipal();
        if ((authentication.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_ENFANT")))
                || (authentication.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_USER")))) {
            throw new API_Request_Exception_UNAUTHORIZED_STATUS_403(authentication.getName().toUpperCase() + " IS UNAUTHORIZED TO USE THIS CONTENT WITH THIS AUTHORITIES !!! ");
        }
        Event eventToSave = new Event();
        Dbo_User userCreator = user_repository.findByEmail(userDetails.getEmail());
        eventToSave.setUser_Event_Creator(userCreator);
        eventToSave.setCapacity(eventRequest.getCapacity());
        eventToSave.setStart_date(eventRequest.getStart_date());
        eventToSave.setEnd_date(eventRequest.getEnd_date());
        eventToSave.setName(eventRequest.getName());
        eventToSave.setDescription(eventRequest.getDescription());
        eventToSave.setType(Enum_Event_Type.valueOf(eventRequest.getType()));
        Event event = event_repository.save(eventToSave); // Persist the event
        Set<Dbo_User> All_user_Participant = new HashSet<>();

        Set<Long> list_Participant = eventRequest.getChild_participant(); // get all identify of user's participant
        int number_event = list_Participant.size(); // get the number
        if (event.getCapacity() >= number_event) { // Check if user can participate
            for (Long idp : list_Participant) { // Iterate the list of participant
                Dbo_User thisParticipant = user_repository.findById(idp).orElse(null);
                All_user_Participant.add(thisParticipant); // Add this user to list of participant

                event.setChild_participant(All_user_Participant); // save all this user iterating to this event
                event_repository.save(event); // persist all this event with all participant
            }
            event.setCapacity(event.getCapacity() - number_event);
            event_repository.save(event);

        } else {
            event_repository.delete(event);
            return ResponseEntity.badRequest().body(new MessageResponse("the Capacity of Event is full "));
        }


        return ResponseEntity.ok(new MessageResponse("Event has been created successfully"));


    }


    @RequestMapping("/Parent-child-event/{id_event}/{user_id}")
    public ResponseEntity<?> parent_affect_child_to_event(@PathVariable("id_event") Long id_event
            , Authentication authentication,
                                                          @PathVariable("user_id") Long user_id) {

        if ((authentication.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_ENFANT")))
                || (authentication.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_USER")))) {
            throw new API_Request_Exception_UNAUTHORIZED_STATUS_403(authentication.getName().toUpperCase() + " IS UNAUTHORIZED CONTENT WITH THIS AUTHORITY !!! ");
        } else {

            eventService.affect_parent_child(id_event, user_id, authentication);
            int test = eventService.getEventById(id_event).get().getCapacity();
            if(test == 0){
                return ResponseEntity.ok(new MessageResponse("No place this event with full capacity"));
            }
            return ResponseEntity.ok(new MessageResponse("Saved"));
        }

    }

    @GetMapping(value = "Get-All-Event/{pageNo}/{pageSize}")
    public List<Event> getAllEvent(@PathVariable int pageNo, @PathVariable int pageSize) {
        List<Event> paginator = eventService.findPaginated(pageNo, pageSize);
        return paginator;
    }

    @GetMapping(value = "/days_remaining/{id_event}")
    public ResponseEntity<?> get_number_of_days_remaining_of_this_event(@PathVariable("id_event") Long id_event) {
        Event thisEvent = event_repository.findById(id_event).orElseThrow(() -> new RuntimeException("Not found"));
        Date endDate = thisEvent.getEnd_date();
        int numberOfDay = thisEvent.getNumberOFdaybythisEvent();
        Date now = new Date();
        long Check_diff = now.getTime() - endDate.getTime();
        long dif = TimeUnit.DAYS.convert(Check_diff, TimeUnit.MILLISECONDS);
        if (dif <= 0) {
            return ResponseEntity.ok(new MessageResponse("This Event is already finished"));
        }
        return ResponseEntity.ok(new MessageResponse("" + numberOfDay));
    }

    @DeleteMapping("/delete_event/{id_event}")
    public void delete_Event_by_identify(@PathVariable("id_event") Long id_event) {
        eventService.delete_Event(id_event);
    }

    @GetMapping("/get_event_by_id/{id_event}")
    public Optional<Event> get_event_by_id(@PathVariable("id_event") Long id_event){
       return  eventService.getEventById(id_event);
    }

}