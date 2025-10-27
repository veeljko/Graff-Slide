package error_handler;

import lombok.Getter;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
@Getter
public class ErrorMessage {
    private String content;
    private ErrorType type;
    private LocalDateTime date;

    public ErrorMessage(String content, ErrorType type, LocalDateTime date) {
        this.content = content;
        this.type = type;
        this.date = date;
    }

    public String getFormatedMessage(){
        return "[" + type + "][" + date.getDayOfMonth() +"." + date.getMonthValue() + "." + date.getYear() + " " + date.getHour() + ":" + date.getMinute() + "] " + content + "\n";
    }
}
