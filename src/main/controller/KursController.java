package main.controller;

import main.model.Kurs;
import main.model.Student;
import main.repository.KursInMemoryRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sncam
 */
public class KursController {
    private KursInMemoryRepo repository;

    public KursController(KursInMemoryRepo kursInMemoryRepo) {
        this.repository = kursInMemoryRepo;
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
        Kurs updatedKurs = this.repository.addStudentToKurs(kursId, student);
        return updatedKurs != null;
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
    public Iterable<Kurs> getAvailableKurse() {
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
     * @param kursId the id of the course
     */
    public void emptyKursStudentList(Long kursId) {
        this.repository.emptyKursStudentList(kursId);
    }
}
