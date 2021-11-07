import com.company.domain.Teacher;
import com.company.repository.TeacherInMemoryRepo;

/**
 * @author sncam
 */
public class ProfessorController {
    private TeacherInMemoryRepo repository;

    public TeacherController(TeacherInMemoryRepo teacherRepo) {
        this.repository = teacherRepo;
    }

    public Teacher findById(long teacherId){
        return this.repository.findOne(teacherId);
    }

    public Teacher updateTeacher(Teacher teacher) {
        return this.repository.update(teacher);
    }
}
