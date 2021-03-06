package feedback;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.context.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RestController
public class FeedbackController {

	@Autowired
	private FeedbackRepo feedbackRepository;

	@RequestMapping(path = "/feedback", method = RequestMethod.POST)
	public Feedback createFeedback(@RequestParam(value="title") String title, @RequestParam(value="isHelpful") Boolean isHelpful,
	                               @RequestParam(value="comments") String comments) {
		Feedback existingFeedback = feedbackRepository.findByTitle(title.toLowerCase());

		if (existingFeedback != null){
			existingFeedback.addFeedback(isHelpful);
			existingFeedback.addComments(comments);
			feedbackRepository.save(existingFeedback);
			return existingFeedback;
		}
		else {
			Feedback newFeedback = new Feedback(title, isHelpful);
			newFeedback.setComments(comments);
			feedbackRepository.save(newFeedback);
			return newFeedback;
		}
	}

	@RequestMapping(value = "/{title}", method = RequestMethod.GET)
	public Feedback getFeedback(@PathVariable String title) {
		return feedbackRepository.findByTitle(title);
	}

	@RequestMapping(value= "/", method = RequestMethod.GET)
	public ArrayList<Feedback> index(){
		return (ArrayList<Feedback>) feedbackRepository.findAll();
	}
}