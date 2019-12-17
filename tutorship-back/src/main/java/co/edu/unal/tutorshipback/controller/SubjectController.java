package co.edu.unal.tutorshipback.controller;

import co.edu.unal.tutorshipback.model.Subject;
import co.edu.unal.tutorshipback.repository.SubjectRepository;
import co.edu.unal.tutorshipback.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SubjectController {
    @Autowired
    SubjectRepository subjectRepository;

    @GetMapping("/subject")
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @PostMapping("/subject")
    public Subject createSubject(@Valid @RequestBody Subject subject) {
        return subjectRepository.save(subject);
    }
    
    @GetMapping("/subject/find/{id}")
    public Subject getSubjectByName(@PathVariable(value = "id") String subjectID) {
        return subjectRepository.findSubjectByName(subjectID);
    }
    
    @GetMapping("/subject/{id}")
    public Subject getSubjectById(@PathVariable(value = "id") Long subjectID) {
        return subjectRepository.findById(subjectID)
                .orElseThrow(() -> new ResourceNotFoundException("Subject", "id", subjectID));
    }

    @PutMapping("/subject/{id}")
    public Subject updateSubject(@PathVariable(value = "id") Long subjectID,
                                       @Valid @RequestBody Subject subjectDetails) {

        Subject subject = subjectRepository.findById(subjectID)
                .orElseThrow(() -> new ResourceNotFoundException("Subject", "id", subjectID));


        subject.setCode(subjectDetails.getCode());
        subject.setSubject_name(subjectDetails.getSubject_name());
        subject.setSubject_faculty(subjectDetails.getSubject_faculty());
        Subject updatedSubject = subjectRepository.save(subject);
        return updatedSubject;
    }

    @DeleteMapping("subject/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable(value = "id") Long subjectID) {

        Subject subject  = subjectRepository.findById(subjectID)
                .orElseThrow(() -> new ResourceNotFoundException("Subject", "id", subjectID));

        subjectRepository.delete(subject);
        return ResponseEntity.ok().build();
    }
}
