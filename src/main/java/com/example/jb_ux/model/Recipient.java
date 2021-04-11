package com.example.jb_ux.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "recipient")
public class Recipient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "TEMPLATE_ID", referencedColumnName = "ID", nullable=false)
    private Template template;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "DISPATCH_METHOD_NAME", referencedColumnName = "NAME", nullable=false)
    private DispatchMethod dispatchMethod;

    @Column(name = "VALUE", nullable = false)
    private String value;
}
