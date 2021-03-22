package Esprit.PiDev.Repository;

import Esprit.PiDev.Entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface Event_Repository extends PagingAndSortingRepository<Event , Long > {


    @Query("select e.name , e.Description , e.start_date , e.end_date, e.capacity ,e.type   from Event e ")
    Page<Event> getAllEvents(Pageable paging);
    @Transactional
    @Modifying
    @Query("delete from Event e where e.hasFinished =true ")
    void delete_Event_Has_Finished();

    @Query("select e from Event e where e.hasFinished =true ")
    Event getHasFinished();
}
