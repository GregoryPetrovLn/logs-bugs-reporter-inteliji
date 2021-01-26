package telran.logs.bugs;

import logs.bugs.dto.LogDto;
import logs.bugs.dto.LogType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.support.GenericMessage;
import telran.logs.bugs.mongo.doc.LogDoc;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Import(TestChannelBinderConfiguration.class)
public class LogsDbPopulatorTest {
    @Autowired
    InputDestination input;

    @Autowired
    LogsRepo logsRepo;

    @Test
    void takeLogDto(){
        LogDto logDto = new LogDto(new Date(), LogType.NO_EXCEPTION, "artifact",
                20, "result");
        input.send(new GenericMessage<LogDto>(logDto));
        //TODO: testing of saving logDto into MongoDB
        LogDoc actualDoc = logsRepo.findAll().get(0) ;
        assertEquals(logDto, actualDoc.getLogDto());

    }
    
}
