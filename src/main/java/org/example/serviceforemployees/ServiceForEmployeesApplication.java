package org.example.serviceforemployees;

import org.example.serviceforemployees.model.*;
import org.example.serviceforemployees.repository.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@SpringBootApplication
public class ServiceForEmployeesApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ServiceForEmployeesApplication.class, args);

		EmployeeRepository employeeRepository = context.getBean(EmployeeRepository.class);

		AccountRepository accountRepository = context.getBean(AccountRepository.class);

		ClientRepository clientRepository = context.getBean(ClientRepository.class);

		for (int i = 1; i < 6; i++) {
			Client client = new Client();
			client.setName("client" + i);
			client.setNumber(i + 50);

			clientRepository.save(client);
		}

		ApplicationRepository applicationRepository = context.getBean(ApplicationRepository.class);
		Client client = clientRepository.findById(1L).get();

		Application application1 = new Application();
		application1.setText("qwe");
		application1.setIsCompleted(false);
		application1.setCreatedAt(LocalDate.now());
		application1.setDeadline(LocalDate.now().plusDays(3));
		application1.setClient(client);

		Application application2 = new Application();
		application2.setText("asd");
		application2.setIsCompleted(true);
		application2.setCreatedAt(LocalDate.now());
		application2.setDeadline(LocalDate.now().plusDays(3));
		application2.setClient(client);

		clientRepository.save(client);

		client = clientRepository.findById(3L).get();


		Application application3 = new Application();
		application3.setText("zxc");
		application3.setIsCompleted(true);
		application3.setCreatedAt(LocalDate.now());
		application3.setDeadline(LocalDate.now().plusDays(3));
		application3.setClient(client);
		clientRepository.save(client);

		applicationRepository.saveAll(List.of(application1, application2, application3));

		HashSet<Application> applicationHashSet = new HashSet<>();
		applicationHashSet.add(application1);
		applicationHashSet.add(application2);

		HashSet<Application> applicationHashSet1 = new HashSet<>();
		applicationHashSet1.add(application3);


		RoleRepository roleRepository = context.getBean(RoleRepository.class);

		RoleEntity admin = new RoleEntity();
		admin.setName(RoleEnum.ADMIN.getName());

		RoleEntity user = new RoleEntity();
		user.setName(RoleEnum.EMPLOYEE.getName());

		RoleEntity rest = new RoleEntity();
		rest.setName(RoleEnum.REST.getName());

		roleRepository.saveAll(List.of(admin, user, rest));

		HashSet<RoleEntity> set1 = new HashSet<>(List.of(admin, user, rest));
		HashSet<RoleEntity> set2 = new HashSet<>(List.of(user, rest));
		HashSet<RoleEntity> set3 = new HashSet<>(List.of(user));

		for (int i = 1; i < 6; i++) {
			Employee employee = new Employee();
			employee.setName("Eployee #" + i);
			employee.setAge(i + 20);

			Account account = new Account();
			account.setLogin("login_"+i);
			account.setPassword("$2a$12$JzVxah6YS3hAZxGgPABlp.kRV2vQnyqhe7e6yaIvImsN7LNsPnXRO"); //password
			if (i == 1) {
				account.setRoles(set1);
				employee.setApplications(applicationHashSet);
			} else if (i == 3) {
				account.setRoles(set2);
				employee.setApplications(applicationHashSet1);
			} else {
				account.setRoles(set3);
				employee.setApplications(applicationHashSet1);
			}

			accountRepository.save(account);
			employee.setAccount(account);
			employeeRepository.save(employee);
		}
	}
}
