package Esprit.PiDev.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@SuppressWarnings("ALL")
@Entity
@Table(name = "T_User")
public class Dbo_User implements Serializable {

    /**
     *
     */
//dfhdfsdhf
    private static final long serialVersionUID = 1L;
    /*-----------------------****Bean_Attributes****-------------------------------------*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_id")
    private Long id;

    @NotNull
    @Column(name = "User_First_Name")
    private String firstName;

    @NotNull
    @Column(name = "User_Last_Name")
    private String lastName;


    @NotNull
    @Column(columnDefinition = "boolean default false")
    private boolean actif;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "User_Birthday_Date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @NotNull
    @Column(name = "User_Email")
    private String email;

    @NotNull
    @Column(name = "User_Password")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))

    private Set<Dbo_Role> role = new HashSet<>();

    @Column(name = "created_time", updatable = false)
    private Date createdTime;

    @Column(name = "Last_Logged_In", updatable = true)
    private Date lastLoggedIn;

    @Column(name = "Last_Logged_out", updatable = true)
    private Date lastLoggedOut;

    @Enumerated(EnumType.STRING)
    @Column(name = "Auth_User_Provider")
    private Dbo_User_Provider dbo_User_Provider;

    @Column(name = "Last_Session_Id_Generated")
    private String Session_Id;

    @Column(name = "account_non_locked")
    private boolean accountNonLocked;

    @Column(name = "failed_attempt")
    private int failedAttempt;

    @Column(name = "lock_time")
    private Date lockTime;

    @ManyToMany(mappedBy = "child_participant")
    private Set<Event> events;

    @Column(name = "upload_dir")
    private String uploadDir;
	@JsonIgnore
	@OneToMany(mappedBy="sender")
	private List<Message> Messagesender = new ArrayList<Message>();
	@JsonIgnore
	@OneToMany(mappedBy="receiver")
	private List<Message> Messagereceiver = new ArrayList<Message>();

	@OneToOne

	private Satisfaction satisfactions;


    /*-----------------------****Getters_Setters_Methods()****-------------------------------------*/

    public List<Message> getMessagesender() {
		return Messagesender;
	}

	public void setMessagesender(List<Message> messagesender) {
		Messagesender = messagesender;
	}

	public List<Message> getMessagereceiver() {
		return Messagereceiver;
	}

	public void setMessagereceiver(List<Message> messagereceiver) {
		Messagereceiver = messagereceiver;
	}

	public Satisfaction getSatisfactions() {
		return satisfactions;
	}

	public void setSatisfactions(Satisfaction satisfactions) {
		this.satisfactions = satisfactions;
	}

	public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

    /**
     * @return the accountNonLocked
     */
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    /**
     * @param accountNonLocked the accountNonLocked to set
     */
    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    /**
     * @return the failedAttempt
     */
    public int getFailedAttempt() {
        return failedAttempt;
    }

    /**
     * @param failedAttempt the failedAttempt to set
     */
    public void setFailedAttempt(int failedAttempt) {
        this.failedAttempt = failedAttempt;
    }

    /**
     * @return the lockTime
     */
    public Date getLockTime() {
        return lockTime;
    }

    /**
     * @param lockTime the lockTime to set
     */
    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }

    public String getSession_Id() {
        return Session_Id;
    }

    public void setSession_Id(String session_Id) {
        Session_Id = session_Id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    /**
     * @return the role
     */
    public Set<Dbo_Role> getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(Set<Dbo_Role> role) {
        this.role = role;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Dbo_User_Provider getDbo_User_Provider() {
        return dbo_User_Provider;
    }

    public void setDbo_User_Provider(Dbo_User_Provider dbo_User_Provider) {
        this.dbo_User_Provider = dbo_User_Provider;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getLastLoggedIn() {
        return lastLoggedIn;
    }

    public void setLastLoggedIn(Date lastLoggedIn) {
        this.lastLoggedIn = lastLoggedIn;
    }

    public Date getLastLoggedOut() {
        return lastLoggedOut;
    }

    public void setLastLoggedOut(Date lastLoggedOut) {
        this.lastLoggedOut = lastLoggedOut;
    }

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

    /*-----------------------****Constructors_Object****-------------------------------------*/
    public Dbo_User() {
        super();
    }

    public Dbo_User(Long id, String firstName, String lastName, boolean actif, Date date, String email,
                    String password) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.actif = actif;
        this.date = date;
        this.email = email;
        this.password = password;
    }

    /* Constructor of registration */
    public Dbo_User(@Size(min = 3, max = 30) String firstName, @Size(min = 3, max = 30) String lastName, boolean actif,
                    Date date, String email, String password, Set<Dbo_Role> role) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.actif = actif;
        this.date = date;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Dbo_User(String firstName, String lastName, boolean actif, String email, String password) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.actif = actif;
        this.email = email;
        this.password = password;
    }

    public Dbo_User(String email, String password) {
        super();
        this.password = password;
    }

    public Dbo_User(String email, String firstName, String lastName, boolean actif, Date date, String password) {
        // TODO Auto-generated constructor stub
        this.firstName = firstName;
        this.lastName = lastName;
        this.actif = actif;
        this.email = email;
        this.password = password;
    }


    public Dbo_User(Long id, String firstName, String lastName, boolean actif, Date date, String email, String password,
			Set<Dbo_Role> role, Date createdTime, Date lastLoggedIn, Date lastLoggedOut,
			Dbo_User_Provider dbo_User_Provider, String session_Id, boolean accountNonLocked, int failedAttempt,
			Date lockTime, Set<Event> events, String uploadDir, List<Message> messagesender,
			List<Message> messagereceiver, Satisfaction satisfactions) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.actif = actif;
		this.date = date;
		this.email = email;
		this.password = password;
		this.role = role;
		this.createdTime = createdTime;
		this.lastLoggedIn = lastLoggedIn;
		this.lastLoggedOut = lastLoggedOut;
		this.dbo_User_Provider = dbo_User_Provider;
		Session_Id = session_Id;
		this.accountNonLocked = accountNonLocked;
		this.failedAttempt = failedAttempt;
		this.lockTime = lockTime;
		this.events = events;
		this.uploadDir = uploadDir;
		Messagesender = messagesender;
		Messagereceiver = messagereceiver;
		this.satisfactions = satisfactions;
	}

	/*-----------------------****TO_String()****-------------------------------------*/
    @Override
    public String toString() {
        return "Dbo_User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", actif=" + actif
                + ", date=" + date + ", email=" + email + ", password=" + password + "]";
    }
    /*------------------------------------------------------------*/

}