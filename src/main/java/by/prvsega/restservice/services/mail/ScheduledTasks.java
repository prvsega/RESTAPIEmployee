package by.prvsega.restservice.services.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@EnableScheduling
public class ScheduledTasks {
    private final MailService mailService;

//    @Scheduled(cron = "0 0 23 * * MON-FRI")
    private void sendEmailAboutSleep(){
        mailService.sendEmailAllEmployeeToNight();
    }
}
