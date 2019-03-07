package HexMapBuilder;


import HexMapBuilder.config.Config;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



public class Main {


    public static void main(String[] args) {


        // create context (container)
        ConfigurableApplicationContext context
                = new AnnotationConfigApplicationContext(Config.class);



        // close context (container)
        context.close();

    }



}
