package com.example.jb_ux.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "template")
public class Template {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "TEMPLATE_STRING", nullable = false)
    private String templateString;

    @OneToMany(mappedBy = "template", cascade = {CascadeType.MERGE})
    private List<Recipient> recipients;

    @OneToMany(mappedBy = "template")
    private List<Schedule> schedules;
}
