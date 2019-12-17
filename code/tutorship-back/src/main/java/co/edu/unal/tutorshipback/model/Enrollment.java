package co.edu.unal.tutorshipback.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "enrollment")
@EntityListeners(AuditingEntityListener.class)
public class Enrollment {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private Long idAssisst;

    private Long idTutorship;

    private String interesting_Topic;

    public long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getInteresting_Topic() {
        return interesting_Topic;
    }

    public void setInteresting_Topic(String interesting_Topic) {
        this.interesting_Topic = interesting_Topic;
    }
}
