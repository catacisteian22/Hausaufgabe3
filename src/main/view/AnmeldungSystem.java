package main.view;

import main.controller.MainController;
import main.model.Kurs;
import main.model.Student;

import java.util.Scanner;

public class AnmeldungSystem {

    private final MainController mainController;

    public AnmeldungSystem(MainController mainController) {
        this.mainController = mainController;
    }

    public void startApplication() {
        this.showMenu();
        this.startConsole();
    }

    /**
     * drücken des Menüs
     */
    private void showMenu() {
        String builder = "Willkommen zu der App der Universität\n" +
                "Drücke: 1 um einen Studenten zu einem Kurs einzuschreiben\n" +
                "Drücke: 2 zeigt an alle Kurse an\n" +
                "Drücke: 3 zeigt alle Studenten für einen bestimmten Kurs an\n" +
                "Drücke: 4 zeigt alle Kurse mit freie Plätze\n" +
                "Drücke: 5 ein Professor will einen Kurs von seiner Liste zu löschen\n" +
                "Drücke: 0 um die App zu verlassen\n" +
                "Deine Auswahl: ";
        System.out.println(builder);
    }

    /**
     * run the methode of each case
     */
    private void startConsole() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String opt = scanner.nextLine();

            switch (opt) {

                case "1":
                    System.out.println("Bitte fügt die ID des Kurses ein: ");
                    String kursId = scanner.nextLine();
                    System.out.println("Bitte fügt die ID des Studenten ein: ");
                    String studentId = scanner.nextLine();

                    try {
                        boolean response = this.mainController.registerStudentToKurs(Long.parseLong(kursId), Long.parseLong(studentId));
                        if (response) System.out.println("Student hinzugefügt mit Erfolg!");
                        else System.out.println("Student war nicht hinzugefügt :(");
                    } catch (Exception e) {
                        System.out.println("Exception occurred:");
                        System.out.println(e.getMessage());
                    }
                    break;

                case "2":
                    Iterable<Kurs> kursList = this.mainController.getAllKurse();
                    for (Kurs kurs : kursList) {
                        System.out.println(kurs.getName() + " , freie Plätze = " + (kurs.getStudentsEnrolled().size() - kurs.getMaxEnrolled()) * (-1) + "\n");
                    }
                    break;

                case "3":
                    System.out.println("Bitte fügt die ID des Kurses ein: ");
                    kursId = scanner.nextLine();

                    try {
                        Iterable<Student> studentListInAKurs = this.mainController.getAllStudentsByKursId(Long.parseLong(kursId));
                        for (Student student : studentListInAKurs) {
                            System.out.println("ID des Studenten in diesem Kurs: " + student.getStudentId() + "\n");
                        }
                    } catch (Exception e) {
                        System.out.println("Exception occurred:");
                        System.out.println(e.getMessage());
                    }
                    break;

                case "4":
                    Iterable<Kurs> availableKurseList = this.mainController.getAllAvailableKurse();
                    System.out.println("Alle Kurse mit freie Plätze: " + availableKurseList + "\n");
                    break;

                case "5":
                    System.out.println("Bitte fügt die ID des Kurses ein: ");
                    kursId = scanner.nextLine();
                    System.out.println("Bitte fügt die ID des Professors ein: ");
                    String professorId = scanner.nextLine();

                    try {
                        boolean response = this.mainController.deleteKursFromProfessor(Long.parseLong(kursId), Long.parseLong(professorId));
                        if (response) System.out.println("Kurs gelöscht mit Erfolg!");
                        else System.out.println("Der Kurs war nicht gelöscht :(");
                    } catch (Exception e) {
                        System.out.println("Exception occurred:");
                        System.out.println(e.getMessage());
                    }
                    break;

                case "0":
                    System.out.println("Auf Wiedersehen!!!");
                    return;
            }
            this.showMenu();
        }
    }

}
