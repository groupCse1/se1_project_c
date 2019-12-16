package co.edu.unal.tutorshipback.model;

import javax.persistence.Id;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * Created by javergarav on 20/11/19.
 */
@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @Column(name="user", length=50, nullable=false, unique=true)
    private String user;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    private String faculty;

    @NotBlank
    private String career;
    
    private long cellphone;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public long getCellphone() {
        return cellphone;
    }

    public void setCellphone(long cellphone) {
        this.cellphone = cellphone;
    }
}
