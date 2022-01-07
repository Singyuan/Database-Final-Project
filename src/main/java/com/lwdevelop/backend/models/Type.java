package com.lwdevelop.backend.models;

import javax.persistence.*;

@Entity
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EType name;

    public Type() {
    }

    public Type(EType name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EType getName() {
        return name;
    }

    public void setName(EType name) {
        this.name = name;
    }
}
