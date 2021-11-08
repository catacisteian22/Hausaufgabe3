package main.controller;

import main.model.Kurs;
import main.model.Student;
import main.repository.KursInMemoryRepo;

import java.util.ArrayList;
import java.util.List;

public class KursController {

    private final KursInMemoryRepo repository;

    public KursController(KursInMemoryRepo kursInMemoryRepo) {
        this.repository = kursInMemoryRepo;
    }

    /**
     * @param kursId ID des Kurses
     * @return der Kurs mit derselbe Id
     */
    public Kurs findKursById(Long kursId) {
        return this.repository.findOne(kursId);
    }

    /**
     * @param kursId ID des Kurses
     * @param student Objekt Student
     * @return True oder False, ob der Student zu einem Kurs hinzugefügt war oder nicht
     */
    public Boolean addStudentToKurs(Long kursId, Student student) {
        Kurs updatedKurs = this.repository.addStudentToKurs(kursId, student);
        return updatedKurs != null;
    }

    /**
     * @return alle Kurse
     */
    public Iterable<Kurs> getAllKurse() {
        return this.repository.findAll();
    }

    /**
     * @return alle Kurse mit freie Plätze
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
     * @param kurs Objekt Kurs
     * @return die aktualisierte Kurse
     */
    public Kurs updateKurs(Kurs kurs) {
        return this.repository.update(kurs);
    }

    /**
     * lauf die Methode emptyList aus dem Repository an
     *
     * @param kursId ID des Kurses
     */
    public void emptyKursStudentList(Long kursId) {
        this.repository.emptyKursStudentList(kursId);
    }
}
