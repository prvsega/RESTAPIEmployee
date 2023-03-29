package by.prvsega.restservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class PageResponseDTO<T> {

    private final int totalPages;
    private final long totalElements;
    private final List<T> list;


}
