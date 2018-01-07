package pl.corp.dziadzia.MnemoTutorBack.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.corp.dziadzia.MnemoTutorBack.model.Discipline;
import pl.corp.dziadzia.MnemoTutorBack.model.Result;
import pl.corp.dziadzia.MnemoTutorBack.model.User;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result,Integer> {

    List<Result> findResultsByUser(User user);

    List<Result> findResultsByUserAndDiscipline(User user, Discipline discipline);
}
