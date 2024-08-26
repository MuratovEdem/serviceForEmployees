package org.example.serviceforemployees.page.pageDTO;

import lombok.Data;
import org.example.serviceforemployees.model.Application;
import org.springframework.core.io.ByteArrayResource;

import java.util.Set;

@Data
public class ClientPageDto {
    private Long id;
    private String name;
    private Set<ApplicationPageDto> applications;
}
