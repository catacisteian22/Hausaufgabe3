package main.model;

import java.util.List;

/**
 * @author sncam
 */
public class Professor extends Person {

    public long professorId;
    public List<Kurs> kurse;

    public Professor(String name, String firstName, long professorId, List<Kurs> kurse) {
        super(name, firstName);
        this.professorId = professorId;
        this.kurse = kurse;
    }


    /*
    getter and setter
    */

    /**
     * @return teacher id
     */
    public long getProfessorId() {
        return professorId;
    }

    /**
     * @param professorId, id of the teacher
     */
    public void setProfessorId(long professorId) {
        this.professorId = professorId;
    }

    /**
     * @return courses
     */
    public List<Kurs> getKurse() {
        return kurse;
    }

    /**
     * @param kurse courses teched by the teacher
     */
    public void setKurse(List<Kurs> kurse) {
        this.kurse = kurse;
    }

    /**
     * @return tostring
     */
    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}