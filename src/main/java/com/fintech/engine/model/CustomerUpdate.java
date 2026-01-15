package com.fintech.engine.model;

import com.fintech.engine.annotations.*;

@FileSource(delimiter=",")
public class CustomerUpdate {

    @Column(index=0, name="name")
    @NotNull(message="Name required")
    private String name;

    @Column(index=1, name="email")
    @Regex(pattern=".+@.+\\..+", message="Invalid email")
    private String email;

    @Column(index=2, name="age")
    @Range(min=1, max=120, message="Invalid age")
    private int age;
}

