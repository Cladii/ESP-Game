package g52010.espgame.view.fx;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Displays the name of the player, gets the input of the player, sends the
 * input to the model.
 *
 * @author jlc
 */
class PlayerPane extends SplitPane {

    /**
     * Aligns the components of the PlayerPane.
     */
    private final GridPane grid;

    /**
     * Input of the user.
     */
    private final TextField input;

    /**
     * Press this button send the word to the model.
     */
    private final Button send;

    /**
     * Displays the words proposed by the user.
     */
    private final ListView<String> words;

    /**
     * List of words to display by the ListView.
     */
    private final ObservableList<String> listOfMessages;

    /**
     * Name of the player.
     */
    private final Label playerTitle;

    /**
     * Builds the components of the pane.
     */
    PlayerPane() {
        grid = new GridPane();
        words = new ListView<>();

        playerTitle = new Label("Entrez une proposition :");
        send = new Button("Proposer");
        input = buildInput();

        grid.add(playerTitle, 0, 0);
        grid.add(input, 0, 1);
        grid.add(send, 1, 1);

        listOfMessages = FXCollections.<String>observableArrayList();
        words.setItems(listOfMessages);
    }

    private TextField buildInput() {
        TextField textField = new TextField();
        textField.setPrefColumnCount(6);
        textField.setPromptText("proposition ...");
        return textField;
    }

    /**
     * Initializes the player's name and the divider position.
     *
     * @param ratio divider's position.
     */
    void initialize(double ratio, int playerId) {
        playerTitle.setText("Entrez une proposition joueur " + playerId + " :");
        setOrientation(Orientation.HORIZONTAL);
        getItems().addAll(grid, words);
        setDividerPositions(ratio);
    }

    /**
     * Returns the words given by the user.
     *
     * @return the words given by the user.
     */
    String getInput() {
        return input.getText();
    }

    /**
     * Adds an action event handler to the send button.
     *
     * @param event event to add to the send button.
     */
    void addSendHandler(EventHandler<ActionEvent> event) {
        send.setOnAction(event);
    }

    /**
     * Displays the words given by the user about the current image.
     *
     * @param words words given by the user about the current image.
     */
    void setWords(List<String> words) {
        listOfMessages.clear();
        for (String word : words) {
            listOfMessages.add(word);
        }
    }

    /**
     * Clear the user part (TextField is clear).
     */
    void clear() {
        input.clear();
    }

    /**
     * Disables the send bouton. The user can't play.
     */
    void disableInput() {
        input.setDisable(true);
        send.setDisable(true);
    }

    /**
     * Enables the send bouton. The user can play.
     */
    void enableInput() {
        input.setDisable(false);
        send.setDisable(false);
    }
}
