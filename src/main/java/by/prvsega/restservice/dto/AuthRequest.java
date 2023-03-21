package by.prvsega.restservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class AuthRequest {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;


}
