package feedback;

import javax.persistence.*;

@Entity
public class Feedback {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private long id;

	@Column(name="title", nullable=false)
	private String title;

	@Column(name="positiveCount")
	private Integer positiveCount;

	@Column(name="negativeCount")
	private Integer negativeCount;

	protected Feedback() {}

	public Feedback(long id, String title, Boolean isHelpful){
		this.id = id;
		this.title = title;
		if (isHelpful){
			positiveCount = 1;
		} else {
			negativeCount = 1;
		}
	}

	public String getTitle(){
		return title;
	}

	public long getId() {
		return id;
	}

	public Integer getPositiveCount() {
		return positiveCount;
	}

	public Integer getNegativeCount() {
		return negativeCount;
	}

	public void setPositiveCount(Integer count) {
		this.positiveCount = count;
	}

	public void setNegativeCount(Integer count) {
		this.negativeCount = count;
	}

	public void setTitle(String title){
		this.title = title;
	}
	public void addPositiveFeedback(){
		positiveCount++;
	}

	public void addNegativeFeedback(){
		negativeCount++;
	}
}