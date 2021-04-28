package Esprit.PiDev.Service;

import java.util.Date;
import java.util.logging.Logger;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import Esprit.PiDev.Entity.Event;
import Esprit.PiDev.Repository.Event_Repository;

@SuppressWarnings("ALL")
@Component
public class FuncReportScheduled implements InitializingBean {
    @Autowired
    private Event_Repository event_repository;

    Logger loggerCRON = Logger.getLogger(this.getClass().getName());

    @Override
    public void afterPropertiesSet() throws Exception {
        loggerCRON.info("---------------- CRON  TASK  BEGIN----------------");
        this.delete_Event_Trigger_Every_two_hours();
        loggerCRON.info("---------------- END  CRON  TASK  ----------------");
    }

    //@Scheduled(cron = "* */10 * * * ?")
    @Transactional
    @RequestMapping("/deleteCron")
    public void delete_Event_Trigger_Every_two_hours() {
        Iterable<Event> eventList = event_repository.findAll();
        loggerCRON.info("" + eventList);
        Date current_date = new Date();
        for (Event eventToDelete : eventList
        ) {
            loggerCRON.info("" + eventToDelete.getEnd_date());
            if (current_date.after(eventToDelete.getEnd_date())) {
                eventToDelete.setHasFinished(true);
                event_repository.save(eventToDelete);


            }

        }


    }
}
