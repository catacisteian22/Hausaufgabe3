package main.repository;


import main.model.Professor;

import java.util.List;

public class ProfessorInMemoryRepo implements CrudRepository<Professor> {

    private final List<Professor> professor;

    public ProfessorInMemoryRepo(List<Professor> professor) {
        this.professor = professor;
    }

    /**
     * @param id -die ID der Entity, die zurückgegeben sein muss(muss nicht NULL sein)
     * @return die Entity mit der spezifizierten ID oder NULL (ob keine Entity mit dieser ID existiert)
     * @throws RuntimeException, ob die ID NULL ist oder die Liste der Professoren leer is
     */
    @Override
    public Professor findOne(Long id) {

        if (professor.isEmpty()) {
            throw new RuntimeException("Error! Liste der Professoren ist leer!");
        }
        if (id == null) {
            throw new RuntimeException("Error! ID darf nicht NULL sein!");
        } else {
            for (Professor p : professor) {
                if (id == p.getProfessorId()) {
                    return p;
                }
            }
        }
        return null;
    }

    /**
     * @return alle Entities
     */
    @Override
    public Iterable<Professor> findAll() {

        return professor;
    }

    /**
     * @param entity Entity muss nicht NULL sein
     * @return NULL, ob die gegebene Entity gespeichert war, andernfalls gibt die Entity zurück(ID existiert schon)
     */
    @Override
    public Professor save(Professor entity) {
        for (Professor p : professor) {
            if (p.equals(entity)) {
                return p;
            }
        }
        professor.add(entity);
        return null;

    }

    /**
     * @param entity Entity muss nicht NULL sein
     * @return die entfernte Entity oder NULL, ob keine Entity mit der gegebene ID existiert
     * @throws RuntimeException, ob die Liste der Professoren leer ist
     */
    @Override
    public Professor delete(Professor entity) {

        if (professor.isEmpty()) {
            throw new RuntimeException("Error! Liste der Professoren ist leer!");
        } else {
            for (Professor p : professor) {

                if (p.equals(entity)) {
                    professor.remove(entity);
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
    public Professor update(Professor entity) {

        for (Professor professor : professor) {
            if (professor.getProfessorId() == entity.getProfessorId()) {
                professor.setLastName(entity.getLastName());
                professor.setFirstName(entity.getFirstName());
                professor.setKurse(entity.getKurse());
                return null;
            }
        }
        return entity;
    }
}
