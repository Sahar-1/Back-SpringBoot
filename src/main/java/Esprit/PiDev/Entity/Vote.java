package Esprit.PiDev.Entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Vote {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	    private VoteType voteType;
	    @ManyToOne(fetch = FetchType.LAZY)
	    private ForumSubject subject;
	    @ManyToOne(fetch = FetchType.LAZY)
	    private Dbo_User user;
	        
		public Vote() {
			super();
		}

		
		
		public Vote(int id, VoteType voteType, ForumSubject subject, Dbo_User user) {
			super();
			this.id = id;
			this.voteType = voteType;
			this.subject = subject;
			this.user = user;
		}



		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public VoteType getVoteType() {
			return voteType;
		}

		public void setVoteType(VoteType voteType) {
			this.voteType = voteType;
		}



		public ForumSubject getSubject() {
			return subject;
		}



		public void setSubject(ForumSubject subject) {
			this.subject = subject;
		}



		public Dbo_User getUser() {
			return user;
		}



		public void setUser(Dbo_User user) {
			this.user = user;
		}

	
	    
		
}
