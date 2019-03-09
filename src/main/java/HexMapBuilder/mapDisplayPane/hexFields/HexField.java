package HexMapBuilder.mapDisplayPane.hexFields;


import HexMapBuilder.controllers.MouseBrushController;
import HexMapBuilder.mapDisplayPane.MapDisplayPane;
import HexMapBuilder.mapDisplayPane.hexFields.symbols.Symbol;
import HexMapBuilder.mapDisplayPane.hexFields.symbols.SymbolColorStyle;
import HexMapBuilder.mapDisplayPane.hexFields.symbols.SymbolFactory;
import HexMapBuilder.mapDisplayPane.hexFields.symbols.SymbolType;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class HexField extends Polygon {

    private static final double EQUILATERAL_TRIANGLE_RATIO = 0.866;
    public static final double HEXFIELD_WIDTH = 35;
    public static final double HEXFIELD_HEIGHT = HEXFIELD_WIDTH * EQUILATERAL_TRIANGLE_RATIO;
    
    private static final double SCALE_HOVER_ON = 1.12;
    private static final double SCALE_HOVER_OFF = 1.00;

    private static final double STROKE_WIDTH_HOVER_ON = 2.5;
    private static final double STROKE_WIDTH_HOVER_OFF = 1;

    private static final double PREF_LABEL_WIDTH = 200;

    // HexField's type
    private FieldType fieldType;

    // Hexfield's symbol
    private Symbol symbol;

    // Hexfield's label
    private Label label;

    // HexFields coordinates
    private int row;
    private int col;




    // paints and updates the field
    public void paintField(FieldType fieldType){
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
            MapDisplayPane.getSymbolLayer().getChildren().remove(symbol);
        }

        symbol = SymbolFactory.getSymbol(symbolType, symbolColorStyle);
        symbol.relocate(getLayoutX()-HexField.HEXFIELD_WIDTH*0.78,getLayoutY()-HEXFIELD_HEIGHT*0.8);
        MapDisplayPane.getSymbolLayer().getChildren().add(symbol);
        symbol.toFront();
        setAllMouseInteractions(symbol); // symbol mimics it's hexfield mouse events
    }
    public void removeSymbol(boolean isSymbolRemovingModeOn){
        if(!isSymbolRemovingModeOn){
            return;
        }else if(symbol==null){
            return;
        }
        MapDisplayPane.getSymbolLayer().getChildren().remove(symbol);
        symbol=null;
    }
    public void placeText(String textToPlace){
        if(textToPlace == null || textToPlace.trim().equals("")){
            return;
        }
        if(label != null){
            MapDisplayPane.getTextLayer().getChildren().remove(label);
        }
        // creates a label and places it on textLayer
        label = new Label(textToPlace);
        label.setTextFill(Color.BLACK);
        MapDisplayPane.getTextLayer().getChildren().add(label);

        label.relocate(getLayoutX()-HexField.HEXFIELD_WIDTH*0.55-textToPlace.length()*3.5,getLayoutY()-HEXFIELD_HEIGHT*1.8);
        label.toFront();
        label.setPickOnBounds(false);  // label ignores mouse events
    }
    public void removeText(boolean isTextRemovingModeOn){
        if(!isTextRemovingModeOn){
            return;
        }else if(label==null){
            return;
        }
        MapDisplayPane.getTextLayer().getChildren().remove(label);
        label=null;
    }

    private void updateThisField(){
        paintField(MouseBrushController.getCurrentType());
        placeSymbol(MouseBrushController.getCurrentSymbolType(), MouseBrushController.getCurrentSymbolColorStyle());
        removeSymbol(MouseBrushController.isSymbolRemovingMode());
        placeText(MouseBrushController.getTextToPlace());
        removeText(MouseBrushController.isTextRemovingMode());
    }



    // paints and updates this field and, if needed, nearby fields
    private void updateNeighborFieldIfNotNull(int row, int col){
        if(row < 0 || row >= MapDisplayPane.getCurrentMap().length){
            return;
        } else if(col < 0 || col >= MapDisplayPane.getCurrentMap()[0].length){
            return;
        }
        MapDisplayPane.getCurrentMap()[row][col].updateThisField();
    }
    private void updateFieldsAtBrushLocation(){
        int size = MouseBrushController.getBrushSize();
        if(size <= 1){
            updateBrushSmall();
        } else if(size == 2){
            updateBrushMedium();
        } else if(size == 3){
            updateBrushLarge();
        }
    }

    private void updateBrushSmall(){
        updateThisField();
    }
    private void updateBrushMedium(){
        updateBrushSmall();

        updateNeighborFieldIfNotNull(row-1,col);
        updateNeighborFieldIfNotNull(row+1,col);

        updateNeighborFieldIfNotNull(row,col+1);
        updateNeighborFieldIfNotNull(row,col-1);

        if(col%2!=0){
            updateNeighborFieldIfNotNull(row+1,col+1);
            updateNeighborFieldIfNotNull(row+1,col-1);
        } else {
            updateNeighborFieldIfNotNull(row-1,col+1);
            updateNeighborFieldIfNotNull(row-1,col-1);
        }



    }
    private void updateBrushLarge(){
        updateBrushMedium();

        updateNeighborFieldIfNotNull(row-2,col);
        updateNeighborFieldIfNotNull(row+2,col);

        updateNeighborFieldIfNotNull(row,col+2);
        updateNeighborFieldIfNotNull(row+1,col+2);
        updateNeighborFieldIfNotNull(row-1,col+2);
        updateNeighborFieldIfNotNull(row,col-2);
        updateNeighborFieldIfNotNull(row+1,col-2);
        updateNeighborFieldIfNotNull(row-1,col-2);


        if(col%2!=0){
            updateNeighborFieldIfNotNull(row-1,col+1);
            updateNeighborFieldIfNotNull(row+2,col+1);
            updateNeighborFieldIfNotNull(row+2,col-1);
            updateNeighborFieldIfNotNull(row-1,col-1);
        } else {
            updateNeighborFieldIfNotNull(row-2,col+1);
            updateNeighborFieldIfNotNull(row+1,col+1);
            updateNeighborFieldIfNotNull(row+1,col-1);
            updateNeighborFieldIfNotNull(row-2,col-1);
        }

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
        textHoverOn();

    }
    private void symbolHoverOn(){
        if(symbol!=null){
            symbol.setScaleX(SCALE_HOVER_ON);
            symbol.setScaleY(SCALE_HOVER_ON);
            symbol.toFront();
        }
    }
    private void textHoverOn(){
        if(label !=null){
            label.setScaleX(SCALE_HOVER_ON);
            label.setScaleY(SCALE_HOVER_ON);
            label.toFront();
        }
    }
    


    private void hoverOff(){
        setScaleX(SCALE_HOVER_OFF);
        setScaleY(SCALE_HOVER_OFF);
        setStrokeWidth(STROKE_WIDTH_HOVER_OFF);
        symbolHoverOff();
        textHoverOff();
    }
    private void symbolHoverOff(){
        if(symbol!=null){
            symbol.setScaleX(SCALE_HOVER_OFF);
            symbol.setScaleY(SCALE_HOVER_OFF);
        }
    }
    private void textHoverOff(){
        if(label !=null){
            label.setScaleX(SCALE_HOVER_OFF);
            label.setScaleY(SCALE_HOVER_OFF);
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
            updateFieldsAtBrushLocation();
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
            updateFieldsAtBrushLocation();
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

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }
}
