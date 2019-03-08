package HexMapBuilder.controllers;

import HexMapBuilder.mapDisplayPane.hexFields.FieldType;
import HexMapBuilder.mapDisplayPane.hexFields.symbols.SymbolColorStyle;
import HexMapBuilder.mapDisplayPane.hexFields.symbols.SymbolType;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MouseBrushController {

    private static FieldType currentType;

    private static SymbolType currentSymbolType;

    private static SymbolColorStyle currentSymbolColorStyle;

    private static int brushSize;

    private static boolean symbolRemovingMode;


    @PostConstruct
    private static void init(){
        //default values
        currentType = FieldType.SEA;
        brushSize = 1;
    }




    // getters and setters
    public static FieldType getCurrentType() {
        return currentType;
    }
    public static void setCurrentType(FieldType currentType) {
        MouseBrushController.currentType = currentType;
    }


    public static SymbolType getCurrentSymbolType() {
        return currentSymbolType;
    }
    public static void setCurrentSymbolType(SymbolType currentSymbolType) {
        MouseBrushController.currentSymbolType = currentSymbolType;
    }

    public static SymbolColorStyle getCurrentSymbolColorStyle() {
        return currentSymbolColorStyle;
    }
    public static void setCurrentSymbolColorStyle(SymbolColorStyle currentSymbolColorStyle) {
        MouseBrushController.currentSymbolColorStyle = currentSymbolColorStyle;
    }

    public static int getBrushSize() {
        return brushSize;
    }
    public static void setBrushSize(int brushSize) {
        MouseBrushController.brushSize = brushSize;
    }

    public static boolean isSymbolRemovingMode() {
        return symbolRemovingMode;
    }
    public static void setSymbolRemovingMode(boolean symbolRemovingMode) {
        MouseBrushController.symbolRemovingMode = symbolRemovingMode;
    }
}
