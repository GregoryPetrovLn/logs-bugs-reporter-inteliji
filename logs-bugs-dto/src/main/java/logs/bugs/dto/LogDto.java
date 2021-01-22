package logs.bugs.dto;
import java.util.Date;

import javax.validation.constraints.*;

public class LogDto {
    @NotNull
    public Date dateTime;
    @NotNull
    public LogType logType;
    @NotEmpty
    public String artifact;
    public int responseTime;
    public String result;

    public LogDto(@NotNull Date dateTime, @NotNull LogType logType, @NotEmpty String artifact, int responseTime,
                  String result) {
        super();
        this.dateTime = dateTime;
        this.logType = logType;
        this.artifact = artifact;
        this.responseTime = responseTime;
        this.result = result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LogDto other = (LogDto) obj;
        if (artifact == null) {
            if (other.artifact != null)
                return false;
        } else if (!artifact.equals(other.artifact))
            return false;
        if (dateTime == null) {
            if (other.dateTime != null)
                return false;
        } else if (!dateTime.equals(other.dateTime))
            return false;
        if (logType != other.logType)
            return false;
        if (responseTime != other.responseTime)
            return false;
        if (result == null) {
            if (other.result != null)
                return false;
        } else if (!result.equals(other.result))
            return false;
        return true;
    }
}