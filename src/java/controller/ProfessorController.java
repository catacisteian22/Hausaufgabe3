import java.model.Professor;
import java.repository.ProfessorInMemoryRepo;

/**
 * @author sncam
 */
public class ProfessorController {
    private ProfessorInMemoryRepo repository;

    public ProfessorController(ProfessorInMemoryRepo professorRepo) {
        this.repository = professorRepo;
    }

    public Professor findById(long professorId){
        return this.repository.findOne(professorId);
    }

    public Professor updateProfessor(Professor professor) {
        return this.repository.update(professor);
    }
}
