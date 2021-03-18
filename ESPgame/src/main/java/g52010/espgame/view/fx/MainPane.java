package g52010.espgame.view.fx;

import java.time.LocalDateTime;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.control.SplitPane;

/**
 * The view is split in three parts : an image section, a player section and a
 * message section.
 *
 * @author jlc
 */
class MainPane extends SplitPane {

    /**
     * Displays a fixed size image and the labels of the image.
     */
    private final ImagePane imagePane;

    /**
     * Gets data and action from the user.
     */
    private final PlayerPane playerPane;

    /**
     * Display messages to the user.
     */
    private final LogPane logPane;

    /**
     * Builds the three parts of the view..
     */
    MainPane() {
        imagePane = new ImagePane();
        playerPane = new PlayerPane();
        logPane = new LogPane();
    }

    /**
     * Initializes the position and the size of the part view.
     */
    void initialize(int playerId) {
        double horizontalRatio = 0.7f;
        imagePane.initialize(horizontalRatio);
        playerPane.initialize(horizontalRatio, playerId);
        logPane.initialize();
        setOrientation(Orientation.VERTICAL);
        getItems().addAll(imagePane, playerPane, logPane);
    }

    /**
     * Returns the words given by the user.
     *
     * @return the words given by the user.
     */
    String getInput() {
        return playerPane.getInput();
    }

    /**
     * Adds a message to the view. The message is add to the top of the list.
     *
     * @param text message to display.
     * @param time timestamp if the message.
     */
    void addMessage(String text, LocalDateTime time) {
        logPane.add(text, time);
    }

    /**
     * Adds an action event handler to the send button.
     *
     * @param event event to add to the send button.
     */
    void addSendHandler(EventHandler<ActionEvent> event) {
        playerPane.addSendHandler(event);
    }

    /**
     * Displays the image found at the given path.
     *
     * @param path path to the image.
     */
    void setImage(String path) {
        try {
            imagePane.setImage(path);
        } catch (IllegalArgumentException e) {
            addMessage(e.getMessage(), LocalDateTime.now());
        }
    }

    /**
     * Displays the labels of the current image.
     *
     * @param labels list of words linked to the current image.
     */
    void setlabels(List<String> labels) {
        imagePane.setLabels(labels);
    }

    /**
     * Displays the words given by the user about the current image.
     *
     * @param words words given by the user about the current image.
     */
    void setWords(List<String> words) {
        playerPane.setWords(words);
    }

    /**
     * Clear the user part (TextField is clear).
     */
    void clear() {
        playerPane.clear();
    }

    /**
     * Disables the send bouton. The user can't play.
     */
    void disableInput() {
        playerPane.disableInput();
    }

    /**
     * Enables the send bouton. The user can play.
     */
    void enableInput() {
        playerPane.enableInput();
    }
}
