package main.model;

import java.util.List;

public class Kurs {

    public String name;
    public Person professor;
    public int maxEnrolled;
    public long kursId;
    public List<Student> studentsEnrolled;
    public int credits;

    public Kurs(String name, Person professor, int maxEnrolled, long kursId, List<Student> studentsEnrolled, int credits) {
        this.name = name;
        this.professor = professor;
        this.maxEnrolled = maxEnrolled;
        this.kursId = kursId;
        this.studentsEnrolled = studentsEnrolled;
        this.credits = credits;
    }

    /**
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name des Kurses
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Professor
     */
    public Person getProfessor() {
        return professor;
    }

    /**
     * @param professor Person, die der Kurs lehrt
     */
    public void setProfessor(Person professor) {
        this.professor = professor;
    }

    /**
     * @return maximale Anzahl von Studenten in der Kurs
     */
    public int getMaxEnrolled() {
        return maxEnrolled;
    }

    /**
     * @param maxEnrolled maximale Anzahl von Studenten in der Kurs
     */
    public void setMaxEnrolled(int maxEnrolled) {
        this.maxEnrolled = maxEnrolled;
    }

    /**
     * @return kursid
     */
    public long getKursId() {
        return kursId;
    }

    /**
     * @return Studenten, die eingeschrieben sind
     */
    public List<Student> getStudentsEnrolled() {
        return studentsEnrolled;
    }

    /**
     * @param studentsEnrolled, Liste von Studenten, die eingeschrieben sind
     */
    public void setStudentsEnrolled(List<Student> studentsEnrolled) {
        this.studentsEnrolled = studentsEnrolled;
    }

    /**
     * @return credits
     */
    public int getCredits() {
        return credits;
    }

    /**
     * @param credits, ECTS des Kurses
     */
    public void setCredits(int credits) {
        this.credits = credits;
    }

    /**
     * @return toString
     */
    @Override
    public String toString() {
        return "Kurs{" +
                "Name='" + name + '\'' +
                ", Professor=" + professor +
                ", maxEnrolled=" + maxEnrolled +
                ", studentsEnrolled=" + studentsEnrolled +
                ", ECTS=" + credits +
                '}';
    }
}
