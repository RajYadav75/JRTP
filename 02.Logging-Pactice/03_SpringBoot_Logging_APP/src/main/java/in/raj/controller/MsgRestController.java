package in.raj.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MsgRestController {


    private Logger logger = LoggerFactory.getLogger(MsgRestController.class);

    @GetMapping("/welcome")
    public String getWlcmMsg(){
        logger.debug("This is debug msg from welcomeMSg() method..");
        logger.info("Welcome() started");
        logger.warn("this warning from welcomeMsg()");

        try{
            int i = 10/0;
        }catch (Exception e){
            logger.error("Exception occured :: "+ e.getMessage());
        }

        String msg = "Welcome to Raj Git Repository";
        logger.info("welcomeMsg() ended ");
        return msg;
    }
}
