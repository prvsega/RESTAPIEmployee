package by.prvsega.restservice.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class MediaDTO {

    private int id;
    @NotEmpty
    private String path;

}
