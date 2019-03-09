package HexMapBuilder.sideChoicePanelComponents.textPlacementChoice;

import CustomFXNodes.AppButton;
import HexMapBuilder.controllers.MouseBrushController;
import HexMapBuilder.mapDisplayPane.hexFields.symbols.Symbol;
import HexMapBuilder.sideChoicePanelComponents.fieldTypeChoice.FieldChooserPane;
import HexMapBuilder.sideChoicePanelComponents.symbolTypeChoice.SymbolChooserPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class TextPlacementChoicePane extends VBox {

    private static TextField textField = new TextField();
    private static AppButton placeTextAppButton = new AppButton("Place text");
    private static AppButton clearTextAppButton = new AppButton("Clear text");


    private static void equipComponentsWithActions(){
        textField.textProperty().addListener(((obsVal, oldVal, newVal) ->{
            FieldChooserPane.fieldPaintingModeOff();
            SymbolChooserPane.symbolPlacementModeOff();
            MouseBrushController.setTextRemovingMode(false);
            MouseBrushController.setTextToPlace(newVal);
            placeTextAppButton.buttonOn();
            clearTextAppButton.buttonOff();
        }));
        placeTextAppButton.setOnAction(e->{
            FieldChooserPane.fieldPaintingModeOff();
            SymbolChooserPane.symbolPlacementModeOff();
            MouseBrushController.setTextRemovingMode(false);
            MouseBrushController.setTextToPlace(textField.getText());
            placeTextAppButton.buttonOn();
            clearTextAppButton.buttonOff();
        });
        clearTextAppButton.setOnAction(e->{
            FieldChooserPane.fieldPaintingModeOff();
            SymbolChooserPane.symbolPlacementModeOff();
            MouseBrushController.setTextRemovingMode(true);
            MouseBrushController.setTextToPlace(null);
            placeTextAppButton.buttonOff();
            clearTextAppButton.buttonOn();
        });
    }

    public static void textPlacementModeOff(){
        placeTextAppButton.buttonOff();
        placeTextAppButton.buttonOff();
        textField.setText("");
        MouseBrushController.setTextToPlace(null);
        MouseBrushController.setTextRemovingMode(false);
    }


    public TextPlacementChoicePane(){
        equipComponentsWithActions();
        getChildren().addAll(textField,placeTextAppButton,clearTextAppButton);
    }

}
