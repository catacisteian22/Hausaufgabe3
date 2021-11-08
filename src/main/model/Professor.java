package main.model;

import java.util.List;

/**
 * @author sncam
 */
public class Professor extends Person {

    public long professorId;
    public List<Kurs> kurse;

    public Professor(String lastName, String firstName, long professorId, List<Kurs> kurse) {
        super(lastName, firstName);
        this.professorId = professorId;
        this.kurse = kurse;
    }

    /**
     * @return teacher id
     */
    public long getProfessorId() {
        return professorId;
    }

    /**
     * @return courses
     */
    public List<Kurs> getKurse() {
        return kurse;
    }

    /**
     * @param kurse courses taught by the teacher
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
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}