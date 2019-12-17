package co.edu.unal.tutorshipback.controller;

import co.edu.unal.tutorshipback.model.Tutorship;
import co.edu.unal.tutorshipback.repository.TutorshipRepository;
import co.edu.unal.tutorshipback.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TutorshipController {
    @Autowired
    TutorshipRepository tutorshipRepository;

    @GetMapping("/tutorship")
    public List<Tutorship> getAllTutorship() {
        return tutorshipRepository.findAll();
    }

    @PostMapping("/tutorship")
    public Tutorship createTutorship(@Valid @RequestBody Tutorship tutorship) {
        return tutorshipRepository.save(tutorship);
    }

    @GetMapping("/tutorship/{id}")
    public Tutorship getTutorshipById(@PathVariable(value = "id") Long tutorshipID) {
        return tutorshipRepository.findById(tutorshipID)
                .orElseThrow(() -> new ResourceNotFoundException("Tutorship", "id", tutorshipID));
    }

    @PutMapping("/tutorship/{id}")
    public Tutorship updateTutorship(@PathVariable(value = "id") Long tutorshipID,
                                       @Valid @RequestBody Tutorship tutorshipDetails) {

        Tutorship tutorship = tutorshipRepository.findById(tutorshipID)
                .orElseThrow(() -> new ResourceNotFoundException("Tutorship", "id", tutorshipID));


        tutorship.setDate(tutorshipDetails.getDate());
        tutorship.setViability(tutorshipDetails.getViability());
        tutorship.setInit_Hour(tutorshipDetails.getInit_Hour());
        tutorship.setLength(tutorshipDetails.getLength());
        tutorship.setLimit_Number(tutorshipDetails.getLimit_Number());
        tutorship.setSubject(tutorshipDetails.getSubject());
        tutorship.setClassroom(tutorshipDetails.getClassroom());
        tutorship.setTutor(tutorshipDetails.getTutor());
        Tutorship updateTutorship = tutorshipRepository.save(tutorship);
        return  updateTutorship;
    }

    @DeleteMapping("tutorship/{id}")
    public ResponseEntity<?> deleteTutorship(@PathVariable(value = "id") Long tutorshipID) {

        Tutorship tutorship  = tutorshipRepository.findById(tutorshipID)
                .orElseThrow(() -> new ResourceNotFoundException("Tutorship", "id", tutorshipID));

        tutorshipRepository.delete(tutorship);
        return ResponseEntity.ok().build();
    }
}
