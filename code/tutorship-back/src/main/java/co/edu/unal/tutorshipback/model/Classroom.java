package co.edu.unal.tutorshipback.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
@Entity
@Table(name = "classroom")
@EntityListeners(AuditingEntityListener.class)
public class Classroom {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@NotBlank
	private String building;

	private int number;

	private int capacity;


	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
    
    
}