package server.example.assignment.file_limit.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileLimitResponse {
    private Long id;
    private String name;
    private LimitType type;

    public FileLimitResponse(FileLimit fileLimit) {
        this.id = fileLimit.getId();
        this.name = fileLimit.getName();
        this.type = fileLimit.getLimitType();
    }
}
