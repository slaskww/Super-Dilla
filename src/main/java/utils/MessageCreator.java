package utils;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MessageCreator {

   private static StringBuilder message;
   private static MessageCreator messageCreator;
   private static final Logger log = LogManager.getLogger(MessageCreator.class.getName());


    private MessageCreator() {
        message = new StringBuilder();
    }

    public static MessageCreator getMessageCreator(){
        if (messageCreator == null){
            messageCreator = new MessageCreator();
        }

        return messageCreator;
    }

    public static void appendMessage(String messageToAppend){
        message.append(messageToAppend);
    }

    public static void executeLog(){
        log.log(Level.INFO, message.toString());
        message = new StringBuilder();

    }
}
