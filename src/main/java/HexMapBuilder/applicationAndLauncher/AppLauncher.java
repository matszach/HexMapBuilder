package HexMapBuilder.applicationAndLauncher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AppLauncher {

    @Autowired
    private SolverApplication application;


    @EventListener
    public void start(ContextRefreshedEvent contextRefreshedEvent){
        application.launch();

    }

}
