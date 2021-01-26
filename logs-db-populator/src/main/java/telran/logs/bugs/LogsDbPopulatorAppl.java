package telran.logs.bugs;

import logs.bugs.dto.LogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import telran.logs.bugs.mongo.doc.LogDoc;

import javax.validation.Valid;
import java.util.function.Consumer;

@SpringBootApplication
public class LogsDbPopulatorAppl {

    @Autowired
    LogsRepo repository;

    public static void main(String[] args) {
        SpringApplication.run(LogsDbPopulatorAppl.class, args);
    }

    @Bean
    Consumer<LogDto> getLogDtoConsumer() {
        return this::takeAndSaveLogDto;
    }

    private void takeAndSaveLogDto(@Valid LogDto logDto) {
        //TODO: create LogDocument with validation and saving mongoDB // taking and saving to MongoDb
        repository.save(new LogDoc(logDto));
        System.out.println(logDto);
    }
}

