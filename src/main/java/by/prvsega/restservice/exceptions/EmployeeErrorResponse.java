package by.prvsega.restservice.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeErrorResponse {
    private String message;
    private long timestmp;

    public EmployeeErrorResponse(String message, long timestmp) {
        this.message = message;
        this.timestmp = timestmp;
    }

}
