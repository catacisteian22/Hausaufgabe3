package main.controller;

import main.model.Kurs;
import main.model.Professor;
import main.model.Student;

import java.util.List;

public class MainController {
    private final KursController kursController;
    private final StudentController studentController;
    private final ProfessorController professorController;


    public MainController(KursController kursController, StudentController studentController, ProfessorController professorController) {
        this.kursController = kursController;
        this.studentController = studentController;
        this.professorController = professorController;
    }

    /**
     * @param studentId ID des Studenten
     * @param kursId ID des Kurses
     * @return die Methode kursAddedToStudent oder studentAddedToKurs
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
     * @return alle Kurse
     */
    public Iterable<Kurs> getAllKurse() {
        return this.kursController.getAllKurse();
    }

    /**
     * @param kursId ID des Kurses
     * @return alle Studenten des Kurses mit übereinstimmender ID
     */
    public Iterable<Student> getAllStudentsByKursId(Long kursId) {
        return this.kursController.findKursById(kursId).getStudentsEnrolled();
    }

    /**
     * @return alle Kurse mit freie Plätze
     */
    public Iterable<Kurs> getAllAvailableKurse() {
        return this.kursController.getAvailableKurse();
    }

    /**
     * @param kursName    Name des Kurses
     * @param professorId ID des Professors für diesen Kurs
     * @param maxEnrolled maximale Anzahl von Studenten für diesen Kurs
     * @param kursId      ID des Kurses
     * @param credits     die ECTS des Kurses
     * @return der aktualisierte Kurs
     */
    public boolean updateKurs(String kursName, long professorId, int maxEnrolled, long kursId, int credits) {
        Kurs existingKurs = this.kursController.findKursById(kursId);
        Kurs kurs = new Kurs(kursName, this.professorController.findById(professorId), maxEnrolled, kursId, null, credits);
        if (existingKurs.getCredits() != credits) {
            for (Student student : existingKurs.getStudentsEnrolled()) {
                Student newStudent = new Student(student.getLastName(), student.getFirstName(), student.getStudentId(), student.getTotalCredit(), student.getEnrolledKurse());
                newStudent.getEnrolledKurse().removeIf(kurs1 -> kurs1.getKursId() == kursId);
                newStudent.getEnrolledKurse().add(kurs);
                this.studentController.updateStudent(newStudent);
            }
        }
        return this.kursController.updateKurs(kurs) == null;
    }

    /**
     * @param kursId      ID des Kurses
     * @param professorId ID des Professors
     * @return True or False, ob der neue Professor war aktualisiert oder nicht
     */
    public boolean deleteKursFromProfessor(long kursId, long professorId) {
        Professor existingProfessor = this.professorController.findById(professorId);
        List<Kurs> newKursList = existingProfessor.getKurse();
        newKursList.removeIf(kurs1 -> kurs1.getKursId() == kursId);

        Professor newProfessor = new Professor(existingProfessor.getLastName(), existingProfessor.getFirstName(), existingProfessor.getProfessorId(), newKursList);
        Kurs kurs = this.kursController.findKursById(kursId);
        for (Student student : kurs.getStudentsEnrolled()) {
            Student newStudent = new Student(student.getLastName(), student.getFirstName(), student.getStudentId(), student.getTotalCredit(), student.getEnrolledKurse());
            newStudent.getEnrolledKurse().removeIf(kurs1 -> kurs1.getKursId() == kursId);
            this.studentController.updateStudent(newStudent);
        }
        this.kursController.emptyKursStudentList(kursId);
        return this.professorController.updateProfessor(newProfessor) == null;
    }

}
