package telran.logs.bugs;

import logs.bugs.dto.LogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Supplier;


@SpringBootApplication
public class RandomLogsAppl {
    @Autowired
    RandomLogs randomLogs;

    public static void main(String[] args) {
        SpringApplication.run(RandomLogsAppl.class, args);

    }

    @Bean
    Supplier<LogDto> random_logs_provider() {
        return randomLogs::createRandomLog;
    }


}
