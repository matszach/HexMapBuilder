package HexMapBuilder.sideChoicePanelComponents.fieldTypeChoice;

import HexMapBuilder.controllers.MouseBrushController;
import HexMapBuilder.mapDisplayPane.hexFields.FieldType;
import HexMapBuilder.mapDisplayPane.hexFields.HexField;
import javafx.scene.paint.Color;

public class HexFieldAsRadioButton extends HexField {


    public void hexOn(){
        setStroke(Color.RED);
        setScaleX(1.15);
        setScaleY(1.15);
        setStrokeWidth(3);
    }

    public void hexOff(){
        setStroke(Color.BLACK);
        setScaleX(1.00);
        setScaleY(1.00);
        setStrokeWidth(1);
    }

    public HexFieldAsRadioButton(FieldType fieldType){
        super(fieldType);
        if(fieldType.equals(MouseBrushController.getCurrentType()))hexOn(); // default

    }
}
