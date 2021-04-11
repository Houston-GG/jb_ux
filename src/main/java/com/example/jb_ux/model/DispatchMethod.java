package com.example.jb_ux.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "dispatch_method")
public class DispatchMethod {

    @Id
    @Column(name="NAME")
    private String name;

    @Column(name="DESCRIPTION", nullable = false)
    private String description;

    public DispatchMethod(String name) {
        this.name = name;
    }
}
