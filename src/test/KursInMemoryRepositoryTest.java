package test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import main.controller.KursController;
import main.controller.MainController;
import main.controller.StudentController;
import main.controller.ProfessorController;
import main.model.Kurs;
import main.model.Student;
import main.model.Professor;
import main.repository.KursInMemoryRepo;
import main.repository.ProfessorInMemoryRepo;
import main.repository.StudentInMemoryRepo;
import org.junit.jupiter.api.Test;

import java.util.List;

class KursInMemoryRepositoryTest {

    @org.junit.jupiter.api.Test
    void findOne() {

        Kurs c1 = new Kurs("C1", null, 100, 1, null, 6);
        Kurs c2 = new Kurs("C2", null, 2, 2, null, 25);
        Kurs c3 = new Kurs("C3", null, 50, 3, null, 4);
        KursInMemoryRepo kursInMemoryRepo = new KursInMemoryRepo(Arrays.asList(c1, c2, c3));

        Kurs kurs = kursInMemoryRepo.findOne(Long.parseLong("1"));
        assertNotNull(kurs);
    }

    @Test
    void findAll() {

        Kurs c1 = new Kurs("C1", null, 100, 1, null, 6);
        Kurs c2 = new Kurs("C2", null, 2, 2, null, 25);
        Kurs c3 = new Kurs("C3", null, 50, 3, null, 4);
        KursInMemoryRepo kursInMemoryRepo = new KursInMemoryRepo(Arrays.asList(c1, c2, c3));

        List<Kurs> kursList = (List<Kurs>) kursInMemoryRepo.findAll();
        assertEquals(3, kursList.size());
    }

    @Test
    void save() {
        KursInMemoryRepo kursInMemoryRepo = new KursInMemoryRepo(new ArrayList<>());

        Kurs c4 = new Kurs("C4", null, 60, 4, null, 12);
        kursInMemoryRepo.save(c4);

        List<Kurs> kursList = (List<Kurs>) kursInMemoryRepo.findAll();
        assertEquals(1, kursList.size());
    }

    @Test
    void delete() {
        Kurs c1 = new Kurs("C1", null, 100, 1, null, 6);
        Kurs c2 = new Kurs("C2", null, 2, 2, null, 25);
        Kurs c3 = new Kurs("C3", null, 50, 3, null, 4);
        KursInMemoryRepo kursInMemoryRepo = new KursInMemoryRepo(new ArrayList<>());
        kursInMemoryRepo.save(c1);
        kursInMemoryRepo.save(c2);
        kursInMemoryRepo.save(c3);

        List<Kurs> kursList = (List<Kurs>) kursInMemoryRepo.findAll();
        assertEquals(3, kursList.size());

        kursInMemoryRepo.delete(c2);
        assertEquals(2, kursList.size());
    }

    @Test
    void update() {
        Kurs c1 = new Kurs("C1", null, 100, 1, null, 6);
        KursInMemoryRepo kursInMemoryRepo = new KursInMemoryRepo(List.of(c1));
        Kurs c2 = new Kurs("C2", null, 2, 1, null, 25);

        kursInMemoryRepo.update(c2);
        assertEquals("C2", kursInMemoryRepo.findOne(Long.parseLong("1")).getName());
    }

    @Test
    void addStudentToCourse() {

        List<Student> listStudentsKurse1 = new ArrayList<>();
        List<Student> listStudentsKurse2 = new ArrayList<>();
        List<Student> listStudentsKurse3 = new ArrayList<>();

        List<Kurs> listKurseProfessor1 = new ArrayList<>();
        List<Kurs> listKurseProfessor2 = new ArrayList<>();
        List<Kurs> listKurseProfessor3 = new ArrayList<>();

        List<Kurs> listKurseStudent1 = new ArrayList<>();
        List<Kurs> listKurseStudent2 = new ArrayList<>();
        List<Kurs> listKurseStudent3 = new ArrayList<>();

        Student s1 = new Student("S1", "SF1", 1, 0, null);
        Student s2 = new Student("S2", "SF2", 2, 0, null);
        Student s3 = new Student("S3", "SF3", 3, 0, null);

        Professor t1 = new Professor("T1", "TF1", 1, null);
        Professor t2 = new Professor("T2", "TF2", 2, null);
        Professor t3 = new Professor("T3", "TF3", 3, null);

        Kurs c1 = new Kurs("C1", t1, 100, 1, null, 6);
        Kurs c2 = new Kurs("C2", t2, 2, 2, null, 25);
        Kurs c3 = new Kurs("C3", t3, 50, 3, null, 4);

        listKurseStudent1.add(c1);
        listKurseStudent1.add(c3);
        listKurseStudent2.add(c2);
        listKurseStudent2.add(c3);
        listKurseStudent3.add(c1);

        s1.setEnrolledKurse(listKurseStudent1);
        for (Kurs kurs : listKurseStudent1)
            s1.setTotalCredit(s1.getTotalCredit() + kurs.getCredits());

        s2.setEnrolledKurse(listKurseStudent2);
        for (Kurs kurs : listKurseStudent2)
            s2.setTotalCredit(s2.getTotalCredit() + kurs.getCredits());

        s3.setEnrolledKurse(listKurseStudent3);
        for (Kurs kurs : listKurseStudent3)
            s3.setTotalCredit(s3.getTotalCredit() + kurs.getCredits());

        listKurseProfessor1.add(c1);
        listKurseProfessor2.add(c2);
        listKurseProfessor3.add(c3);

        t1.setKurse(listKurseProfessor1);
        t2.setKurse(listKurseProfessor2);
        t3.setKurse(listKurseProfessor3);

        listStudentsKurse1.add(s1);
        listStudentsKurse1.add(s2);

        listStudentsKurse2.add(s1);

        listStudentsKurse3.add(s2);
        listStudentsKurse3.add(s3);

        c1.setStudentsEnrolled(listStudentsKurse1);
        c2.setStudentsEnrolled(listStudentsKurse2);
        c3.setStudentsEnrolled(listStudentsKurse3);

        List<Kurs> kursList = new ArrayList<>(Arrays.asList(c1, c2, c3));
        List<Student> studentList = new ArrayList<>(Arrays.asList(s1, s2, s3));
        List<Professor> professorList = new ArrayList<>(Arrays.asList(t1, t2, t3));

        KursInMemoryRepo kursRepo = new KursInMemoryRepo(kursList);
        ProfessorInMemoryRepo professorRepo = new ProfessorInMemoryRepo(professorList);
        StudentInMemoryRepo studentRepo = new StudentInMemoryRepo(studentList);

        KursController kursController = new KursController(kursRepo);
        ProfessorController professorController = new ProfessorController(professorRepo);
        StudentController studentController = new StudentController(studentRepo);

        MainController mainController = new MainController(kursController, studentController, professorController);
        mainController.registerStudentToKurs(Long.parseLong("3"), (Long.parseLong("1")));

        List<Student> newStudentList = (List<Student>) mainController.getAllStudentsByKursId(Long.parseLong("1"));
        assertEquals(3, newStudentList.size());
    }
}