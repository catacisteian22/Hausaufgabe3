package java.controller;

import java.util.List;

import java.model.Kurs;
import java.model.Student;
import java.model.Teacher;

/**
 * @author sncam
 */
public class MainController {
    private CourseController courseController;
    private StudentController studentController;
    private TeacherController teacherController;


    public MainController(CourseController courseController, StudentController studentController, TeacherController teacherController) {
        this.courseController = courseController;
        this.studentController = studentController;
        this.teacherController = teacherController;
    }

    /**
     *
     * @param studentId id of the student
     * @param courseId  id of the course
     * @return the methode courseAddedToStudent or studentAddedToCourse
     */
    public boolean registerStudentToCourse(Long studentId, Long courseId){
        Student student = this.studentController.findStudentById(studentId);
        Kurs kurs = this.courseController.findCourseById(courseId);

        if(kurs.getStudentsEnrolled().size() == kurs.getMaxEnrolled())
            throw new RuntimeException("Course is full");
        if(student.getTotalCredit()+ kurs.getCredits() > 30)
            throw new RuntimeException("To many Credits");

        Boolean courseAddedToStudent = this.studentController.addCourseToStudent(studentId, kurs);
        Boolean studentAddedToCourse = this.courseController.addStudentToCourse(courseId,student);

        return courseAddedToStudent | studentAddedToCourse;
    }

    /**
     *
     * @return all the course
     */
    public Iterable<Kurs> getAllCourses(){
        return this.courseController.getAllCourses();
    }

    /**
     *
     * @param courseId id of the course
     * @return all the student of the course with the matching id
     */
    public Iterable<Student> getAllStudentsByCourseId(Long courseId){
        return this.courseController.findCourseById(courseId).getStudentsEnrolled();
    }

    /**
     *
     * @return all the available courses
     */
    public Iterable<Kurs> getAllAvailableCourses(){
        return this.courseController.getAvailableCourses();
    }

    /**
     *
     * @param courseName the name of the course
     * @param teacherId the id of the teacher in this course
     * @param maxEnrolled number of max student for this course
     * @param courseId id of this course
     * @param credits the credits of the course
     * @return the updated course
     */
    public boolean updateCourse(String courseName, long teacherId, int maxEnrolled, long courseId, int credits){
        Kurs existingKurs = this.courseController.findCourseById(courseId);
        Kurs kurs = new Kurs(courseName, this.teacherController.findById(teacherId), maxEnrolled, courseId, null ,credits);
        if(existingKurs.getCredits() != credits)
        {
            for(Student student: existingKurs.getStudentsEnrolled()){
                Student newStudent = new Student(student.getName(),student.getFirstName(),student.getStudentId(),student.getTotalCredit(),student.getEnrolledCourses());
                newStudent.getEnrolledCourses().removeIf(kurs1 -> kurs1.getCourseId()==courseId);
                newStudent.getEnrolledCourses().add(kurs);
                this.studentController.updateStudent(newStudent);
            }
        }
        return this.courseController.updateCourse(kurs) == null;
    }

    /**
     *
     * @param courseId id of the course
     * @param teacherId id of the teacher
     * @return true or false if the new teacher was updated or not
     */
    public boolean deleteCourseFromTeacher(long courseId, long teacherId){
        Teacher existingTeacher = this.teacherController.findById(teacherId);
        List<Kurs> newKursList = existingTeacher.getCourses();
        newKursList.removeIf(kurs1 -> kurs1.getCourseId()==courseId);

        Teacher newTeacher= new Teacher(existingTeacher.getName(), existingTeacher.getFirstName(), existingTeacher.getTeacherId(), newKursList);
        Kurs kurs = this.courseController.findCourseById(courseId);
        for(Student student: kurs.getStudentsEnrolled()){
            Student newStudent = new Student(student.getName(),student.getFirstName(),student.getStudentId(),student.getTotalCredit(),student.getEnrolledCourses());
            newStudent.getEnrolledCourses().removeIf(kurs1 -> kurs1.getCourseId()==courseId);
            this.studentController.updateStudent(newStudent);
        }
        this.courseController.emptyCourseStudentList(courseId);
        return this.teacherController.updateTeacher(newTeacher) == null;
    }

}
