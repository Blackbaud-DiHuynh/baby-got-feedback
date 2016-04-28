package feedback;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.context.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

@RestController
public class FeedbackController {

	@Autowired
	private FeedbackRepo feedbackRepository;

	@RequestMapping(path = "/feedback", method = RequestMethod.POST)
	public Feedback createFeedback(@RequestParam(value="title") String title, @RequestParam(value="isHelpful") Boolean isHelpful) {
		Feedback existingFeedback = feedbackRepository.findByTitle(title);

		if (existingFeedback != null){
			existingFeedback.addFeedback(isHelpful);
			feedbackRepository.save(existingFeedback);
			return existingFeedback;
		}
		else {
			Feedback newFeedback = new Feedback(title, isHelpful);
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