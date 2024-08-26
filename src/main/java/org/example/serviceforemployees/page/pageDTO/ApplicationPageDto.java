package org.example.serviceforemployees.page.pageDTO;

import lombok.Data;
import org.example.serviceforemployees.model.Client;

@Data
public class ApplicationPageDto {
    private Long id;
    private String text;
    private Boolean isCompleted;
    private String clientName;
}