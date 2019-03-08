package HexMapBuilder.controllers;

import HexMapBuilder.mapDisplayPane.MapDisplayPane;
import HexMapBuilder.mapDisplayPane.hexFields.FieldType;
import HexMapBuilder.mapSaving.MapSaverAndLoader;
import HexMapBuilder.mapSaving.MapSerializable;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

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
    public void drawNewMapVS(){
        //TEMP
        MapDisplayPane.drawDefaultMap(FieldType.SEA,20,30);
    }

    @FXML
    public void drawNewMapS(){
        //TEMP
        MapDisplayPane.drawDefaultMap(FieldType.SEA,40,60);
    }

    @FXML
    public void drawNewMapM(){
        //TEMP
        MapDisplayPane.drawDefaultMap(FieldType.SEA,60,90);
    }

    @FXML
    public void drawNewMapL(){
        //TEMP
        MapDisplayPane.drawDefaultMap(FieldType.SEA,80,120);
    }

    @FXML
    public void drawNewMapVL(){
        //TEMP
        MapDisplayPane.drawDefaultMap(FieldType.SEA,100,150);
    }

    @FXML
    public void exportMapToPng(){
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File("SavedMaps\\"));
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG", "*.png"));
        File file = chooser.showSaveDialog(null);
        if(file == null) {
            return; // chooser cancelled
        }
        try {
            Pane castedPane = MapDisplayPane.getMapPane();
            WritableImage writableImage = new WritableImage((int)castedPane.getWidth() + 20,
                    (int)castedPane.getHeight() + 20);
            castedPane.snapshot(null, writableImage);
            RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
            ImageIO.write(renderedImage, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
