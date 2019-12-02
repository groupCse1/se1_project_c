package co.edu.unal.tutorshipback.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "enrollment")
@EntityListeners(AuditingEntityListener.class)
public class Enrollment {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "idAssisst")
    private User idAssisst;

    @ManyToOne
    @JoinColumn(name = "idTutorship")
    private Tutorship idTutorship;

    @Lob
    @Column(name="interesting_Topic", length=100)
    private String interesting_Topic;
}
