package error_handler.loggers;

import error_handler.ErrorMessage;

import java.io.FileWriter;
import java.io.IOException;

public class FileLogger extends Logger{
    FileWriter fw;

    public FileLogger(){

    }

    public FileLogger(ErrorMessage message) throws IOException {
        super(message);
        fw = new FileWriter("../log.txt");
    }

    @Override
    public void log(String message) throws IOException {
        fw.write(formatErrorMessage() + "\n");
    }
}
