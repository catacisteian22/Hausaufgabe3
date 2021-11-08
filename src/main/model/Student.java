package main.model;

import java.util.List;

/**
 * @author sncam
 */
public class Student extends Person {

    public long studentId;
    public int totalCredit;
    public List<Kurs> enrolledKurse;

    public Student(String lastName, String firstName, long studentId, int totalCredit, List<Kurs> enrolledKurse) {
        super(lastName, firstName);
        this.studentId = studentId;
        this.totalCredit = totalCredit;
        this.enrolledKurse = enrolledKurse;
    }

    /**
     * @return studentId
     */
    public long getStudentId() {
        return studentId;
    }

    /**
     * @return totalCredit
     */
    public int getTotalCredit() {
        return totalCredit;
    }

    /**
     * @param totalCredit credits of the student
     */
    public void setTotalCredit(int totalCredit) {
        this.totalCredit = totalCredit;
    }

    /**
     * @return enrolledCourses
     */
    public List<Kurs> getEnrolledKurse() {
        return enrolledKurse;
    }

    /**
     * @param enrolledKurse courses where the student goes
     */
    public void setEnrolledKurse(List<Kurs> enrolledKurse) {
        this.enrolledKurse = enrolledKurse;
    }

    /**
     * @return tostring
     */
    @Override
    public String toString() {
        return "Student{" +
                "name='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", studentId=" + studentId +
                ", totalCredit=" + totalCredit +
                '}';
    }
}