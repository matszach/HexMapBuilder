package HexMapBuilder.controllers;

import HexMapBuilder.mapDisplayPane.MapDisplayPane;
import HexMapBuilder.mapDisplayPane.hexFields.FieldType;
import HexMapBuilder.mapSaving.MapSaverAndLoader;
import HexMapBuilder.mapSaving.MapSerializable;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Controller {

    @FXML
    private BorderPane mainPane;





    // == methods ==


    // SAVE
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

    // LOAD
    @FXML
    public void loadMap(){
        toPrintableScale();
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


    // NEW
    private void drawNew(FieldType fieldType, int height, int width){
        scaleView100();
        MapDisplayPane.drawDefaultMap(fieldType,height,width);
    }

    @FXML
    public void drawNewMapVS(){
        drawNew(FieldType.SEA,20,30);
    }
    @FXML
    public void drawNewMapS(){
        drawNew(FieldType.SEA,40,60);
    }
    @FXML
    public void drawNewMapM(){
        drawNew(FieldType.SEA,60,90);
    }
    @FXML
    public void drawNewMapL(){
        drawNew(FieldType.SEA,80,120);
    }
    @FXML
    public void drawNewMapVL(){
        drawNew(FieldType.SEA,100,150);
    }


    // SCALE TODO correct layout movement within ScrollPane after scaling
    private Pane mapPane = MapDisplayPane.getMapPane();
    private double currentScale = 1;

    private void toPrintableScale(){
        mapPane.setScaleX(1);
        mapPane.setScaleY(1);
        mapPane.setTranslateX(0);
        mapPane.setTranslateY(0);
    }

    private void scaleMapPane(double scale){
        currentScale = scale;
        mapPane.setScaleX(scale);
        mapPane.setScaleY(scale);
        mapPane.setTranslateX( - (1-scale)*mapPane.getWidth()/2 );
        mapPane.setTranslateY( - (1-scale)*mapPane.getHeight()/2 );
    }
    @FXML
    public void scaleView25(){
        scaleMapPane(0.25);
    }
    @FXML
    public void scaleView50(){
        scaleMapPane(0.5);
    }
    @FXML
    public void scaleView75(){
        scaleMapPane(0.75);
    }
    @FXML
    public void scaleView100(){
        scaleMapPane(1);
    }
    @FXML
    public void scaleView125(){
        scaleMapPane(1.25);
    }
    @FXML
    public void scaleView150(){
        scaleMapPane(1.5);
    }


    // EXPORT
    @FXML
    public void exportMapToPng(){
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File("SavedMaps\\"));
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG", "*.png"));
        File file = chooser.showSaveDialog(null);
        if(file == null) {
            return; // chooser cancelled
        }
        // resize pane view for consistent png exports
        toPrintableScale();
        try {
            Pane castedPane = MapDisplayPane.getMapPane();
            WritableImage writableImage = new WritableImage((int)castedPane.getWidth() + 20,
                    (int)castedPane.getHeight() + 20);
            castedPane.snapshot(null, writableImage);
            RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
            ImageIO.write(renderedImage, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // brings back original scale
            scaleMapPane(currentScale);
        }
    }

}
