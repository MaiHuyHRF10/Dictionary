package sample;

import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
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

import java.io.IOException;
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

    @FXML
    Button translate;

<<<<<<< HEAD
    @FXML
    Button voice;

=======
>>>>>>> 7a9807447741fd8da269454b0585129606fc5508
    public Dictionary myDictionary = new Dictionary();

    public ObservableList names = FXCollections.observableArrayList();

    public Translate Google = new Translate();
<<<<<<< HEAD
    public Voice speak = new Voice();
=======
>>>>>>> 7a9807447741fd8da269454b0585129606fc5508

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textSearch.setPromptText("Searching...");
        search.setOnAction(event -> {
            String wordSearch = textSearch.getText();
            if (wordSearch != null && wordSearch.equals("") == false) {
                int size = myDictionary.getLibraryAt(wordSearch.charAt(0) - 97).getSize();
                String wordMeaning = myDictionary.dictionaryLookup(wordSearch, 0, size);
                result.setText(wordMeaning);
                ArrayList<Word> temp = myDictionary.dictionarySearcher(wordSearch);
                names.clear();
                for (int i = 0; i < temp.size(); i++) {
                    names.add(temp.get(i).getWordTarget());
                }
                words.setItems(names);
            } else {
                result.setText("No data");
            }
        });

        this.initializeWordList();

        words.setOnMouseClicked(event -> {
            String wordClick = words.getSelectionModel().getSelectedItem();
            textSearch.setText(wordClick);
            if (wordClick != null && wordClick.equals("") == false) {
                int size = myDictionary.getLibraryAt(wordClick.charAt(0) - 97).getSize();
                String wordMeaning = myDictionary.dictionaryLookup(wordClick, 0, size);
                result.setText(wordMeaning);
            }
        });

        translate.setOnAction(e -> {
            String wordTarget = textSearch.getText();
            String wordExplain = null;
            try {
                wordExplain = Google.translate("en", "vi", wordTarget);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            result.setText(wordExplain + "\n" + "Translate by Google API");
        });
<<<<<<< HEAD

        voice.setOnAction(e -> {
            String wordTarget = textSearch.getText();
            speak.sayMultiple(wordTarget);
        });

=======
>>>>>>> 7a9807447741fd8da269454b0585129606fc5508
    }

    public void initializeWordList() {
        myDictionary.insertFromFile();
        for (int i = 0; i < 26; i++) {
            int size = myDictionary.getLibraryAt(i).getSize();
            for (int j = 0; j < size; j++) {
                words.getItems().add(myDictionary.getWord(i, j).getWordTarget());
                names.add(myDictionary.getWord(i, j).getWordTarget());
            }
        }
    }

    public void addANewWord() {
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
            if (dialogButton == addButtonType) {
                return new Word(wordTarget.getText(), wordExplain.getText());
            }
            return null;
        });
        Optional<Word> result = dialog.showAndWait();
        result.ifPresent(newWord -> {
            Word add = new Word(newWord.getWordTarget(), newWord.getWordExplain());
            myDictionary.getLibraryAt(wordTarget.getText().charAt(0) - 97).addWord(add);
            int index = myDictionary.getLibraryAt(wordTarget.getText().charAt(0) - 97).getSize();
            //words.getItems().add(myDictionary.getWord(wordTarget.getText().charAt(0) - 97, index - 1).getWordTarget());
            names.add(add.getWordTarget());
            Collections.sort(myDictionary.getLibraryAt(add.getWordTarget().charAt(0) - 97).getLibrary());
            FXCollections.sort(names);
            words.setItems(names);
            myDictionary.addDatabase(add);
        });

    }

    public void DeleteWord() {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Delete a word");
        dialog.setHeaderText("English");

        ButtonType deleteButtonType = new ButtonType("Delete", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(deleteButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField wordTarget = new TextField();
        wordTarget.setPromptText("Word target");

        grid.add(new Label("Word target:"), 0, 0);
        grid.add(wordTarget, 1, 0);

        Node loginButton = dialog.getDialogPane().lookupButton(deleteButtonType);
        loginButton.setDisable(true);

        wordTarget.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });
        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == deleteButtonType) {
                return wordTarget.getText();
            }
            return null;
        });
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(newWord -> {
            myDictionary.getLibraryAt(newWord.charAt(0) - 97).deleteWord(newWord);
            names.remove(newWord);
            words.setItems(names);
            myDictionary.deleteDatabase(newWord);
        });
    }

    public void EditWord() {
        Dialog<Word> dialog = new Dialog<>();
        dialog.setTitle("Edit a word");
        dialog.setHeaderText("English");

        ButtonType editButtonType = new ButtonType("Edit", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(editButtonType, ButtonType.CANCEL);

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

        Node loginButton = dialog.getDialogPane().lookupButton(editButtonType);
        loginButton.setDisable(true);

        wordTarget.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });
        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == editButtonType) {
                return new Word(wordTarget.getText(), wordExplain.getText());
            }
            return null;
        });
        Optional<Word> result = dialog.showAndWait();
        result.ifPresent(newWord -> {
            String temp = newWord.getWordTarget();
            myDictionary.getLibraryAt(temp.charAt(0) - 97).editWord(newWord);
            myDictionary.editDatabase(newWord);
        });

    }

}
