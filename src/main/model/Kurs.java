package main.model;

import java.util.List;

/**
 * @author sncam
 */
public class Kurs {

    public String name;
    public Person teacher;
    public int maxEnrolled;
    public long kursId;
    public List<Student> studentsEnrolled;
    public int credits;

    public Kurs(String name, Person teacher, int maxEnrolled, long kursId, List<Student> studentsEnrolled, int credits) {
        this.name = name;
        this.teacher = teacher;
        this.maxEnrolled = maxEnrolled;
        this.kursId = kursId;
        this.studentsEnrolled = studentsEnrolled;
        this.credits = credits;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name of the course
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return teacher
     */
    public Person getTeacher() {
        return teacher;
    }

    /**
     * @param teacher, person who teach the course
     */
    public void setTeacher(Person teacher) {
        this.teacher = teacher;
    }

    /**
     * @return number max of student in the course
     */
    public int getMaxEnrolled() {
        return maxEnrolled;
    }

    /**
     * @param maxEnrolled number max of student in the course
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
     * @return students that are enrolled
     */
    public List<Student> getStudentsEnrolled() {
        return studentsEnrolled;
    }

    /**
     * @param studentsEnrolled, list of students that are enrolled
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
     * @param credits, vale of the course
     */
    public void setCredits(int credits) {
        this.credits = credits;
    }


    /**
     * @return tostring
     */
    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", teacher=" + teacher +
                ", maxEnrolled=" + maxEnrolled +
                ", studentsEnrolled=" + studentsEnrolled +
                ", credits=" + credits +
                '}';
    }
}
