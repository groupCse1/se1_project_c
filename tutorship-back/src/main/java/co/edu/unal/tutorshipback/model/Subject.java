package co.edu.unal.tutorshipback.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "subjects")
@EntityListeners(AuditingEntityListener.class)
public class Subject {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long code;

	@NotBlank
	private String subject_name;

	@NotBlank
	private String subject_faculty;

	@OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
	private Set<Tutorship> tutorships;

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public String getSubject_name() {
		return subject_name;
	}

	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}

	public String getSubject_faculty() {
		return subject_faculty;
	}

	public void setSubject_faculty(String subject_faculty) {
		this.subject_faculty = subject_faculty;
	}
	
	
}
