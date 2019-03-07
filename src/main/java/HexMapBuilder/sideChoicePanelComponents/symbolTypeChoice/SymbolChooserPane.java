package HexMapBuilder.sideChoicePanelComponents.symbolTypeChoice;

import HexMapBuilder.controllers.MouseBrushController;
import HexMapBuilder.mapDisplayPane.hexFields.HexField;
import HexMapBuilder.mapDisplayPane.hexFields.symbols.Symbol;
import HexMapBuilder.mapDisplayPane.hexFields.symbols.SymbolColorStyle;
import HexMapBuilder.mapDisplayPane.hexFields.symbols.SymbolFactory;
import HexMapBuilder.mapDisplayPane.hexFields.symbols.SymbolType;
import HexMapBuilder.sideChoicePanelComponents.fieldTypeChoice.FieldChooserPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class SymbolChooserPane extends Pane {

    private final static double OFFSET = 5;

    private static List<Symbol> symbolsAsButtons = new ArrayList<>();



    private void loadAllFieldTypes(){
        for(SymbolType symbolType:SymbolType.values()){
            for(SymbolColorStyle symbolColorStyle:SymbolColorStyle.values()) {
                symbolsAsButtons.add(SymbolFactory.getSymbol(symbolType,symbolColorStyle));
            }
        }
    }

    private void equipHexButtons(){
        for(Symbol symbol: symbolsAsButtons){
            symbol.setOnMouseClicked(e-> {
                FieldChooserPane.allHexesOff();
                MouseBrushController.setCurrentType(null);
                MouseBrushController.setCurrentSymbolType(symbol.getSymbolType());
                MouseBrushController.setCurrentSymbolColorStyle(symbol.getSymbolColorStyle());
                allSymbolsOff();


                // temp
                symbol.setStrokeWidth(2);
                symbol.setScaleX(1.08);
                symbol.setScaleY(1.08);
            });
        }
    }

    public static void allSymbolsOff(){
        for(Symbol symbol:symbolsAsButtons){
            symbol.setStrokeWidth(1);
            symbol.setScaleX(1.00);
            symbol.setScaleY(1.00);
        }
    }


    private void placeSymbols(){
        symbolsAsButtons.forEach(this::placeOneSymbol); // method reference
    }

    private void placeOneSymbol(Symbol symbol){

        int index = symbolsAsButtons.indexOf(symbol);

        int rowNum = index/3;
        int colNum = index%3;

        double locX = colNum * HexField.HEXFIELD_WIDTH + OFFSET;

        double locY = rowNum * (HexField.HEXFIELD_HEIGHT*1.33);

        symbol.relocate(locX,locY);
        getChildren().add(symbol);
    }



    public SymbolChooserPane(){
        setPrefHeight(Integer.MAX_VALUE);
        loadAllFieldTypes();
        equipHexButtons();
        placeSymbols();
    }














}
