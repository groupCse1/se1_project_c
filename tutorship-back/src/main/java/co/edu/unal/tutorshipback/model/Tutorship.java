package co.edu.unal.tutorshipback.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Date;
import java.sql.Time;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tutorships")
@EntityListeners(AuditingEntityListener.class)
public class Tutorship {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idtutorship;

	@NotBlank
	private Date date;

	@NotBlank
	private Time init_Hour;

	@NotBlank
	private Time length;

	@NotBlank
	private int limit_Number;

	@NotBlank
	private int viability;

	@ManyToOne
	@JoinColumn(name="tutor")
	private User tutor;

	@OneToMany(mappedBy = "idTutorship", cascade = CascadeType.ALL)
	private Set<Enrollment> enrollments;

	@ManyToOne
	@JoinColumn(name="subject")
	private Subject subject;

	@ManyToOne
	@JoinColumn(name="classroom")
	private Classroom classroom;
	
	public long getIdTutorship() {
		return idtutorship;
	}
	public void setIdTutorship(long idtutorship) {
		this.idtutorship=idtutorship;
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getViability() {
		return viability;
	}
	public void setViability(int viability) {
		this.viability = viability;
	}
	public Time getInit_Hour() {
		return init_Hour;
	}
	public void setInit_Hour(Time init_Hour) {
		this.init_Hour = init_Hour;
	}
	public Time getLength() {
		return length;
	}
	public void setLength(Time length) {
		this.length = length;
	}
	public int getLimit_Number() {
		return limit_Number;
	}
	public void setLimit_Number(int limit_Number) {
		this.limit_Number = limit_Number;
	}
	public User getTutor() {
		return tutor;
	}
}
