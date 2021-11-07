package java.controller;

import java.util.ArrayList;
import java.util.List;

import java.model.Kurs;
import java.model.Student;
import java.repository.KursInMemoryRepo;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sncam
 */
public class KursController {
    private KursInMemoryRepo repository;

    public KursController(KursInMemoryRepo kursInMemoryRepo) {
        this.repository = KursInMemoryRepo;
    }

    /**
     * @param kursId id of the course
     * @return the course with the same id
     */
    public Kurs findKursById(Long kursId) {
        return this.repository.findOne(kursId);
    }

    /**
     * @param kursId  id of the course
     * @param student object student
     * @return true or false if the student was added to the course or not
     */
    public Boolean addStudentToKurs(Long kursId, Student student) {
        Course updatedCourse = this.repository.addStudentToCourse(kursId, student);
        return updatedCourse != null;
    }

    /**
     * @return all the course
     */
    public Iterable<Kurs> getAllKurse() {
        return this.repository.findAll();
    }

    /**
     * @return all the course with available places
     */
    public Iterable<Kurs> getAvailableKurs() {
        Iterable<Kurs> kursList = this.repository.findAll();
        List<Kurs> availableKurs = new ArrayList<>();
        for (Kurs c : kursList) {
            if (c.getStudentsEnrolled().size() < c.getMaxEnrolled()) {
                availableKurs.add(c);
            }
        }
        return availableKurs;
    }

    /**
     * @param kurs object course
     * @return the updated course
     */
    public Kurs updateKurs(Kurs kurs) {
        return this.repository.update(kurs);
    }

    /**
     * run the methode emptylist of the repo
     *
     * @param courseId the id of the course
     */
    public void emptyKursStudentenList(Long kursId) {
        this.repository.emptyKursStudentenList(kursId);
    }
}
