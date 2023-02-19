package server.example.assignment.file_limit.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum LimitType {

    FIXED("FIXED"),
    CUSTOM("CUSTOM");

    private final String type;

    public String value() {
        return type;
    }
}
