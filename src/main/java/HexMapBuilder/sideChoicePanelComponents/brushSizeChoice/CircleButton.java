package HexMapBuilder.sideChoicePanelComponents.brushSizeChoice;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CircleButton extends Circle {

    private final static double SCALE = 1.08;

    public void selectOn(){
        setScaleX(SCALE);
        setScaleY(SCALE);
        setStrokeWidth(2.5);
    }

    public void selectOff(){
        setScaleX(1);
        setScaleY(1);
        setStrokeWidth(1);
    }


    public CircleButton(int radius){
        super(radius);
        setStroke(Color.BLACK);
        setFill(Color.WHITE);

    }

}
