package pl.corp.dziadzia.MnemoTutorBack.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "results")
public class Result {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "discipline")
    @Enumerated(EnumType.ORDINAL)
    private Discipline discipline;

    @Column(name = "result_date")
    private Date date;

    @Column(name = "time")
    private Long time;

    @Column(name = "score")
    private Integer score;
}
