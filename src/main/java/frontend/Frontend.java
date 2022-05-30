package frontend;

import lombok.ToString;
import myInterface.ServiceFrontend;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@ToString
@Component
@Qualifier("frontend5555")
public  class Frontend implements ServiceFrontend {

    @Value("${front}")
    private String phrase;

    @Override
    public String say() {
        System.out.println(phrase);
        return "frontSay";
    }
}
