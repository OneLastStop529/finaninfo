package com.finaninfo.config;

import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;

    @Version
    @Ignore
    private Long version;



    public BaseEntity() {
        id=null;

    }

    public Long getId() {
        return id;
    }
}
