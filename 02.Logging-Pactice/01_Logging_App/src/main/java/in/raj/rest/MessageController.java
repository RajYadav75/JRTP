package in.raj.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    private Logger logger = LoggerFactory.getLogger(MessageController.class);


    @GetMapping("/welcome")
    public String welcomeMsg(){
        logger.debug("This is debug msg from welcome....");
        logger.info("welcomeMsg() execution started.....  " );

        String msg = "Welcome to Raj Git Repo";
        logger.warn("This is warning from welcome method");
        try{
            int i = 10/0;
        }catch (Exception e){
            logger.error("Exception Error "+e.getMessage());
        }

        logger.info("Welcome() execution ended...");
        return msg;
    }
    @GetMapping("/greet")
    public String greetMsg(){
        logger.debug("This is debug msg from welcome....");
        logger.info("GreetMsg() execution started...");
        String msg = "Good Morning...";
        try{
            String s = null;
            s.length();
        }catch (Exception e){
            logger.error("Exception Error "+e.getMessage());
        }

        logger.warn("This is warning from greet method");
        logger.info("GreetMsg() execution ended");
        return msg;
    }
}
