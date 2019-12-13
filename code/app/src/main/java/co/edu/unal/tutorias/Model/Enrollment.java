package co.edu.unal.tutorias.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;



public class Enrollment {
	private long id;
	
	private Long idAssisst;
	
	private Long idTutorship;
	
	private String interestin_Topic;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getIdAssisst() {
		return idAssisst;
	}

	public void setIdAssisst(Long idAssisst) {
		this.idAssisst = idAssisst;
	}

	public Long getIdTutorship() {
		return idTutorship;
	}

	public void setIdTutorship(Long idTutorship) {
		this.idTutorship = idTutorship;
	}

	public String getInterestin_Topic() {
		return interestin_Topic;
	}

	public void setInterestin_Topic(String interestin_Topic) {
		this.interestin_Topic = interestin_Topic;
	}

	public Enrollment(long id, Long idAssisst, Long idTutorship, String interestin_Topic) {
		super();
		this.id = id;
		this.idAssisst = idAssisst;
		this.idTutorship = idTutorship;
		this.interestin_Topic = interestin_Topic;
	}
	
}
