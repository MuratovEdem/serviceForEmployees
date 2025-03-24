package org.example.serviceforemployees.page.pageDTO;

import lombok.Data;

import java.util.Set;

@Data
public class ClientPageDto {
    private Long id;
    private String name;
    private Set<ApplicationPageDto> applications;
}
