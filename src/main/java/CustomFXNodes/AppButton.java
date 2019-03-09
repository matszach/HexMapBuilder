package CustomFXNodes;

import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;

public class AppButton extends Button {

    private static final double SCALE = 0.9;

    public void buttonOn(){
        setScaleX(SCALE);
        setScaleY(SCALE);
        setEffect(new ColorAdjust(0.1,0,0,0));
    }

    public void buttonOff(){
        setScaleX(1);
        setScaleY(1);
        setEffect(null);
    }

    public AppButton(String text){
        super(text);
       setId("appButton");
    }
}
