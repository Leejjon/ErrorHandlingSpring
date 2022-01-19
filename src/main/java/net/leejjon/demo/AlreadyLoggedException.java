package net.leejjon.demo;

public class AlreadyLoggedException extends RuntimeException {
    public AlreadyLoggedException(Exception e) {
        super(e);
    }
}
