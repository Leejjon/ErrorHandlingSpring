package net.leejjon.demo;

import lombok.Getter;

public class AlreadyLoggedException extends RuntimeException {
    @Getter
    private final String uuid;

    public AlreadyLoggedException(Exception e, final String uuid) {
        super(e);
        this.uuid = uuid;
    }
}
