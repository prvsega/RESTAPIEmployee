package by.prvsega.restservice.services.mail;

import by.prvsega.restservice.models.Employee;
import by.prvsega.restservice.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender emailSender;
    private final EmployeeRepository employeeRepository;

    public void sendSimpleEmail(String toAddress, String subject, String message) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("noreply@bestapp.com");
        simpleMailMessage.setTo(toAddress);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        emailSender.send(simpleMailMessage);
    }


    public void sendEmailAboutRegistration(String email, String message) {
        String subject = "Created new Employee";
        sendSimpleEmail(email, subject, message);
    }

    public void sendEmailAllEmployeeToNight() {
        List<String> listEmail = employeeRepository.findAll().stream().map(Employee::getEmail).collect(Collectors.toList()); // get a list of employee email
        String subject = "Employee care department";
        String message = "It is late. You must go to bed, because You will go to work and will be tired tomorrow";
        listEmail.forEach(email -> sendSimpleEmail(email, subject, message));
    }


}
