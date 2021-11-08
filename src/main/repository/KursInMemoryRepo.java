package main.repository;

import main.model.Kurs;
import main.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sncam
 */
public class KursInMemoryRepo implements CrudRepository<Kurs> {

    private final List<Kurs> kurse;

    public KursInMemoryRepo(List<Kurs> kurse) {
        this.kurse = kurse;
    }

    /**
     * @param id -the id of the entity to be returned id must not be null
     * @return the entity with the specified id or null if there is no entity with the given id
     * @throws RuntimeException if the id is null or student list is empty
     */
    @Override
    public Kurs findOne(Long id) {

        if (kurse.isEmpty()) {
            throw new RuntimeException("Course list is empty");
        }
        if (id == null) {
            throw new RuntimeException("Id can't be null");
        } else {
            for (Kurs c : kurse) {
                if (id == c.getKursId()) {
                    return c;
                }
            }
        }
        return null;
    }

    /**
     * @return all entities
     */
    @Override
    public Iterable<Kurs> findAll() {
        return kurse;
    }

    /**
     * @param entity entity must be not null
     * @return null if the given entity is saved, otherwise returns the entity (id already exists)
     */
    @Override
    public Kurs save(Kurs entity) {
        for (Kurs c : kurse) {
            if (c.equals(entity)) {
                return c;
            }
        }
        kurse.add(entity);
        return null;

    }

    /**
     * @param entity entity must be not null
     * @return the removed entity or null if there is no entity with the given id
     * @throws RuntimeException if course list is empty
     */
    @Override
    public Kurs delete(Kurs entity) {

        if (kurse.isEmpty()) {
            throw new RuntimeException("Course list is empty");
        } else {
            for (Kurs c : kurse) {
                if (c.equals(entity)) {
                    kurse.remove(entity);
                    return entity;
                }
            }
        }
        return null;
    }

    /**
     * @param entity entity must not be null
     * @return null if the entity is updated, otherwise returns the entity
     */
    @Override
    public Kurs update(Kurs entity) {

        for (Kurs course : kurse) {
            if (course.getKursId() == entity.getKursId()) {
                course.setName(entity.getName());
                course.setTeacher(entity.getTeacher());
                course.setMaxEnrolled(entity.getMaxEnrolled());
                course.setCredits(entity.getCredits());
                return null;
            }
        }
        return entity;
    }

    /**
     * @param kursId  id of the course
     * @param student object student
     * @return the course with the student added or null if the id of the course was not found
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
     * set a new empty arraylist to the course
     *
     * @param kursId id of the course
     */

    public void emptyKursStudentList(Long kursId) {
        for (Kurs kurs : kurse) {
            if (kursId == kurs.getKursId()) {
                kurs.setStudentsEnrolled(new ArrayList<>());
            }
        }
    }
}
