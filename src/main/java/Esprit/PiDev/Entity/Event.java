package Esprit.PiDev.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;


@SuppressWarnings("ALL")
@Entity
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Event_id")
    private Long id;

    @NotNull
    @Column(name = "Event_name")
    private String name;

    @NotNull
    @Column(name = "Event_description")
    private String Description;

    @Enumerated(EnumType.STRING)
    @Column(name = "Event_type")
    private Enum_Event_Type type;

    @NotNull
    @Column(name = "Event_capacity")
    private int capacity;

    @Column(name = "Event_hasFinished")
    private boolean hasFinished;

    @Temporal(TemporalType.DATE)
    @Column(name = "Event_start_date")
    private Date start_date;

    @Temporal(TemporalType.DATE)
    @Column(name = "Event_end_date")
    private Date end_date;

    @JsonIgnore 
    @OneToOne(targetEntity = Dbo_User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id_Creator")
    private Dbo_User user_Event_Creator;

    @JsonIgnore 
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Dbo_User> child_participant;

    public Event() {

    }


    public int  getNumberOFdaybythisEvent()   {
        Date start = getStart_date();
        Date end = getEnd_date();




        long diff = end.getTime() - start.getTime();
        long res = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        return Math.round(res);
    }

    public Dbo_User getUser_Event_Creator() {
        return user_Event_Creator;
    }

    public void setUser_Event_Creator(Dbo_User user_Event_Creator) {
        this.user_Event_Creator = user_Event_Creator;
    }

    public Set<Dbo_User> getChild_participant() {
        return child_participant;
    }

    public void setChild_participant(Set<Dbo_User> child_participant) {
        this.child_participant = child_participant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Enum_Event_Type getType() {
        return type;
    }

    public void setType(Enum_Event_Type type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isHasFinished() {
        return hasFinished;
    }

    public void setHasFinished(boolean hasFinished) {
        this.hasFinished = hasFinished;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Event(String name, String description, Enum_Event_Type type, int capacity, boolean hasFinished, Date start_date, Date end_date, Dbo_User user_Event_Creator) {
        this.name = name;
        Description = description;
        this.type = type;
        this.capacity = capacity;
        this.hasFinished = hasFinished;
        this.start_date = start_date;
        this.end_date = end_date;
        this.user_Event_Creator = user_Event_Creator;
    }

    public Event(String name, String description, Enum_Event_Type type, int capacity, boolean hasFinished, Date start_date, Date end_date, Dbo_User user_Event_Creator, Set<Dbo_User> child_participant) {
        this.name = name;
        Description = description;
        this.type = type;
        this.capacity = capacity;
        this.hasFinished = hasFinished;
        this.start_date = start_date;
        this.end_date = end_date;
        this.user_Event_Creator = user_Event_Creator;
        this.child_participant = child_participant;
    }


    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", Description='" + Description + '\'' +
                ", type=" + type +
                ", capacity=" + capacity +
                ", hasFinished=" + hasFinished +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", user_Event_Creator=" + user_Event_Creator +
                ", child_participant=" + child_participant +
                '}';
    }
}
