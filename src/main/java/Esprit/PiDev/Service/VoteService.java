package Esprit.PiDev.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Esprit.PiDev.Entity.Dbo_User;
import Esprit.PiDev.Entity.ForumSubject;
import Esprit.PiDev.Entity.SpringVoteException;
import Esprit.PiDev.Entity.Vote;
import Esprit.PiDev.Repository.Forum_Subject_Repository;
import Esprit.PiDev.Repository.User_Repository;
import Esprit.PiDev.Repository.VoteRepository;

@Service
public class VoteService {
	@Autowired
	VoteRepository voteRepository;
	@Autowired
	Forum_Subject_Repository subjectRepository;
	@Autowired
	User_Repository userRepository;
	
	/*@Transactional
    public void vote(Vote vote, int idSubject, int idUser) {
        Subject post = subjectRepository.findById(idSubject).get();//postRepository.findById(voteDto.getPostId())
        
        User user = userRepository.findById(idUser).get();
        Optional<Vote> voteByPostAndUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, user);
        if (voteByPostAndUser.isPresent() &&
                voteByPostAndUser.get().getVoteType()
                        .equals(vote.getVoteType()/*voteDto.getVoteType()*//*)) {
            throw new SpringVoteException("You have already "
                    + vote.getVoteType() + "'d for this post");
        }
        if (vote.getVoteType().UPVOTE.equals(VoteType.UPVOTE)){
        //if (UPVOTE.equals(voteDto.getVoteType())) {
            post.setVoteCount(post.getVoteCount() + 1);
        } else {
            post.setVoteCount(post.getVoteCount() - 1);
        }
        voteRepository.save(vote);
        subjectRepository.save(post);
    }
*/
	public void addVote(Vote vote, long idUser, long idSubject){
		Dbo_User user = userRepository.findById(idUser).get();
		ForumSubject subject =  subjectRepository.findById(idSubject).get();
		
		List<Vote> votes = voteRepository.findAll();
		for(Vote votee : votes) {
			if(votee.getUser().getId() == idUser && votee.getSubject().getId() == idSubject && votee.getVoteType().equals(vote.getVoteType())){
				 throw new SpringVoteException("You have already "
		                    + vote.getVoteType() + "'d for this post");
			}
		}
		
		if(vote.getVoteType().equals(vote.getVoteType().UPVOTE)){
			subject.setVoteCount(subject.getVoteCount() + 1);
		} else {
			subject.setVoteCount(subject.getVoteCount() - 1);
		}
		
		vote.setSubject(subject);
		vote.setUser(user);
		
		voteRepository.save(vote);
		subjectRepository.save(subject);
	}

}
