package HexMapBuilder.mapSaving;

import HexMapBuilder.mapDisplayPane.hexFields.FieldType;
import HexMapBuilder.mapDisplayPane.hexFields.HexField;
import HexMapBuilder.mapDisplayPane.hexFields.symbols.SymbolColorStyle;
import HexMapBuilder.mapDisplayPane.hexFields.symbols.SymbolType;
import lombok.Getter;

import java.io.Serializable;


@Getter
public class MapSerializable implements Serializable {
    private FieldType[][] typeMap;
    private SymbolType[][] symbolTypeMap;
    private SymbolColorStyle[][] symbolColorStyleMap;
    private String[][] textMap;


    public MapSerializable(HexField[][]fieldMap){
        typeMap = new FieldType[fieldMap.length][fieldMap[0].length];
        symbolTypeMap = new SymbolType[fieldMap.length][fieldMap[0].length];
        symbolColorStyleMap = new SymbolColorStyle[fieldMap.length][fieldMap[0].length];
        textMap = new String[fieldMap.length][fieldMap[0].length];
        for(int row = 0; row < fieldMap.length; row++){
            for(int col = 0; col < fieldMap[0].length; col++){
                typeMap[row][col] = fieldMap[row][col].getFieldType();
                if(fieldMap[row][col].getSymbol()!=null){
                    symbolTypeMap[row][col] = fieldMap[row][col].getSymbol().getSymbolType();
                    symbolColorStyleMap[row][col] =  fieldMap[row][col].getSymbol().getSymbolColorStyle();
                }
                if(fieldMap[row][col].getLabel() != null){
                    textMap[row][col] = fieldMap[row][col].getLabel().getText();
                }
            }
        }
    }

}
