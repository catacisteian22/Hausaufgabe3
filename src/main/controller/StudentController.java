package main.controller;

import main.model.Kurs;
import main.model.Student;
import main.repository.StudentInMemoryRepo;

public class StudentController {

    private final StudentInMemoryRepo repository;

    public StudentController(StudentInMemoryRepo studentRepo) {
        this.repository = studentRepo;
    }

    /**
     * @param studentId ID des Studenten
     * @return der Student mit dieselbe ID
     */
    public Student findStudentById(Long studentId) {
        return this.repository.findOne(studentId);
    }

    /**
     * @param studentId ID des Studenten
     * @param kurs      Objekt Kurs
     * @return True oder False, ob der Kurs war yu den Studenten hinzugefügt
     */
    public Boolean addKursToStudent(Long studentId, Kurs kurs) {
        Student updatedStudent = this.repository.addKursToStudent(studentId, kurs);
        return updatedStudent != null;
    }

    /**
     * @param student Objekt Student
     */
    public void updateStudent(Student student) {
        this.repository.update(student);
    }
}
