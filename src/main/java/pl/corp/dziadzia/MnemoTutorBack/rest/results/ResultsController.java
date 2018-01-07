package pl.corp.dziadzia.MnemoTutorBack.rest.results;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.corp.dziadzia.MnemoTutorBack.model.Discipline;
import pl.corp.dziadzia.MnemoTutorBack.model.Result;
import pl.corp.dziadzia.MnemoTutorBack.model.User;
import pl.corp.dziadzia.MnemoTutorBack.repo.ResultRepository;
import pl.corp.dziadzia.MnemoTutorBack.repo.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/results")
public class ResultsController {

    private static final Logger logger = LoggerFactory.getLogger(RestController.class);

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ResultPresentation>> getResults(@RequestParam("username") String username, @RequestParam(value = "discipline", required = false) String discipline) {
        User user = userRepository.findUserByUsername(username);
        logger.error(discipline);
        List<Result> rawResults = discipline != null && !discipline.isEmpty() ? resultRepository.findResultsByUserAndDiscipline(user, Discipline.getDisciplineByName(discipline)) : resultRepository.findResultsByUser(user);
        List<ResultPresentation> results = rawResults
                .stream()
                .map(ResultPresentation::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addResult(@RequestBody ResultPresentation resultPresentation) {
        User user = userRepository.findUserByUsername(resultPresentation.getUsername());

        Result result = new Result();
        result.setDate(new Date());
        result.setDiscipline(Discipline.getDisciplineByName(resultPresentation.getDiscipline()));
        result.setTime(resultPresentation.getTime());
        result.setScore(resultPresentation.getScore());
        result.setUser(user);

        resultRepository.save(result);
        return new ResponseEntity(HttpStatus.CREATED);
    }

}
