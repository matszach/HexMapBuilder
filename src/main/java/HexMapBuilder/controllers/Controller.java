package HexMapBuilder.controllers;

import HexMapBuilder.mapDisplayPane.MapDisplayPane;
import HexMapBuilder.mapDisplayPane.hexFields.FieldType;
import HexMapBuilder.mapSaving.MapSaverAndLoader;
import HexMapBuilder.mapSaving.MapSerializable;
import javafx.fxml.FXML;

public class Controller {







    // methods
    @FXML
    public void saveMap(){
        MapSerializable ms = new MapSerializable(MapDisplayPane.getCurrentMap());
        MapSaverAndLoader.save(ms, "map1");
    }


    @FXML
    public void loadMap(){
        MapSerializable ms = MapSaverAndLoader.load("map1");
        MapDisplayPane.drawFromMapSerializable(ms);
    }

    @FXML
    public void drawNewMap(){
        MapDisplayPane.drawDefaultMap(FieldType.SEA, 100, 100);
    }



}
