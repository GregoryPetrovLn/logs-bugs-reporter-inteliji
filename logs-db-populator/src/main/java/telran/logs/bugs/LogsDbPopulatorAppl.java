package telran.logs.bugs;

import logs.bugs.dto.LogDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

@SpringBootApplication
public class LogsDbPopulatorAppl {
    public static void main(String[] args) {
        SpringApplication.run(LogsDbPopulatorAppl.class, args);
    }
    @Bean
    Consumer<LogDto> getLogDtoConsumer(){
        return this::takeAndSaveLogDto;
    }

    private void takeAndSaveLogDto(LogDto logDto) {
        //TODO: create LogDocument with validation and saving mongoDB // taking and saving to MongoDb
        System.out.println(logDto);
    }
}

