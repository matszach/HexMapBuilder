package HexMapBuilder.sideChoicePanelComponents.brushSizeChoice;

import HexMapBuilder.controllers.MouseBrushController;
import javafx.scene.layout.HBox;

public class BrushSizeChooserPane extends HBox {


    private static CircleButton smallBrushMode = new CircleButton(8);
    private static CircleButton mediumBrushMode = new CircleButton(12);
    private static CircleButton largeBrushMode = new CircleButton(16);



    public BrushSizeChooserPane(){
        setPrefHeight(Integer.MAX_VALUE);
        placeCircleButtons();
        equipWithActions();
        setDefault();

    }

    private void placeCircleButtons(){
        getChildren().addAll(smallBrushMode,mediumBrushMode,largeBrushMode);
    }

    private void equipWithActions(){
        smallBrushMode.setOnMousePressed(e->{
            smallBrushMode.selectOn();
            mediumBrushMode.selectOff();
            largeBrushMode.selectOff();
            MouseBrushController.setBrushSize(1);
        });

        mediumBrushMode.setOnMousePressed(e->{
            smallBrushMode.selectOff();
            mediumBrushMode.selectOn();
            largeBrushMode.selectOff();
            MouseBrushController.setBrushSize(2);
        });

        largeBrushMode.setOnMousePressed(e->{
            smallBrushMode.selectOff();
            mediumBrushMode.selectOff();
            largeBrushMode.selectOn();
            MouseBrushController.setBrushSize(3);
        });



    }

    private void setDefault(){
        if(MouseBrushController.getBrushSize()==1){
            smallBrushMode.selectOn();
        } else if (MouseBrushController.getBrushSize()==2){
            mediumBrushMode.selectOn();
        } else {
            largeBrushMode.selectOn();
        }
    }









}
