package by.prvsega.restservice.services.mail;

import by.prvsega.restservice.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ScheduledTasks {
    private final EmployeeService employeeService;
    @Autowired
    public ScheduledTasks(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Scheduled(cron = "0 0 23 * * MON-FRI")
    private void sendEmailAboutSleep(){
        employeeService.sendEmailAllEmployeeToNight();
    }
}
