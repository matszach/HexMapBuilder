package HexMapBuilder.controllers;

import HexMapBuilder.mapDisplayPane.MapDisplayPane;
import HexMapBuilder.mapDisplayPane.hexFields.FieldType;
import HexMapBuilder.mapSaving.MapSaverAndLoader;
import HexMapBuilder.mapSaving.MapSerializable;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

import java.io.File;

public class Controller {

    @FXML
    private BorderPane mainPane;





    // methods
    @FXML
    public void saveMap(){
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File("SavedMaps\\"));
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("MAP","*.map"));
        File file = chooser.showSaveDialog(mainPane.getScene().getWindow());
        if(file==null){
            return; // chooser cancelled
        }
        MapSerializable ms = new MapSerializable(MapDisplayPane.getCurrentMap());
        MapSaverAndLoader.saveToFile(ms,file);
    }


    @FXML
    public void loadMap(){
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File("SavedMaps\\"));
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("MAP","*.map"));
        File file = chooser.showOpenDialog(mainPane.getScene().getWindow());
        if(file==null){
            return; // chooser cancelled
        }
        MapSerializable ms = MapSaverAndLoader.loadFromFile(file);
        MapDisplayPane.drawFromMapSerializable(ms);
    }

    @FXML
    public void drawNewMap(){
        MapDisplayPane.drawDefaultMap(FieldType.SEA, 100, 100);
    }



}
