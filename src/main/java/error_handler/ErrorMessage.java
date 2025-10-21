package error_handler;

import lombok.Getter;

import java.util.Date;
@Getter
public class ErrorMessage {
    private String content;
    private ErrorType type;
    private Date date;

    public ErrorMessage(String content, ErrorType type, Date date) {
        this.content = content;
        this.type = type;
        this.date = date;
    }
}
