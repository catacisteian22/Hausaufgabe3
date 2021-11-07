package java.repository;

import java.model.Kurs;
import java.model.Student;
import java.model.Professor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sncam
 */
public class KursInMemoryRepo implements CrudRepository<Course>{
    private List<Course> courses;

    public CourseInMemoryRepo(List<Course> courses) {
        this.courses = courses;
    }

    /**
     * @param id -the id of the entity to be returned id must not be null
     * @return the entity with the specified id or null if there is no entity with the given id
     * @throws RuntimeException if the id is null or student list is empty
     */
    @Override
    public Course findOne(Long id) {

        if (courses.isEmpty())
        {
            throw new RuntimeException("Course list is empty");
        }
        if(id==null)
        {
            throw new RuntimeException("Id can't be null");
        }
        else
        {
            for (Course c : courses) {
                if (id == c.getCourseId()) {
                    return c;
                }
            }
        }
        return null;
    }

    /**
     * @return all entities
     */
    @Override
    public Iterable<Course> findAll() {
        return courses;
    }

    /**
     * @param entity entity must be not null
     * @return null if the given entity is saved, otherwise returns the entity (id already exists)
     */
    @Override
    public Course save(Course entity) {
        for(Course c:courses)
        {
            if(c.equals(entity))
            {
                return c;
            }
        }
        courses.add(entity);
        return null;

    }

    /**
     * @param entity entity must be not null
     * @return the removed entity or null if there is no entity with the given id
     * @throws RuntimeException if course list is empty
     */
    @Override
    public Course delete(Course entity) {

        if(courses.isEmpty())
        {
            throw new RuntimeException("Course list is empty");
        }
        else
        {
            for (Course c : courses)
            {
                if (c.equals(entity))
                {
                    courses.remove(entity);
                    return entity;
                }
            }
        }
        return null;
    }

    /**
     * @param entity entity must not be null
     * @return null if the entity is updated, otherwise returns the entity
     */
    @Override
    public Course update(Course entity) {

        for(Course course: courses) {
            if (course.getCourseId() == entity.getCourseId()) {
                course.setName(entity.getName());
                course.setTeacher(entity.getTeacher());
                course.setMaxEnrolled(entity.getMaxEnrolled());
                course.setCredits(entity.getCredits());
                return null;
            }
        }
        return entity;
    }

    /**
     *
     * @param courseId id of the course
     * @param student object student
     * @return the course with the student added or null if the id of the course was not found
     */

    public Course addStudentToCourse (long courseId, Student student){
        for (Course course: courses){
            if(courseId == course.getCourseId())
            {
                course.getStudentsEnrolled().add(student);
                return course;
            }
        }
        return null;
    }

    /**
     * set a new empty arraylist to the course
     * @param courseId id of the course
     */

    public void emptyCourseStudentList(Long courseId){
        for (Course course: courses){
            if(courseId == course.getCourseId())
            {
                course.setStudentsEnrolled(new ArrayList<>());
            }
        }
    }
}
