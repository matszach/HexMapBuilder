package HexMapBuilder.sideChoicePanelComponents.fieldTypeChoice;

import HexMapBuilder.controllers.MouseBrushController;
import HexMapBuilder.mapDisplayPane.hexFields.FieldType;
import HexMapBuilder.mapDisplayPane.hexFields.HexField;
import HexMapBuilder.sideChoicePanelComponents.fieldTypeChoice.HexFieldAsRadioButton;
import HexMapBuilder.sideChoicePanelComponents.symbolTypeChoice.SymbolChooserPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;


public class FieldChooserPane extends Pane {

    private final static double OFFSET = 5;

    private static List<HexFieldAsRadioButton> hexes = new ArrayList<>();



    private void loadAllFieldTypes(){
        for(FieldType fieldType : FieldType.values()){
            hexes.add(new HexFieldAsRadioButton(fieldType));
        }
    }

    private void equipHexButtons(){
        for(HexFieldAsRadioButton hex : hexes){
            hex.setOnMouseClicked(e-> {
                SymbolChooserPane.allSymbolsOff();
                MouseBrushController.setCurrentSymbolType(null);
                MouseBrushController.setCurrentSymbolColorStyle(null);
                MouseBrushController.setSymbolRemovingMode(false);
                MouseBrushController.setCurrentType(hex.getFieldType());
                allHexesOff();
                hex.hexOn();
            });
        }
    }

    public static void allHexesOff(){
        for(HexFieldAsRadioButton hex : hexes){
            hex.hexOff();
        }
    }


    private void placeHexes(){
       hexes.forEach(this::placeOneHex); // method reference
    }

    private void placeOneHex(HexFieldAsRadioButton hex){

        int index = hexes.indexOf(hex);

        int rowNum = index/3;
        int colNum = index%3;
        boolean isMiddleColumn = ((hexes.indexOf(hex)) +2 ) % 3 == 0; // checks if the hex is from column

        double locX = colNum * HexField.HEXFIELD_WIDTH + OFFSET;

        double locY = rowNum * (HexField.HEXFIELD_HEIGHT*1.33);
        if(isMiddleColumn) locY += 0.5 * (HexField.HEXFIELD_HEIGHT*1.33);


        hex.relocate(locX,locY);
        getChildren().add(hex);
    }



    public FieldChooserPane(){
        loadAllFieldTypes();
        equipHexButtons();
        placeHexes();
    }









}
