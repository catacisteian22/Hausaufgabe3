package java.view;

import java.controller.MainController;
import java.model.Kurs;
import java.model.Student;

import java.util.Scanner;

public class AnmeldungSystem {

    private MainController mainController;

    public AnmeldungSystem(MainController mainController) {
        this.mainController = mainController;
    }

    public void startApplication(){
        this.showMenu();
        this.startConsole();
    }

    /**
     * print the menu
     */

    private void showMenu(){
        StringBuilder builder = new StringBuilder();
        builder.append("Welcome to the University's Application\n")
                .append("Press: 1 for add student to course\n")
                .append("Press: 2 for get all courses\n")
                .append("Press: 3 for get all student of a course\n")
                .append("Press: 4 for get all course with free places\n")
                .append("Press: 5 for a teacher to delete a course in his list\n")
                .append("Press: 0 to exit application\n")
                .append("Your choise: ");
        System.out.println(builder.toString());
    }

    /**
     * run the methode of each case
     */
    private void startConsole(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            String opt = scanner.nextLine();

            switch (opt) {


                case "1":
                    System.out.println("Please insert course id");
                    String courseId = scanner.nextLine();
                    System.out.println("Please insert student id");
                    String studentId = scanner.nextLine();

                    try {
                        boolean response = this.mainController.registerStudentToCourse(Long.parseLong(courseId), Long.parseLong(studentId));
                        if (response) System.out.println("Student added successfully");
                        else System.out.println("Failed to add student to course");
                    } catch (Exception e) {
                        System.out.println("Exception occurred:");
                        System.out.println(e.getMessage());
                    }

                    break;

                case "2":
                    Iterable<Course> courseList = this.mainController.getAllCourses();
                    for (Course course : courseList) {
                        System.out.println(course.getName() + " , available places = " + String.valueOf((course.getStudentsEnrolled().size() - course.getMaxEnrolled())*(-1)) + "\n");
                    }
                    break;

                case "3":
                    System.out.println("Please insert course id");
                    courseId = scanner.nextLine();

                    try {
                        Iterable<Student> studentListInACourse = this.mainController.getAllStudentsByCourseId(Long.parseLong(courseId));
                        for (Student student : studentListInACourse) {
                            System.out.println("Id of the student in this course: " + student.getStudentId() + "\n");
                        }
                    } catch (Exception e) {
                        System.out.println("Exception occurred:");
                        System.out.println(e.getMessage());
                    }
                    break;

                case "4":
                    Iterable<Course> availableCourseList = this.mainController.getAllAvailableCourses();
                    System.out.println("all the available courses: " + availableCourseList + "\n");
                    break;

                case "5":
                    System.out.println("Please insert course id");
                    courseId = scanner.nextLine();
                    System.out.println("Please insert teacher id");
                    String teacherId = scanner.nextLine();

                    try {
                        boolean response = this.mainController.deleteCourseFromTeacher(Long.parseLong(courseId), Long.parseLong(teacherId));
                        if (response) System.out.println("Course deleted successfully");
                        else System.out.println("Failed to delete course");
                    } catch (Exception e) {
                        System.out.println("Exception occurred:");
                        System.out.println(e.getMessage());
                    }
                    break;

                case "0":
                    System.out.println("Good-Bye!!");
                    return;
            }
            this.showMenu();
        }
    }

}
