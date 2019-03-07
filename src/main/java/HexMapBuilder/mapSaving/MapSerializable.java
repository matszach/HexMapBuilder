package HexMapBuilder.mapSaving;

import HexMapBuilder.mapDisplayPane.hexFields.FieldType;
import HexMapBuilder.mapDisplayPane.hexFields.HexField;

import java.io.Serializable;

public class MapSerializable implements Serializable {
    private FieldType[][] typeMap;

    public FieldType[][] getTypeMap() {
        return typeMap;
    }

    public MapSerializable(HexField[][]fieldMap){
        typeMap = new FieldType[fieldMap.length][fieldMap[0].length];
        for(int row = 0; row < fieldMap.length; row++){
            for(int col = 0; col < fieldMap[0].length; col++){
                typeMap[row][col] = fieldMap[row][col].getFieldType();
            }
        }
    }

}
