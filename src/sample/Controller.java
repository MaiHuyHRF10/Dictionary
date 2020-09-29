package sample;

import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.w3c.dom.Text;

import java.net.URL;
import java.util.*;


public class Controller implements Initializable {

    @FXML
    Button search;

    @FXML
    TextField textSearch;

    @FXML
    TextArea result;

    @FXML
    ListView<String> words;

    public Dictionary myDictionary = new Dictionary();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textSearch.setPromptText("Searching...");
        search.setOnAction(event -> {
            String wordSearch = textSearch.getText();
            if (wordSearch != null && wordSearch.equals("") == false) {
                String wordMeaning = myDictionary.dictionaryLookup(wordSearch);
                result.setText(wordMeaning);
            }
        });

        this.initializeWordList();

        words.setOnMouseClicked(event ->{
            String wordClick = words.getSelectionModel().getSelectedItem();
            textSearch.setText(wordClick);
            if (wordClick != null && wordClick.equals("") == false) {
                String wordMeaning = myDictionary.dictionaryLookup(wordClick);
                result.setText(wordMeaning);
            }
        });

    }

    public void initializeWordList() {
        myDictionary.insertFromFile();
        for (int i = 0; i < 26; i++) {
            int size = myDictionary.getLibraryAt(i).getSize();
            for (int j = 0; j < size; j++) {
                words.getItems().add(myDictionary.getWord(i, j).getWordTarget());
            }
        }
    }

    /** public void addANewWord() {
        Dialog<Word> dialog = new Dialog<>();
        dialog.setTitle("Add a new word");
        dialog.setHeaderText("English");

        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField wordTarget = new TextField();
        wordTarget.setPromptText("Word target");
        TextField wordExplain = new TextField();
        wordExplain.setPromptText("Word explain");

        grid.add(new Label("Word target:"), 0, 0);
        grid.add(wordTarget, 1, 0);
        grid.add(new Label("Word explain:"), 0, 1);
        grid.add(wordExplain, 1, 1);

        Node loginButton = dialog.getDialogPane().lookupButton(addButtonType);
        loginButton.setDisable(true);

        wordTarget.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });
        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType){
                return new Word(wordTarget.getText(), wordExplain.getText());
            }
            return null;
        });
        Optional<Word> result = dialog.showAndWait();
        result.ifPresent(newWord -> {
            myDictionary.addWord(new Word(newWord.getWordTarget(), newWord.getWordExplain()));
        });
        words.getItems().add(wordTarget.getText());
    } */

}
