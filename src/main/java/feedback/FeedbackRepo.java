package feedback;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FeedbackRepo extends CrudRepository<Feedback, Long> {
	@Query("select f from Feedback f where f.title = :title")
	Feedback findByTitle(@Param("title") String title);
}
