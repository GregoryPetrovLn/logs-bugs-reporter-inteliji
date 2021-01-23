package telran.logs.bugs.mongo.doc;

import logs.bugs.dto.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "logs")
public class LogDoc {
    @Id
    ObjectId id;
    Date dateTime;
    LogType logType;
    String artifact;
    int responseTime;
    String result;

    public LogDoc(LogDto logDto) {
        dateTime = logDto.dateTime;
        logType = logDto.logType;
        artifact = logDto.artifact;
        responseTime = logDto.responseTime;
        result = logDto.result;
    }

    public LogDoc(Date dateTime, LogType logType, String artifact, int responseTime, String result) {
        this.dateTime = dateTime;
        this.logType = logType;
        this.artifact = artifact;
        this.responseTime = responseTime;
        this.result = result;
    }

    public LogDoc() {
    }

    public LogDto getLogDto() {
         LogDto res = new LogDto(dateTime, logType, artifact, responseTime, result);
         return res;
    }

    public ObjectId getId() {
        return id;
    }
}
