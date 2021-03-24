package Esprit.PiDev.Service;

import java.util.ArrayList;
import java.util.List;

import Esprit.PiDev.InterfaceService.Satisfaction_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import Esprit.PiDev.Entity.Answer_Satisfaction;
import Esprit.PiDev.Entity.Dbo_User;
import Esprit.PiDev.Entity.ERole;
import Esprit.PiDev.Entity.Question_Satisfaction;
import Esprit.PiDev.Entity.Review;
import Esprit.PiDev.Entity.Satisfaction;
import Esprit.PiDev.Entity.RequestApiForm.MessageResponse;
import Esprit.PiDev.Repository.Answer_Repository;
import Esprit.PiDev.Repository.Question_Satisfaction_Repository;
import Esprit.PiDev.Repository.Satisfaction_Repository;
import Esprit.PiDev.Repository.User_Repository;

@Service
public class Satisfaction_Service_Impl implements Satisfaction_Service {
	@Autowired
	Satisfaction_Repository sat_rep;
	@Autowired
	User_Repository us_rep;
	@Autowired
	Question_Satisfaction_Repository quest_rep;

	@Autowired
	Answer_Repository answer_Repository;

	@Override
	public ResponseEntity<?> addSatisfaction(Satisfaction satisfaction, Long iduser) {
		// TODO Auto-generated method stub
		Dbo_User user = us_rep.findById(iduser).orElse(null);

		if (user.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN))) {
			

				return ResponseEntity.ok(new MessageResponse("success" + sat_rep.save(satisfaction)));
	
			}
			else{
				return ResponseEntity.ok(new MessageResponse("athhh"));
	
			}


	}

	@Override
	public ResponseEntity<?> deleteSatisfaction(Satisfaction satisfaction) {
		// TODO Auto-generated method stub
		if (satisfaction != null) {
			sat_rep.delete(satisfaction);
			return ResponseEntity.ok(new MessageResponse("satisfaction est supprimé "));
		} else {
			return ResponseEntity.ok(new MessageResponse("satisfaction n'existe pas"));
		}
	}

	@Override
	public ResponseEntity<?> deleteSatisfactionById(Long id) {
		// TODO Auto-generated method stub

		return ResponseEntity.ok(new MessageResponse("success" + sat_rep.findById(id).get()));

	}

	@Override
	public Satisfaction findSatisfactionById(Long id) {
		// TODO Auto-generated method stub
		return sat_rep.findById(id).get();
	}

	@Override
	public List<Satisfaction> retrieveAllSatisfactions() {
		// TODO Auto-generated method stub
		return (List<Satisfaction>) sat_rep.findAll();
	}

	@Override
	public Satisfaction retrieveSatisfaction(Satisfaction satisfaction) {
		// TODO Auto-generated method stub
		return sat_rep.findById(satisfaction.getId()).get();
	}

	@Override
	public ResponseEntity<?> saveOrUpdate(Satisfaction satisfaction, Long user_id, Long sat) {
		// TODO Auto-generated method stub
		Dbo_User dbo_User = us_rep.findById(user_id).orElse(null);
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN))) {
			Satisfaction Satisfaction_To_Update = sat_rep.findById(sat).orElse(null);
			if (satisfaction != null) {

				Satisfaction_To_Update.setName(satisfaction.getName());

				return ResponseEntity.ok(new MessageResponse("satisfaction est bien modifiée "));
			} else {
				return ResponseEntity.ok(new MessageResponse("satisfaction n'existe pas"));
			}

		}
		return ResponseEntity.ok(new MessageResponse("athhh"));

	}

	@Override
	public ResponseEntity<?> Affecter_Answer_Question_Satisfaction(Long user_id, Long idsat, List<Review> reviews) {
		// TODO Auto-generated method stub
		Dbo_User dbo_User = us_rep.findById(user_id).orElse(null);
		Satisfaction satisfaction = sat_rep.findById(idsat).orElse(null);
		List<Answer_Satisfaction> list_answers = new ArrayList<>();
		int i = 0;
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_PARENT))) {
			for (Question_Satisfaction question : satisfaction.getQuestions()) {
				Answer_Satisfaction answer = new Answer_Satisfaction();
				answer.setQuestion(question);
				answer.setReview(reviews.get(i));
				answer.setUser(dbo_User);
				answer_Repository.save(answer);
				list_answers.add(answer);
				i++;

			}

			return ResponseEntity.ok(new MessageResponse("" + list_answers));

		}
		{
			return ResponseEntity.ok(new MessageResponse("user n'est pas parent"));

		}

	}

	@Override
	public ResponseEntity<?> getAllQuestionsbySatisfaction(Long user_id, Long idsat) {
		// TODO Auto-generated method stub
		Dbo_User dbo_User = us_rep.findById(user_id).orElse(null);
		Satisfaction satisfaction = sat_rep.findById(idsat).orElse(null);
		List<Question_Satisfaction> list_questions = new ArrayList<>();

		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN)))

		{
			for (Question_Satisfaction question : satisfaction.getQuestions()) {

				list_questions.add(question);
			}

			return ResponseEntity.ok(new MessageResponse("" + list_questions));

		}
		{
			return ResponseEntity.ok(new MessageResponse("user n'est pas admin"));

		}

	}

	@Override
	public ResponseEntity<?> StatistiqueAnswer_QuetionSatisfactionbUSer(Long user_id, Long idusersat, String namesat) 
	{
		// TODO Auto-generated method stub
		Dbo_User dbo_User = us_rep.findById(user_id).orElse(null);
		Dbo_User usersat = us_rep.findById(idusersat).orElse(null);

		Satisfaction sat = new Satisfaction();
		for (Satisfaction satisfaction : usersat.getSatisfactions()) {
			if (satisfaction.getName().equals(namesat)) {
				sat = satisfaction;
			}
		}

		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN))) {

			String response = "";

			for (Question_Satisfaction question : sat.getQuestions()) {

				float count_good = (answer_Repository.countasnwergood(question.getId().intValue())
						/ answer_Repository.countasnwer(question.getId().intValue())) * 100;
				float count_bad = (answer_Repository.countasnwerbad(question.getId().intValue())
						/ answer_Repository.countasnwer(question.getId().intValue())) * 100;
				float count_medium = (answer_Repository.countasnwerbad(question.getId().intValue())
						/ answer_Repository.countasnwer(question.getId().intValue())) * 100;

				response = response + "question est  " + question.getQuestion_Sat()
						+ "  les nombres Reviews GOOD  sont   " + count_good + "  % "
						+ "    les nombres  Reviews BAD  sont " + count_bad + " %"
						+ "    les nombres  Reviews MEDIUM  sont " + count_medium;
			}

			return ResponseEntity.ok(new MessageResponse(response));

		}

		return ResponseEntity.ok(new MessageResponse("user n'est pas parent"));

	}

	@Override
	public ResponseEntity<?> AffecterQuetions_Satisfaction_Satisfaction(Satisfaction satisfaction, Long iduser) {
		// TODO Auto-generated method stub
		Dbo_User dbo_User = us_rep.findById(iduser).orElse(null);

		List<Satisfaction> allusersatsafctions = new ArrayList<>();

		List<Question_Satisfaction> alluserquetions = new ArrayList<>();
		alluserquetions = (List<Question_Satisfaction>) quest_rep.findAll();

		for (Satisfaction sat : dbo_User.getSatisfactions()) {
			allusersatsafctions.add(sat);

		}
		for (Question_Satisfaction question_Satisfaction : alluserquetions) {
			if (!allusersatsafctions.contains(question_Satisfaction)) {
				Satisfaction sat = new Satisfaction();
				sat.getUsers().add(dbo_User);
				sat.setName(satisfaction.getName());
				sat.getQuestions().add(question_Satisfaction);

			}
		}

		return null;
	}

}
