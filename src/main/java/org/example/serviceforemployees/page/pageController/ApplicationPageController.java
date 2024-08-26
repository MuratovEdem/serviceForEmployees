package org.example.serviceforemployees.page.pageController;

import lombok.RequiredArgsConstructor;
import org.example.serviceforemployees.page.pageDTO.ApplicationPageDto;
import org.example.serviceforemployees.page.pageService.ApplicationPageService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequestMapping("/home/applications")
@RequiredArgsConstructor
public class ApplicationPageController {

    private final ApplicationPageService applicationPageService;

//    @GetMapping
//    public String getAllApplicationsCurrentLoggedUser(Model model) {
//        List<ApplicationPageDto> applications = applicationPageService.findAll();
//
//        model.addAttribute("applications", applications);
//        return "applications-page.html";
//    }

    @GetMapping("/completed")
    public String getApplicationsCurrentLoggedUserByStatusPage(@RequestParam Boolean isCompleted, Model model) {
        List<ApplicationPageDto> applications = applicationPageService.findApplicationsCurrentLoggedUserByStatus(isCompleted);

        model.addAttribute("applications", applications);
        return "applications-page.html";
    }

    @GetMapping("/{id}")
    public String getApplicationPage(@PathVariable Long id, Model model) {
        Optional<ApplicationPageDto> applicationOpt = applicationPageService.findById(id);
        if (applicationOpt.isEmpty()) {
            throw new NoSuchElementException();
        }

        model.addAttribute("app", applicationOpt.get());
        return "application-page.html";
    }
}
