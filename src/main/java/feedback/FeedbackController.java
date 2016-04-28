package feedback;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.context.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class FeedbackController {
	private final AtomicLong counter = new AtomicLong();

	@Autowired
	private FeedbackRepo feedbackRepository;

	@RequestMapping(path = "/feedback", method = RequestMethod.POST)
	public Feedback feedback(@RequestParam(value="title") String title, @RequestParam(value="isHelpful") Boolean isHelpful) {
		Feedback newFeedback = new Feedback(counter.incrementAndGet(), title, isHelpful);
		this.feedbackRepository.save(newFeedback);
		return newFeedback;
	}

	@RequestMapping(value = "/{title}", method = RequestMethod.GET)
	Feedback getFeedback(@PathVariable String title) {
		return this.feedbackRepository.findByTitle(title);
	}

}