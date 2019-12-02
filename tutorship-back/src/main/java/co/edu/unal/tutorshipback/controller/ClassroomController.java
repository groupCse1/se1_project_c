package co.edu.unal.tutorshipback.controller;

import co.edu.unal.tutorshipback.model.Classroom;
import co.edu.unal.tutorshipback.repository.ClassroomRepository;
import co.edu.unal.tutorshipback.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ClassroomController {
    @Autowired
    ClassroomRepository classroomRepository;

    @GetMapping("/classrooms")
    public List<Classroom> getAllClassrooms() {
        return classroomRepository.findAll();
    }

    @PostMapping("/classrooms")
    public Classroom createClassroom(@Valid @RequestBody Classroom classroom) {
        return classroomRepository.save(classroom);
    }

    @GetMapping("/classrooms/{id}")
    public Classroom getClassroomById(@PathVariable(value = "id") Long classroomID) {
        return classroomRepository.findById(classroomID)
                .orElseThrow(() -> new ResourceNotFoundException("Classroom", "id", classroomID));
    }

    @PutMapping("/classrooms/{id}")
    public Classroom updateClassroom(@PathVariable(value = "id") Long classroomID,
                           @Valid @RequestBody Classroom classroomDetails) {

        Classroom classroom = classroomRepository.findById(classroomID)
                .orElseThrow(() -> new ResourceNotFoundException("Classroom", "id", classroomID));

        classroom.setBuilding(classroomDetails.getBuilding());
        classroom.setNumber(classroomDetails.getNumber());
        classroom.setCapacity(classroomDetails.getCapacity());
        Classroom updatedClassroom = classroomRepository.save(classroom);
        return updatedClassroom;
    }

    @DeleteMapping("/classrooms/{id}")
    public ResponseEntity<?> deleteClassroom(@PathVariable(value = "id") Long classroomID) {

        Classroom classroom  = classroomRepository.findById(classroomID)
                .orElseThrow(() -> new ResourceNotFoundException("Classroom", "id", classroomID));

        classroomRepository.delete(classroom);
        return ResponseEntity.ok().build();
    }
}
