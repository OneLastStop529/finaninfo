package com.finaninfo.config;

import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    protected Long id;

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
