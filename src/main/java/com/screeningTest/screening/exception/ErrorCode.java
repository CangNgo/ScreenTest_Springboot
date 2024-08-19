package com.screeningTest.screening.exception;

public enum ErrorCode {
    USER_NOT_EXISTED(999, "user not existed"),
    USER_EXISTED (998, "user existed"),
    TEACHER_EXISTED(1001, "Teacher already existed"),
    UNCATEGORIZED(1002, "Uncategorized"),
    TEACHER_INVALID(1003, "Username must be at least 3 characters"),
    PASSWORD_INVALID(1004, "Password must be at least 8 characters"),
    INVALID_KEY(1005, "Invalid message key"),
    TEACHER_NOT_FOUND(1006, "Teacher not found"),
    DEGREE_EXISTED(1007, "Degree already existed"),
    CONTRACT_EXISTED(1008, "Contract already existed"),
    DEGREE_NOT_FOUND(1009, "Degree not found"),
    CONTRACT_NOT_FOUND(1010, "Contract not found"),
    CONTRACT_NOT_ITEM (1011, "Contract not item"),
    UNAUTHENTICATED(1012, "Unauthenticated"),
    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
