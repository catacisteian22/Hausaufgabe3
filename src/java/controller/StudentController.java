package java.controller;

import com.domain.Course;
import com.domain.Student;
import com.repository.StudentInMemoryRepo;

/**
 * @author sncam
 */
public class StudentController {
    private StudentInMemoryRepo repository;

    public StudentController(StudentInMemoryRepo studentRepo){
        this.repository = studentRepo;
    }

    /**
     *
     * @param studentId id of the student
     * @return the student with the same id
     */
    public Student findStudentById(Long studentId){
        return this.repository.findOne(studentId);
    }

    /**
     *
     * @param studentId id of the student
     * @param course object course
     * @return true or false if the course was added to the student or not
     */
    public Boolean addCourseToStudent(Long studentId, Course course) {
        Student updatedStudent = this.repository.addCourseToStudent(studentId,course);
        return updatedStudent!=null;
    }

    /**
     *
     * @param student object student
     * @return the updated student
     */
    public Student updateStudent(Student student) {
        return this.repository.update(student);
    }
}
