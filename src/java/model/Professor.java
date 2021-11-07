package java.model;

import java.util.List;

/**
 * @author sncam
 */
public class Professor extends Person {

    public long teacherId;
    public List<Kurs> cours;

    public Professor(String name, String firstName, long teacherId, List<Kurs> cours) {
        super(name, firstName);
        this.teacherId = teacherId;
        this.cours = cours;
    }


    /*
    getter and setter
    */

    /**
     * @return teacher id
     */
    public long getTeacherId() {
        return teacherId;
    }

    /**
     * @param teacherId, id of the teacher
     */
    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * @return courses
     */
    public List<Kurs> getCourses() {
        return cours;
    }

    /**
     * @param cours courses teched by the teacher
     */
    public void setCourses(List<Kurs> cours) {
        this.cours = cours;
    }

    /**
     *
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