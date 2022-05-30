package component;

import myInterface.ServiceFrontend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public
class MyComponent {

    @Qualifier("frontend5555")
    @Autowired
    private ServiceFrontend frontend1;

    public void outMyComponent() {
        frontend1.say();
    }
}
