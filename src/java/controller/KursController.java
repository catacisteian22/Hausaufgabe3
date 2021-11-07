package java.controller;

import java.util.ArrayList;
import java.util.List;

import com.company.domain.Course;
        import com.company.domain.Student;
        import com.company.repository.CourseInMemoryRepo;
        import java.util.ArrayList;
        import java.util.List;

/**
 * @author sncam
 */
public class KursController {
    private CourseInMemoryRepo repository;

    public CourseController(CourseInMemoryRepo courseInMemoryRepo){
        this.repository = courseInMemoryRepo;
    }

    /**
     *
     * @param courseId id of the course
     * @return the course with the same id
     */
    public Course findCourseById(Long courseId) {
        return this.repository.findOne(courseId);
    }

    /**
     *
     * @param courseId id of the course
     * @param student object student
     * @return true or false if the student was added to the course or not
     */
    public Boolean addStudentToCourse(Long courseId, Student student) {
        Course updatedCourse = this.repository.addStudentToCourse(courseId,student);
        return updatedCourse!=null;
    }

    /**
     *
     * @return all the course
     */
    public Iterable<Course> getAllCourses() {
        return this.repository.findAll();
    }

    /**
     *
     * @return all the course with available places
     */
    public Iterable<Course> getAvailableCourses() {
        Iterable<Course> courseList = this.repository.findAll();
        List<Course> availableCourse = new ArrayList<>();
        for (Course c:courseList) {
            if(c.getStudentsEnrolled().size() < c.getMaxEnrolled())
            {
                availableCourse.add(c);
            }
        }
        return availableCourse;
    }

    /**
     *
     * @param course object course
     * @return the updated course
     */
    public Course updateCourse(Course course) {
        return this.repository.update(course);
    }

    /**
     *  run the methode emptylist of the repo
     * @param courseId the id of the course
     */
    public void emptyCourseStudentList(Long courseId){
        this.repository.emptyCourseStudentList(courseId);
    }
}
