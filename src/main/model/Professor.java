package main.model;

import java.util.List;

public class Professor extends Person {

    public long professorId;
    public List<Kurs> kurse;

    public Professor(String lastName, String firstName, long professorId, List<Kurs> kurse) {
        super(lastName, firstName);
        this.professorId = professorId;
        this.kurse = kurse;
    }

    /**
     * @return professor id
     */
    public long getProfessorId() {
        return professorId;
    }

    /**
     * @return Kurse
     */
    public List<Kurs> getKurse() {
        return kurse;
    }

    /**
     * @param kurse Kurse gelehrt von dem Professor
     */
    public void setKurse(List<Kurs> kurse) {
        this.kurse = kurse;
    }

    /**
     * @return toString
     */
    @Override
    public String toString() {
        return "Professor{" +
                "Nachname='" + lastName + '\'' +
                ", Vorname='" + firstName + '\'' +
                '}';
    }
}