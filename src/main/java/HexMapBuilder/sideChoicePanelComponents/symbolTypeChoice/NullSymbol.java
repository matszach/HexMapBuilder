package HexMapBuilder.sideChoicePanelComponents.symbolTypeChoice;

import HexMapBuilder.controllers.MouseBrushController;
import HexMapBuilder.mapDisplayPane.hexFields.HexField;
import HexMapBuilder.mapDisplayPane.hexFields.symbols.Symbol;
import HexMapBuilder.sideChoicePanelComponents.fieldTypeChoice.FieldChooserPane;
import HexMapBuilder.sideChoicePanelComponents.textPlacementChoice.TextPlacementChoicePane;
import javafx.scene.paint.Color;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class NullSymbol extends Symbol {

    private void onAction(){
        FieldChooserPane.fieldPaintingModeOff();
        SymbolChooserPane.symbolPlacementModeOff();
        TextPlacementChoicePane.textPlacementModeOff();
        MouseBrushController.setSymbolRemovingMode(true);
        setStrokeWidth(2);
        setScaleX(1.08);
        setScaleY(1.08);
    }

    private static double[] shape;


    @PostConstruct
    private void init(){
        double size = HexField.HEXFIELD_HEIGHT/2;
        shape = new double[]{
                //    x        y
                size*0.00,size*0.15,
                size*0.15,size*0.00,
                size*0.50,size*0.35,
                size*0.85,size*0.00,
                size*1.00,size*0.15,
                size*0.65,size*0.50,
                size*1.00,size*0.85,
                size*0.85,size*1.00,
                size*0.50,size*0.65,
                size*0.15,size*1.00,
                size*0.00,size*0.85,
                size*0.35,size*0.50};
    }


    public NullSymbol(){
       super(shape);
       setFill(Color.GRAY);
       setStroke(Color.BLACK);
       setOnMouseClicked(e->onAction());
    }
}
