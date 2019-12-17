package co.edu.unal.tutorshipback.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Time;
import java.util.*;
import java.sql.Date;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static java.sql.Date.valueOf;

@Entity
@Table(name = "tutorship")
@EntityListeners(AuditingEntityListener.class)
public class Tutorship {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idtutorship;

	@JsonFormat(pattern="dd-MM-yyyy")
    private Date date;

	@JsonFormat(pattern="HH:mm:ss")
    private Time init_Hour;

	private Long length;

	private Long tutor;

	private Long subject;

	private Long classroom;

	private int limit_Number;

	private int viability;


	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getInit_Hour() {
		return init_Hour;
	}

	public void setInit_Hour(Time init_Hour) {
		this.init_Hour = init_Hour;
	}

	public Long getLength() {
		return length;
	}

	public void setLength(Long length) {
		this.length = length;
	}

	public Long getTutor() {
		return tutor;
	}

	public void setTutor(Long tutor) {
		this.tutor = tutor;
	}

	public Long getSubject() {
		return subject;
	}

	public void setSubject(Long subject) {
		this.subject = subject;
	}

	public Long getClassroom() {
		return classroom;
	}

	public void setClassroom(Long classroom) {
		this.classroom = classroom;
	}

	public int getLimit_Number() {
		return limit_Number;
	}

	public void setLimit_Number(int limit_Number) {
		this.limit_Number = limit_Number;
	}

	public int getViability() {
		return viability;
	}

	public void setViability(int viability) {
		this.viability = viability;
	}
}
