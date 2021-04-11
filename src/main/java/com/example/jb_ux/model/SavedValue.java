package com.example.jb_ux.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "saved_value", uniqueConstraints = @UniqueConstraint(columnNames = {"TEMPLATE_ID", "SCHEDULE_ID", "KEY"}))
public class SavedValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "TEMPLATE_ID", referencedColumnName = "ID", nullable=false)
    private Template template;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "SCHEDULE_ID", referencedColumnName = "ID", nullable=false)
    private Schedule schedule;

    @Column(name = "KEY", nullable = false)
    private String key;

    @Column(name = "VALUE", nullable = false)
    private String value;
}
