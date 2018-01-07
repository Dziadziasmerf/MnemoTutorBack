package pl.corp.dziadzia.MnemoTutorBack.rest.results;

import lombok.Data;
import pl.corp.dziadzia.MnemoTutorBack.model.Result;

import java.util.Date;

@Data
class ResultPresentation {

    private Date date;
    private String discipline;
    private Integer score;
    private Long time;
    private String username;

    public ResultPresentation() {
    }

    ResultPresentation(Result result) {
        this.date = result.getDate();
        this.discipline = result.getDiscipline().getName();
        this.score = result.getScore();
        this.time = result.getTime();
        this.username = result.getUser().getUsername();
    }
}
