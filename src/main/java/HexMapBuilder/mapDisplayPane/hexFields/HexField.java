package HexMapBuilder.mapDisplayPane.hexFields;


import HexMapBuilder.controllers.MouseBrushController;
import HexMapBuilder.mapDisplayPane.MapDisplayPane;
import HexMapBuilder.mapDisplayPane.hexFields.symbols.Symbol;
import HexMapBuilder.mapDisplayPane.hexFields.symbols.SymbolColorStyle;
import HexMapBuilder.mapDisplayPane.hexFields.symbols.SymbolFactory;
import HexMapBuilder.mapDisplayPane.hexFields.symbols.SymbolType;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.util.Map;

public class HexField extends Polygon {

    private static final double EQUILATERAL_TRIANGLE_RATIO = 0.866;
    public static final double HEXFIELD_WIDTH = 35;
    public static final double HEXFIELD_HEIGHT = HEXFIELD_WIDTH * EQUILATERAL_TRIANGLE_RATIO;
    
    private static final double SCALE_HOVER_ON = 1.08;
    private static final double SCALE_HOVER_OFF = 1.00;

    private static final double STROKE_WIDTH_HOVER_ON = 2.5;
    private static final double STROKE_WIDTH_HOVER_OFF = 1;

    // HexField's type
    private FieldType fieldType;

    // Hexfield's symbol
    private Symbol symbol;


    // HexFields coordinates
    private int row;
    private int col;




    // paints and updates the field
    private void paintField(FieldType fieldType){
        if(fieldType == null){
            return;
        }
        this.fieldType = fieldType;
        setFill(fieldTypeToColor(fieldType));
    }
    public void placeSymbol(SymbolType symbolType, SymbolColorStyle symbolColorStyle){
        if(symbolType == null || symbolColorStyle == null){
            return;
        }
        if(symbol!=null){
            // removes current symbol if already placed
            MapDisplayPane.getMapPane().getChildren().remove(symbol);
        }

        symbol = SymbolFactory.getSymbol(symbolType, symbolColorStyle);
        symbol.relocate(getLayoutX()-HexField.HEXFIELD_WIDTH*0.78,getLayoutY()-HEXFIELD_HEIGHT*0.8);
        MapDisplayPane.getMapPane().getChildren().add(symbol);
        symbol.toFront();
        setAllMouseInteractions(symbol); // this makes it so that clicking "through" the symbol is possible

    }

    private void updateField(){
        paintField(MouseBrushController.getCurrentType());
        placeSymbol(MouseBrushController.getCurrentSymbolType(), MouseBrushController.getCurrentSymbolColorStyle());
    }







    // FieldType to Color
    private Color fieldTypeToColor(FieldType fieldType){
        switch (fieldType){

            case SEA: return Color.DARKBLUE;
            case LAKE : return Color.BLUE;
            case RIVER: return Color.CYAN;

            case DEEP_FORREST: return Color.DARKGREEN;
            case FORREST: return Color.GREEN;
            case GRASSLAND: return Color.LIGHTGREEN;

            case DESERT: return Color.ORANGE;
            case DESERT2: return Color.DARKORANGE;
            case SHORE: return Color.YELLOW;

            case MOUNTAIN_TOP: return Color.DARKSLATEGRAY;
            case HIGH_MOUNTAIN: return Color.GRAY;
            case MOUNTAIN: return Color.LIGHTSLATEGRAY;

            default: return Color.WHITE;
        }
    }




    // On hover
    private void hoverOn(){
        setScaleX(SCALE_HOVER_ON);
        setScaleY(SCALE_HOVER_ON);
        setStrokeWidth(STROKE_WIDTH_HOVER_ON);
        toFront();
        symbolHoverOn();

    }
    private void symbolHoverOn(){
        if(symbol!=null){
            symbol.setScaleX(SCALE_HOVER_ON);
            symbol.setScaleY(SCALE_HOVER_ON);
            symbol.toFront();
        }
    }


    private void hoverOff(){
        setScaleX(SCALE_HOVER_OFF);
        setScaleY(SCALE_HOVER_OFF);
        setStrokeWidth(STROKE_WIDTH_HOVER_OFF);
        symbolHoverOff();
    }
    private void symbolHoverOff(){
        if(symbol!=null){
            symbol.setScaleX(SCALE_HOVER_OFF);
            symbol.setScaleY(SCALE_HOVER_OFF);
        }
    }








    // mouse actions for HexField and it's Symbol
    private void setAllMouseInteractions(Node node){

        // On Hover
        node.setOnMouseEntered(e->hoverOn());
        node.setOnMouseExited(e->hoverOff());

        // On Drag / Click
        node.setOnMousePressed(e->{
            if(!e.isPrimaryButtonDown()){
                return;
            }
            if(e.isSecondaryButtonDown()){
                return;
            }
            e.setDragDetect(true);
            updateField();
        });

        node.setOnMouseDragged(e->{
            e.setDragDetect(false);
        });

        node.setOnDragDetected(e-> {
            startFullDrag();
        });

        node.setOnMouseDragEntered(e-> {
            if(!e.isPrimaryButtonDown()){
                return;
            }
            updateField();
        });
    }







    // Constructors
    public HexField(FieldType fieldType, int row, int col){
        this(fieldType);
        this.row = row;
        this.col = col;
        setAllMouseInteractions(this);

    }
    public HexField(FieldType fieldType, Symbol symbol, int row, int col){
        this(fieldType,row,col);
        this.symbol = symbol;
    }

    // Bare Constructor -> this one is to to be inherited by HexFieldAsRadioButton
    public HexField(FieldType fieldType){
        this.fieldType=fieldType;
        paintField(fieldType);

        // Points
        getPoints().setAll(
                0d,-HEXFIELD_HEIGHT/2,
                -HEXFIELD_WIDTH/4,-HEXFIELD_HEIGHT,
                -3*HEXFIELD_WIDTH/4,-HEXFIELD_HEIGHT,
                -HEXFIELD_WIDTH,-HEXFIELD_HEIGHT/2,
                -3*HEXFIELD_WIDTH/4,0d,
                -HEXFIELD_WIDTH/4,0d);

        // Stroke (Border)
        setStroke(Color.BLACK);

    }






    // Getters and Setters
    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

}