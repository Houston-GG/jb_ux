package com.example.jb_ux.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
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

    public Template(String id, String templateString, List<Recipient> recipients) {
        this.id = id;
        this.templateString = templateString;
        this.recipients = recipients;
    }
}
