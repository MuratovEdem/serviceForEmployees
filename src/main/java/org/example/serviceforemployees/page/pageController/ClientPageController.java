package org.example.serviceforemployees.page.pageController;

import lombok.RequiredArgsConstructor;
import org.example.serviceforemployees.page.pageDTO.ApplicationPageDto;
import org.example.serviceforemployees.page.pageDTO.ClientPageDto;
import org.example.serviceforemployees.page.pageService.ApplicationPageService;
import org.example.serviceforemployees.page.pageService.ClientPageService;
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
@RequestMapping("/home/clients")
@RequiredArgsConstructor
public class ClientPageController {

    private final ClientPageService clientPageService;
    private final ApplicationPageService applicationPageService;

    @GetMapping
    public String getAllClientsPage(Model model) {
        List<ClientPageDto> clients = clientPageService.findAll();
        model.addAttribute("clients", clients);

        return "clients-page.html";
    }

    @GetMapping("/{id}/applications")
    public String getApplicationsByClientIdPage(@PathVariable Long id, Model model) {
        List<ApplicationPageDto> applicationsByClientId = applicationPageService.findApplicationsByClientId(id);
        model.addAttribute("applications", applicationsByClientId);

        return "client's-applications-page.html";
    }

    @GetMapping("/{id}")
    public String getClientPage(@PathVariable Long id, Model model) {
        Optional<ClientPageDto> clientOpt = clientPageService.findById(id);
        if (clientOpt.isEmpty()) {
            throw new NoSuchElementException();
        }

        model.addAttribute("client", clientOpt.get());
        return "client-page.html";
    }
}
