package HexMapBuilder.sideChoicePanelComponents.symbolTypeChoice;

import HexMapBuilder.controllers.MouseBrushController;
import HexMapBuilder.mapDisplayPane.hexFields.symbols.Symbol;
import HexMapBuilder.mapDisplayPane.hexFields.symbols.SymbolColorStyle;
import HexMapBuilder.mapDisplayPane.hexFields.symbols.SymbolFactory;
import HexMapBuilder.mapDisplayPane.hexFields.symbols.SymbolType;
import HexMapBuilder.sideChoicePanelComponents.fieldTypeChoice.FieldChooserPane;
import HexMapBuilder.sideChoicePanelComponents.textPlacementChoice.TextPlacementChoicePane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class SymbolChooserPane extends Pane {


    private final static double OFFSET = 5;

    private static List<Symbol> symbolsAsButtons = new ArrayList<>();
    private static NullSymbol nullSymbol = new NullSymbol();



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
                FieldChooserPane.fieldPaintingModeOff();
                TextPlacementChoicePane.textPlacementModeOff();
                MouseBrushController.setSymbolRemovingMode(false);
                MouseBrushController.setCurrentSymbolType(symbol.getSymbolType());
                MouseBrushController.setCurrentSymbolColorStyle(symbol.getSymbolColorStyle());
                allSymbolsOff();

                // temp TODO
                symbol.setStrokeWidth(2);
                symbol.setScaleX(1.08);
                symbol.setScaleY(1.08);
            });
        }
    }

    public static void allSymbolsOff(){
        for(Symbol symbol:symbolsAsButtons){
            // temp TODO
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

        int rowNum = index/SymbolColorStyle.values().length;
        int colNum = index%SymbolColorStyle.values().length;

        double locX = colNum * (SymbolFactory.DS_WIDTH *1.2) + OFFSET;
        double locY = rowNum * (SymbolFactory.DS_HEIGHT *1.5);

        symbol.relocate(locX,locY);
        getChildren().add(symbol);
    }


    public static void symbolPlacementModeOff(){
        allSymbolsOff();
        MouseBrushController.setSymbolRemovingMode(false);
        MouseBrushController.setCurrentSymbolType(null);
        MouseBrushController.setCurrentSymbolColorStyle(null);
    }




    public SymbolChooserPane(){
        loadAllFieldTypes();
        equipHexButtons();
        symbolsAsButtons.add(nullSymbol);
        placeSymbols();
    }














}
