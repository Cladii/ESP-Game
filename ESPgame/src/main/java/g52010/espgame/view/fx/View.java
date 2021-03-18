package g52010.espgame.view.fx;

import g52010.espgame.presenter.Presenter;
import java.time.LocalDateTime;
import java.util.List;

/**
 * View of the Extrasensory perception game.
 *
 * @author jlc
 */
public interface View {

    /**
     * Initializes the stage and show it to the screen.A view is displayed by
     * user.
     *
     * @param playerId the id of the user.
     */
    void initialize(int playerId);

    /**
     * Adds a message to the view. The message is add to the top of the list.
     *
     * @param text message to display.
     * @param time timestamp if the message.
     */
    void addMessage(String text, LocalDateTime time);

    /**
     * Clear the user part (TextField is clear).
     */
    void clear();

    /**
     * Disables the send bouton. The user can't play.
     */
    void disableInput();

    /**
     * Enables the send bouton. The user can play.
     */
    void enableInput();

    /**
     * Displays the image found at the given path.
     *
     * @param path path to the image.
     */
    void setImage(String path);

    /**
     * Displays the words given by the user about the current image.
     *
     * @param words words given by the user about the current image.
     */
    void setWords(List<String> words);

    /**
     * Displays the labels of the current image.
     *
     * @param labels list of words linked to the current image.
     */
    void setlabels(List<String> labels);

    /**
     * Adds an action event handler to the send button. A presenter handles the
     * event.
     *
     * @param presenter the presenter.
     *
     * @see
     * <a href="https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter">
     * Link to a description of the MVP pattern  </a>
     */
    void addHandlerSend(Presenter presenter);

    /**
     * Adds an action event handler to the close button. A presenter handles the
     * event.
     *
     * @param presenter the presenter.
     *
     * @see
     * <a href="https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter">
     * Link to a description of the MVP pattern  </a>
     */
    void addHandlerClose(Presenter presenter);

    /**
     * Quits the FX window.
     */
    void quit();

    /**
     * Shows a dialog box to alert all the players.The dialog box is shown when
     * the match is over.
     *
     */
    void showEndMatch();

}
