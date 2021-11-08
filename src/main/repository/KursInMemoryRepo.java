package main.repository;

import main.model.Kurs;
import main.model.Student;

import java.util.ArrayList;
import java.util.List;

public class KursInMemoryRepo implements CrudRepository<Kurs> {

    private final List<Kurs> kurse;

    public KursInMemoryRepo(List<Kurs> kurse) {
        this.kurse = kurse;
    }

    /**
     * @param id -die ID der Entity, die zurückgegeben sein muss(muss nicht NULL sein)
     * @return die Entity mit der spezifizierten ID oder NULL (ob keine Entity mit dieser ID existiert)
     * @throws RuntimeException, ob die ID NULL ist oder die Liste der Kurse leer ist
     */
    @Override
    public Kurs findOne(Long id) {

        if (kurse.isEmpty()) {
            throw new RuntimeException("Error! Liste der Kurse ist leer!");
        }
        if (id == null) {
            throw new RuntimeException("Error! ID darf nicht NULL sein");
        } else {
            for (Kurs k : kurse) {
                if (id == k.getKursId()) {
                    return k;
                }
            }
        }
        return null;
    }

    /**
     * @return alle Entities
     */
    @Override
    public Iterable<Kurs> findAll() {
        return kurse;
    }

    /**
     * @param entity Entity muss nicht NULL sein
     * @return NULL, ob die gegebene Entity gespeichert war, andernfalls gibt die Entity zurück(ID existiert schon)
     */
    @Override
    public Kurs save(Kurs entity) {
        for (Kurs k : kurse) {
            if (k.equals(entity)) {
                return k;
            }
        }
        kurse.add(entity);
        return null;

    }

    /**
     * @param entity Entity muss nicht NULL sein
     * @return die entfernte Entity oder NULL, ob keine Entity mit der gegebene ID existiert
     * @throws RuntimeException, ob die Liste der Kurse leer ist
     */
    @Override
    public Kurs delete(Kurs entity) {

        if (kurse.isEmpty()) {
            throw new RuntimeException("Error! Liste der Kurse ist leer!");
        } else {
            for (Kurs k : kurse) {
                if (k.equals(entity)) {
                    kurse.remove(entity);
                    return entity;
                }
            }
        }
        return null;
    }

    /**
     * @param entity Entity muss nicht NULL sein
     * @return NULL, ob die Entity aktualisiert ist, andernfalls gibt die Entity zurück
     */
    @Override
    public Kurs update(Kurs entity) {

        for (Kurs course : kurse) {
            if (course.getKursId() == entity.getKursId()) {
                course.setName(entity.getName());
                course.setProfessor(entity.getProfessor());
                course.setMaxEnrolled(entity.getMaxEnrolled());
                course.setCredits(entity.getCredits());
                return null;
            }
        }
        return entity;
    }

    /**
     * @param kursId  ID des Kurses
     * @param student Objekt Student
     * @return der Kurs mit dem Studenten hinzugefügt oder NULL, ob die ID des Kurses nicht gefunden war
     */

    public Kurs addStudentToKurs(long kursId, Student student) {
        for (Kurs kurs : kurse) {
            if (kursId == kurs.getKursId()) {
                kurs.getStudentsEnrolled().add(student);
                return kurs;
            }
        }
        return null;
    }

    /**
     * Stell eine neue leere ArrayList für den Kurs
     *
     * @param kursId ID des Kurses
     */

    public void emptyKursStudentList(Long kursId) {
        for (Kurs kurs : kurse) {
            if (kursId == kurs.getKursId()) {
                kurs.setStudentsEnrolled(new ArrayList<>());
            }
        }
    }
}
