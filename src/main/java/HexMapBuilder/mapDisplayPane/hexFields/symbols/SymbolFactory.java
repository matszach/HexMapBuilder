package HexMapBuilder.mapDisplayPane.hexFields.symbols;

import HexMapBuilder.mapDisplayPane.hexFields.HexField;
import javafx.scene.paint.Color;
import org.springframework.stereotype.Component;


@Component
public class SymbolFactory {

    // desired symbol width and height
    public final static double DS_WIDTH = HexField.HEXFIELD_WIDTH/2;
    public final static double DS_HEIGHT = HexField.HEXFIELD_HEIGHT/2;
    
    private final static double[] POINTS_FOR_CROWN = new double[]{
            0,0,
            0,-DS_HEIGHT,
            DS_WIDTH /4,-DS_HEIGHT /2,
            DS_WIDTH /2,-DS_HEIGHT,
            DS_WIDTH /4*3,-DS_HEIGHT /2,
            DS_WIDTH,-DS_HEIGHT,
            DS_WIDTH,0};

    private final static double[] POINTS_FOR_CASTLE = new double[]{
            0,0,
            0,-DS_HEIGHT,
            DS_WIDTH /5,-DS_HEIGHT,
            DS_WIDTH /5,-DS_HEIGHT /3*2,
            DS_WIDTH /5*2,-DS_HEIGHT /3*2,
            DS_WIDTH /5*2,-DS_HEIGHT,
            DS_WIDTH /5*3,-DS_HEIGHT,
            DS_WIDTH /5*3,-DS_HEIGHT /3*2,
            DS_WIDTH /5*4,-DS_HEIGHT /3*2,
            DS_WIDTH /5*4,-DS_HEIGHT,
            DS_WIDTH /5*5,-DS_HEIGHT,
            DS_WIDTH,0};

    private final static double[] POINTS_FOR_SQUARE = new double[]{
            0,0,
            0,-DS_HEIGHT,
            DS_WIDTH,-DS_HEIGHT,
            DS_WIDTH,0};

    private final static double[] POINTS_FOR_TRIANGLE = new double[]{
            0,0,
            DS_WIDTH /2,-DS_HEIGHT,
            DS_WIDTH,0};

    private final static double[] POINTS_FOR_RHOMBUS = new double[]{
            DS_WIDTH/2,0,
            0,-DS_HEIGHT/2,
            DS_WIDTH/2,-DS_HEIGHT,
            DS_WIDTH,-DS_HEIGHT/2};


    private static Symbol currentSymbol;


    private static double[] getPointPattern(SymbolType symbolType){
        switch (symbolType){
            case CROWN: return POINTS_FOR_CROWN;
            case CASTLE: return POINTS_FOR_CASTLE;
            case SQUARE: return POINTS_FOR_SQUARE;
            case TRIANGLE: return POINTS_FOR_TRIANGLE;
            case RHOMBUS: return  POINTS_FOR_RHOMBUS;
            default: return POINTS_FOR_SQUARE; // fixme?
        }
    }


    private static void paintSymbol(Color bodyColor, Color strokeColor){
        currentSymbol.setFill(bodyColor);
        currentSymbol.setStroke(strokeColor);
    }


    private static void paintSymbolByStyle(SymbolColorStyle symbolColorStyle){
        switch (symbolColorStyle){
            case BLACK: paintSymbol(Color.BLACK, Color.GRAY); break;
            case BLUE: paintSymbol(Color.BLUE, Color.BLACK); break;
            case RED: paintSymbol(Color.RED, Color.BLACK); break;
            case GREEN: paintSymbol(Color.GREEN, Color.BLACK); break;
            case YELLOW: paintSymbol(Color.YELLOW, Color.BLACK); break;
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
