package com.fintech.engine.model;

import com.fintech.engine.annotations.*;

@FileSource(delimiter=";")
public class SecurityAudit {

    @Column(index=0, name="ip")
    @Regex(pattern="\\d+\\.\\d+\\.\\d+\\.\\d+", message="Invalid IP")
    private String ip;

    @Column(index=1, name="severity")
    @Range(min=1, max=5, message="Severity 1-5")
    private int severity;
}

