package HexMapBuilder.mapDisplayPane.hexFields.symbols;

import HexMapBuilder.mapDisplayPane.hexFields.HexField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class SymbolFactory {
    
    public final static double DESIRED_SYMBOL_WIDTH = HexField.HEXFIELD_WIDTH/2;
    
    private final static double[] POINTS_FOR_CROWN = new double[]{0,0,  0,DESIRED_SYMBOL_WIDTH,  DESIRED_SYMBOL_WIDTH,DESIRED_SYMBOL_WIDTH,  DESIRED_SYMBOL_WIDTH,0};
    private final static double[] POINTS_FOR_CASTLE = new double[]{0,0,  0,DESIRED_SYMBOL_WIDTH,  DESIRED_SYMBOL_WIDTH,DESIRED_SYMBOL_WIDTH,  DESIRED_SYMBOL_WIDTH,0};
    private final static double[] POINTS_FOR_TOWN = new double[]{0,0,  0,DESIRED_SYMBOL_WIDTH,  DESIRED_SYMBOL_WIDTH,DESIRED_SYMBOL_WIDTH,  DESIRED_SYMBOL_WIDTH,0};


    private static Symbol currentSymbol;


    private static double[] getPointPattern(SymbolType symbolType){
        switch (symbolType){
            case CROWN: return POINTS_FOR_CROWN;
            case CASTLE: return POINTS_FOR_CASTLE;
            case TOWN: return POINTS_FOR_TOWN;
            default: return POINTS_FOR_TOWN; // fixme?
        }
    }


    private static void paintSymbol(Color bodyColor, Color strokeColor){
        currentSymbol.setFill(bodyColor);
        currentSymbol.setStroke(strokeColor);
    }


    private static void paintSymbolByStyle(SymbolColorStyle symbolColorStyle){
        switch (symbolColorStyle){
            case BLACK: paintSymbol(Color.BLACK, Color.WHITE); break;
            case BLUE: paintSymbol(Color.BLUE, Color.BLACK); break;
            case RED: paintSymbol(Color.RED, Color.BLACK); break;
            default: break;
        }
    }


    public static Symbol getSymbol(SymbolType symbolType, SymbolColorStyle symbolColorStyle){
        currentSymbol = new Symbol(getPointPattern(symbolType));
        currentSymbol.setSymbolType(symbolType);
        paintSymbolByStyle(symbolColorStyle);
        currentSymbol.setSymbolColorStyle(symbolColorStyle);
        return currentSymbol;
    }















}
