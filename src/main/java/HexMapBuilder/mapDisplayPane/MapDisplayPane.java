package HexMapBuilder.mapDisplayPane;

import HexMapBuilder.mapDisplayPane.hexFields.FieldType;
import HexMapBuilder.mapDisplayPane.hexFields.HexField;
import HexMapBuilder.mapDisplayPane.hexFields.symbols.Symbol;
import HexMapBuilder.mapDisplayPane.hexFields.symbols.SymbolFactory;
import HexMapBuilder.mapSaving.MapSerializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;


public class MapDisplayPane extends ScrollPane {

    private static Pane mapPane = new Pane();

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
        mapPane.getChildren().add(hexField);

        // if hexfield has a symbol ->
        if(hexField.getSymbol()!=null){
            hexField.placeSymbol(hexField.getSymbol().getSymbolType(), hexField.getSymbol().getSymbolColorStyle());
            hexField.getSymbol().toFront();
        }

    }



    // works best with 100x100 or smaller, ~150x150 is about the limit.
    public static void drawDefaultMap(FieldType fieldType, int rows, int cols){
        currentMap = new HexField[rows][cols];
        mapPane.getChildren().clear();
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
        mapPane.getChildren().clear();
        for(int c = 0; c < ms.getTypeMap()[0].length; c++){
            for(int r = 0; r < ms.getTypeMap().length; r++){
                HexField hexField = new HexField(ms.getTypeMap()[r][c],r,c);
                if(ms.getSymbolTypeMap()[r][c] != null && ms.getSymbolColorStyle()[r][c] != null){
                    hexField.setSymbol(SymbolFactory.getSymbol(ms.getSymbolTypeMap()[r][c],ms.getSymbolColorStyle()[r][c]));
                }
                placeHexField(hexField);
            }
        }
    }



    // Constructor
    public MapDisplayPane(){
        setContent(mapPane);
        // TEMP todo -> remove this and make it impossible to save a file if no map is loaded
        drawDefaultMap(FieldType.SEA,60,90);
    }






    // Getters and Setters
    public static Pane getMapPane() {
        return mapPane;
    }






}
