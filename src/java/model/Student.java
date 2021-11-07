package java.model;
import java.util.List;

/**
 * @author sncam
 */
public class Student extends Person {

    public long studentId;
    public int totalCredit;
    public List<Kurs> enrolledCours;

    public Student(String name, String firstName, long studentId, int totalCredit, List<Kurs> enrolledCours) {
        super(name, firstName);
        this.studentId = studentId;
        this.totalCredit = totalCredit;
        this.enrolledCours = enrolledCours;
    }

    /*
    getter and setter
    */

    /**
     * @return studentId
     */
    public long getStudentId() {
        return studentId;
    }

    /**
     * @param studentId the id of the student
     */
    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    /**
     * @return totalCredit
     */
    public int getTotalCredit() {
        return totalCredit;
    }

    /**
     *
     * @param totalCredit credits of the student
     */
    public void setTotalCredit(int totalCredit) {
        this.totalCredit = totalCredit;
    }

    /**
     * @return enrolledCourses
     */
    public List<Kurs> getEnrolledCourses() {
        return enrolledCours;
    }

    /**
     * @param enrolledCours courses where the student goes
     */
    public void setEnrolledCourses(List<Kurs> enrolledCours) {
        this.enrolledCours = enrolledCours;
    }

    /**
     *
     * @return tostring
     */
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", studentId=" + studentId +
                ", totalCredit=" + totalCredit +
                '}';
    }
}