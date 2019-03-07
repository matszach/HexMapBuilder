package HexMapBuilder.mapDisplayPane.hexFields.symbols;

import javafx.scene.shape.Polygon;

public class Symbol extends Polygon {

    private SymbolType symbolType;
    private SymbolColorStyle symbolColorStyle;

    public Symbol(double[] doubles){
        super(doubles);
    }


    // getters and setters
    public SymbolType getSymbolType() {
        return symbolType;
    }

    public void setSymbolType(SymbolType symbolType) {
        this.symbolType = symbolType;
    }

    public SymbolColorStyle getSymbolColorStyle() {
        return symbolColorStyle;
    }

    public void setSymbolColorStyle(SymbolColorStyle symbolColorStyle) {
        this.symbolColorStyle = symbolColorStyle;
    }
}
