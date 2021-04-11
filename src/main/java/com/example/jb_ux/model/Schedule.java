package com.example.jb_ux.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "TEMPLATE_ID", referencedColumnName = "ID", nullable=false)
    private Template template;

    @Column(name = "PERIODICITY", nullable = false)
    private Long periodicity;

    @Column(name = "TRIGGER_TIME", nullable = false)
    private LocalTime triggerTime;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "schedule")
    private List<SavedValue> savedValues;
}
