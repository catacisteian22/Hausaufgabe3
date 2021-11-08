package main.repository;


import main.model.Professor;

import java.util.List;

/**
 * @author sncam
 */
public class ProfessorInMemoryRepo implements CrudRepository<Professor> {
    private List<Professor> professor;

    public ProfessorInMemoryRepo(List<Professor> professor) {
        this.professor = professor;
    }

    /**
     * @param id -the id of the entity to be returned id must not be null
     * @return the entity with the specified id or null if there is no entity with the given id
     * @throws RuntimeException if the id is null or teacher list is empty
     */
    @Override
    public Professor findOne(Long id) {

        if (professor.isEmpty()) {
            throw new RuntimeException("Teacher list is empty");
        }
        if (id == null) {
            throw new RuntimeException("Id can't be null");
        } else {
            for (Professor t : professor) {
                if (id == t.getProfessorId()) {
                    return t;
                }
            }
        }
        return null;
    }

    /**
     * @return all entities
     */
    @Override
    public Iterable<Professor> findAll() {

        return professor;
    }

    /**
     * @param entity entity must be not null
     * @return null if the given entity is saved, otherwise returns the entity (id already exists)
     */
    @Override
    public Professor save(Professor entity) {
        for (Professor t : professor) {
            if (t.equals(entity)) {
                return t;
            }
        }
        professor.add(entity);
        return null;

    }

    /**
     * @param entity entity must be not null
     * @return the removed entity or null if there is no entity with the given id
     * @throws RuntimeException if teacher list is empty
     */
    @Override
    public Professor delete(Professor entity) {

        if (professor.isEmpty()) {
            throw new RuntimeException("Teacher list is empty");
        } else {
            for (Professor t : professor) {

                if (t.equals(entity)) {
                    professor.remove(entity);
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
    public Professor update(Professor entity) {

        for (Professor professor : professor) {
            if (professor.getProfessorId() == entity.getProfessorId()) {
                professor.setName(entity.getName());
                professor.setFirstName(entity.getFirstName());
                professor.setKurse(entity.getKurse());
                return null;
            }
        }
        return entity;
    }
}

