package main.controller;

import main.model.Kurs;
import main.model.Professor;
import main.model.Student;

import java.util.List;

/**
 * @author sncam
 */
public class MainController {
    private KursController kursController;
    private StudentController studentController;
    private ProfessorController professorController;


    public MainController(KursController kursController, StudentController studentController, ProfessorController professorController) {
        this.kursController = kursController;
        this.studentController = studentController;
        this.professorController = professorController;
    }

    /**
     * @param studentId id of the student
     * @param kursId    id of the course
     * @return the methode courseAddedToStudent or studentAddedToCourse
     */
    public boolean registerStudentToKurs(Long studentId, Long kursId) {
        Student student = this.studentController.findStudentById(studentId);
        Kurs kurs = this.kursController.findKursById(kursId);

        if (kurs.getStudentsEnrolled().size() == kurs.getMaxEnrolled())
            throw new RuntimeException("Course is full");
        if (student.getTotalCredit() + kurs.getCredits() > 30)
            throw new RuntimeException("To many Credits");

        Boolean kursAddedToStudent = this.studentController.addKursToStudent(studentId, kurs);
        Boolean studentAddedToKurs = this.kursController.addStudentToKurs(kursId, student);

        return kursAddedToStudent | studentAddedToKurs;
    }

    /**
     * @return all the course
     */
    public Iterable<Kurs> getAllKurse() {
        return this.kursController.getAllKurse();
    }

    /**
     * @param kursId id of the course
     * @return all the student of the course with the matching id
     */
    public Iterable<Student> getAllStudentsByKursId(Long kursId) {
        return this.kursController.findKursById(kursId).getStudentsEnrolled();
    }

    /**
     * @return all the available courses
     */
    public Iterable<Kurs> getAllAvailableKurse() {
        return this.kursController.getAvailableKurse();
    }

    /**
     * @param kursName    the name of the course
     * @param professorId the id of the teacher in this course
     * @param maxEnrolled number of max student for this course
     * @param kursId      id of this course
     * @param credits     the credits of the course
     * @return the updated course
     */
    public boolean updateKurs(String kursName, long professorId, int maxEnrolled, long kursId, int credits) {
        Kurs existingKurs = this.kursController.findKursById(kursId);
        Kurs kurs = new Kurs(kursName, this.professorController.findById(professorId), maxEnrolled, kursId, null, credits);
        if (existingKurs.getCredits() != credits) {
            for (Student student : existingKurs.getStudentsEnrolled()) {
                Student newStudent = new Student(student.getName(), student.getFirstName(), student.getStudentId(), student.getTotalCredit(), student.getEnrolledKurse());
                newStudent.getEnrolledKurse().removeIf(kurs1 -> kurs1.getKursId() == kursId);
                newStudent.getEnrolledKurse().add(kurs);
                this.studentController.updateStudent(newStudent);
            }
        }
        return this.kursController.updateKurs(kurs) == null;
    }

    /**
     * @param kursId      id of the course
     * @param professorId id of the teacher
     * @return true or false if the new teacher was updated or not
     */
    public boolean deleteKursFromProfessor(long kursId, long professorId) {
        Professor existingProfessor = this.professorController.findById(professorId);
        List<Kurs> newKursList = existingProfessor.getKurse();
        newKursList.removeIf(kurs1 -> kurs1.getKursId() == kursId);

        Professor newProfessor = new Professor(existingProfessor.getName(), existingProfessor.getFirstName(), existingProfessor.getProfessorId(), newKursList);
        Kurs kurs = this.kursController.findKursById(kursId);
        for (Student student : kurs.getStudentsEnrolled()) {
            Student newStudent = new Student(student.getName(), student.getFirstName(), student.getStudentId(), student.getTotalCredit(), student.getEnrolledKurse());
            newStudent.getEnrolledKurse().removeIf(kurs1 -> kurs1.getKursId() == kursId);
            this.studentController.updateStudent(newStudent);
        }
        this.kursController.emptyKursStudentList(kursId);
        return this.professorController.updateProfessor(newProfessor) == null;
    }

}
