package error_handler.loggers;

import error_handler.ErrorMessage;

import java.io.FileWriter;
import java.io.IOException;

public class FileLogger extends Logger{
    FileWriter fw;

    public FileLogger(){
        try {
            fw = new FileWriter("..../resources/log.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update(ErrorMessage errorMessage) {
        try {
            fw.append(super.formatErrorMessage(errorMessage));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
