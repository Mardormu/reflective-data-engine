package com.fintech.engine.model;

import com.fintech.engine.annotations.*;

@FileSource(delimiter="\\|")
public class TransactionLog {

    @Column(index=0, name="id")
    @NotNull(message="ID required")
    private String id;

    @Column(index=1, name="amount")
    @Range(min=0, max=100000, message="Invalid amount")
    private double amount;

    @Column(index=2, name="date")
    private String date;
}

