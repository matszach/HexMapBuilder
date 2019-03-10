package HexMapBuilder.mapDisplayPane.hexFields.symbols;

import javafx.scene.shape.Polygon;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Symbol extends Polygon {

    private SymbolType symbolType;
    private SymbolColorStyle symbolColorStyle;

    public Symbol(double[] doubles){
        super(doubles);
    }

}
