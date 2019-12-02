package co.edu.unal.tutorshipback.controller;

import co.edu.unal.tutorshipback.model.Enrollment;
import co.edu.unal.tutorshipback.repository.EnrollmentRepository;
import co.edu.unal.tutorshipback.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EnrollmentController {
    @Autowired
    EnrollmentRepository enrollmentRepository;

    @GetMapping("/enrollment")
    public List<Enrollment> getAllEnrollment() {
        return enrollmentRepository.findAll();
    }

    @PostMapping("/enrollment")
    public Enrollment createEnrollment(@Valid @RequestBody Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    @GetMapping("/enrollment/{id}")
    public Enrollment getEnrollmentById(@PathVariable(value = "id") Long enrollmentID) {
        return enrollmentRepository.findById(enrollmentID)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment", "id", enrollmentID));
    }

    @PutMapping("/enrollment/{id}")
    public Enrollment updateEnrollment(@PathVariable(value = "id") Long enrollmentID,
                                     @Valid @RequestBody Enrollment enrollmentDetails) {

        Enrollment enrollment = enrollmentRepository.findById(enrollmentID)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment", "id", enrollmentID));

        Enrollment updatedEnrollment = enrollmentRepository.save(enrollment);

        //all Attributes are FK :v
        return updatedEnrollment;
    }

    @DeleteMapping("enrollment/{id}")
    public ResponseEntity<?> deleteEnrollment(@PathVariable(value = "id") Long enrollmentID) {

        Enrollment enrollment  = enrollmentRepository.findById(enrollmentID)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment", "id", enrollmentID));

        enrollmentRepository.delete(enrollment);
        return ResponseEntity.ok().build();
    }

}
