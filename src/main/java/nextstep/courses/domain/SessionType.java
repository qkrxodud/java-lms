package nextstep.courses.domain;

public enum SessionType {
    READY("ready"),
    RECRUITING("recruiting"),
    END("end");

    private String status;

    SessionType(String status) {
        this.status = status;
    }

    public static boolean isRecruiting(SessionType sessionType) {
        return sessionType == RECRUITING;
    }
}
