package HexMapBuilder.mapDisplayPane;

import HexMapBuilder.mapDisplayPane.hexFields.FieldType;
import HexMapBuilder.mapDisplayPane.hexFields.HexField;
import HexMapBuilder.mapDisplayPane.hexFields.symbols.SymbolFactory;
import HexMapBuilder.mapSaving.MapSerializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import lombok.Getter;


public class MapDisplayPane extends ScrollPane {

    @Getter
    private static StackPane mapStackPane = new StackPane();
    @Getter
    private static Pane hexFieldLayer = new Pane();
    @Getter
    private static Pane symbolLayer = new Pane();
    @Getter
    private static Pane textLayer = new Pane();

    private static HexField[][] currentMap;
    public static HexField[][] getCurrentMap() {
        return currentMap;
    }

    private static void placeHexField(HexField hexField){

        // base location
        double baseX = -HexField.HEXFIELD_WIDTH/2;
        double baseY = -HexField.HEXFIELD_HEIGHT/2-2;

        // row and col
        int col = hexField.getCol();
        int row = hexField.getRow();

        // offset Y (due to nature of hex-grids)
        double offsetY = col % 2 == 0 ? 0 : (HexField.HEXFIELD_HEIGHT/2) ;

        // calculation
        double locationX = baseX + col*0.75*(HexField.HEXFIELD_WIDTH);
        double locationY = baseY + offsetY + row*(HexField.HEXFIELD_HEIGHT);

        // relocation
        hexField.relocate(locationX,locationY);

        // allocation
        currentMap[row][col] = hexField;

        // placement
        hexFieldLayer.getChildren().add(hexField);

        // if hexfield has a symbol ->
        if(hexField.getSymbol()!=null){
            hexField.placeSymbol(hexField.getSymbol().getSymbolType(), hexField.getSymbol().getSymbolColorStyle());
        }

        // if hexfield has label
        if(hexField.getLabel()!=null){
            hexField.placeText(hexField.getLabel().getText());
        }

    }


    // resets all layers
    private static void resetAllLayers(){
        hexFieldLayer.getChildren().clear();
        symbolLayer.getChildren().clear();
        textLayer.getChildren().clear();
    }


    // draws default, empty map
    public static void drawDefaultMap(FieldType fieldType, int rows, int cols){
        currentMap = new HexField[rows][cols];
        resetAllLayers();
        for(int c = 0; c < cols; c++){
            for(int r = 0; r < rows; r++){
                HexField hexField = new HexField(fieldType,r,c);
                placeHexField(hexField);
            }
        }
    }



    // draw map from MapSerializable
    public static void drawFromMapSerializable(MapSerializable ms){
        currentMap = new HexField[ms.getTypeMap().length][ms.getTypeMap()[0].length];
        resetAllLayers();
        for(int c = 0; c < ms.getTypeMap()[0].length; c++){
            for(int r = 0; r < ms.getTypeMap().length; r++){
                HexField hexField = new HexField(ms.getTypeMap()[r][c],r,c);
                if(ms.getSymbolTypeMap()[r][c] != null && ms.getSymbolColorStyleMap()[r][c] != null){
                    hexField.setSymbol(SymbolFactory.getSymbol(ms.getSymbolTypeMap()[r][c],ms.getSymbolColorStyleMap()[r][c]));
                }
                if(ms.getTextMap()[r][c] != null){
                    hexField.setLabel(new Label(ms.getTextMap()[r][c]));
                }
                placeHexField(hexField);
            }
        }
    }




    // Constructor
    public MapDisplayPane(){
        setContent(mapStackPane);
        mapStackPane.getChildren().addAll(hexFieldLayer, symbolLayer, textLayer);
        // makes textLayer and symbolLayer "transparent" to mouse events,
        // allowing the user to interact with hexFieldLayer
        // while keeping the textLayer and symbolLayer on top
        textLayer.setPickOnBounds(false);
        symbolLayer.setPickOnBounds(false);
        // TEMP todo -> remove this and make it impossible to save a file if no map is loaded
        drawDefaultMap(FieldType.SEA,60,90);
    }

}
