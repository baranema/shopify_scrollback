package game;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.net.URL;

public class CyberController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private Button undoBtn;
    @FXML
    private Label sequences;
    @FXML
    private Label bufferLabel;
    @FXML
    private Label bufferRow;
    @FXML
    private GridPane valueTable;

    private static final Level level = new Level();
    private static boolean vertical = false;
    private static boolean firstTile = true;

    public static void startTimer(){
    }

    public static void loadLevel() throws FileNotFoundException {
        level.loadLevel();
    }

    public void chooseTile(Tile tile, int index) {
        if(firstTile){
            startTimer();
            firstTile = false;
        }

        if(!(level.buffer.isFull())){
            level.buffer.receiveTile(tile);

            level.grid.crossTile(tile);
            vertical = !vertical;
            highlight(index);
            if (level.buffer.isFull()){
                level.grid.deactivateGrid();
            }
        }
    }

    public static void highlight(int index) {
        if(vertical){
            for(int i=0; i<level.grid.getGrid().length; i++){
                level.grid.getGrid()[i][index].setActive(true);
            }
        }
        else {
            for(int i=0; i<level.grid.getGrid().length; i++){
                level.grid.getGrid()[index][i].setActive(true);
            }
        }
    }

    public static void undo(){
        level.buffer.removeTile();
    }

    public void colorGrid() {
        for (int i = 0; i < level.grid.getSize() * level.grid.getSize(); i++) {
            Button cell = (Button) valueTable.getChildren().get(i);

            Tile thisTile = level.grid.getGrid()[GridPane.getRowIndex(cell)-1][GridPane.getColumnIndex(cell) - 1];

            if (level.buffer.nextEmpty() == 0) {
                cell.setStyle("-fx-text-fill: #fff; -fx-background-color: #000;");
                cell.setDisable(false);
            } else {
                if (thisTile.isActive() && thisTile.isValid()) {
                    cell.setStyle("-fx-text-fill: #000; -fx-background-color: #9da327;");
                    cell.setDisable(false);
                } else if (!thisTile.isValid() && thisTile.isActive()) {
                    cell.setStyle("-fx-text-fill: #000; -fx-background-color: #f3ff00;");
                    cell.setDisable(true);
                } else {
                    cell.setStyle("-fx-text-fill: #fff; -fx-background-color: #000;");
                    cell.setDisable(true);
                }
            }
        }
    }

    public void printBuffer() {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < level.buffer.getbuffer().length; i++) {
            buffer.append(level.buffer.getbuffer()[i].getContent()).append(" ");
        }
        bufferRow.setText(buffer.toString().toUpperCase(Locale.ROOT));
    }

    public void printSequences() {
        StringBuilder str = new StringBuilder();
        for (Sequence seq : level.sequences) {
            for (Tile tile : seq.getSequence()) {
                str.append(tile.getContent()).append(" ");
            }
            str.append("\n");
        }
        sequences.setText(str.toString().toUpperCase(Locale.ROOT));
    }

    public void removeActive() {
        for (int i = 0; i < level.grid.getSize() * level.grid.getSize(); i++) {
            Button cell = (Button) valueTable.getChildren().get(i);

            Tile tile = level.grid.getGrid()[GridPane.getRowIndex(cell)-1][GridPane.getColumnIndex(cell) - 1];
            if (tile.isActive()) {
                tile.setActive(false);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cancelButton.setStyle("-fx-text-fill: #fff; -fx-background-color: #870000;");
        undoBtn.setStyle("-fx-text-fill: #fff; -fx-background-color: #870000;");

        bufferLabel.setVisible(false);

        try {
            loadLevel();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int k = 0;
        for (int i = 0; i < level.grid.getSize(); i++) {
            for (int j = 0; j < level.grid.getSize(); j++) {
                if (k != level.grid.getSize() * level.grid.getSize()) {
                    Button cell = (Button) valueTable.getChildren().get(k++);
                    cell.setText(level.grid.getGrid()[i][j].getContent().toUpperCase(Locale.ROOT));
                    cell.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
                }
            }
        }
        printSequences();
    }

    public void clickEvent(ActionEvent event) {
        bufferLabel.setVisible(true);

        removeActive();

        Button clickedButton = (Button) event.getSource();
        Tile clickedTile = level.grid.getGrid()[GridPane.getRowIndex(clickedButton)-1][GridPane.getColumnIndex(clickedButton) - 1];

        clickedTile.setX(GridPane.getRowIndex(clickedButton)-1);
        clickedTile.setY(GridPane.getColumnIndex(clickedButton) - 1);

        if (vertical) {
            chooseTile( clickedTile, GridPane.getRowIndex(clickedButton)-1);
        } else {
            chooseTile(clickedTile, GridPane.getColumnIndex(clickedButton)-1);
        }

        printBuffer();
        colorGrid();
     }

    public void undoButtonOnAction() throws FileNotFoundException {
        if (!level.buffer.getbuffer()[0].getContent().equals("")) {
            undo();
            removeActive();

            if (level.buffer.nextEmpty() == 0) {
                bufferLabel.setVisible(false);
                loadLevel();
            } else {
                Tile thisTile = level.buffer.getbuffer()[level.buffer.nextEmpty()+1];
                Tile clickedTile = level.grid.getGrid()[thisTile.getX()][thisTile.getY()];

                if (vertical) {
                    vertical = false;
                    highlight(thisTile.getX());
                } else {
                    vertical = true;
                    highlight(thisTile.getY());
                }

                clickedTile.setValid(true);
            }
            colorGrid();
        }
        printBuffer();
    }

    public void cancelButtonOnAction() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
