package org.example.serviceforemployees.page.pageService;

import lombok.RequiredArgsConstructor;
import org.example.serviceforemployees.model.Application;
import org.example.serviceforemployees.page.pageDTO.ApplicationPageDto;
import org.example.serviceforemployees.service.ApplicationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicationPageService {

    private final ApplicationService applicationService;
//
//    public List<ApplicationPageDto> findAll() {
//        return applicationService.findAll().stream()
//                .map(this::convert)
//                .toList();
//    }

    public List<ApplicationPageDto> findApplicationsByClientId(Long id){
        return applicationService.findApplicationsByClientId(id).stream()
                .map(this::convert)
                .toList();
    }

    public List<ApplicationPageDto> findApplicationsCurrentLoggedUserByStatus(Boolean isCompleted) {
        return applicationService.findApplicationsCurrentLoggedUserByStatus(isCompleted).stream()
                .map(this::convert)
                .toList();
    }

    public Optional<ApplicationPageDto> findById(Long id) {
        return applicationService.findById(id)
                .map(this::convert);
    }

    private ApplicationPageDto convert(Application application) {
        ApplicationPageDto applicationPageDto = new ApplicationPageDto();

        applicationPageDto.setId(application.getId());
        applicationPageDto.setText(application.getText());
        applicationPageDto.setIsCompleted(application.getIsCompleted());
        applicationPageDto.setClientName(application.getClient().getName());

        return applicationPageDto;
    }
}
