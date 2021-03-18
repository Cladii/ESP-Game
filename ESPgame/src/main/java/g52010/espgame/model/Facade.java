package g52010.espgame.model;

import g52010.espgame.presenter.Observer;
import g52010.espgame.dto.ImageDto;
import g52010.espgame.exception.RepositoryException;
import java.util.List;

/**
 * Facade of the Extrasensory perception game.
 *
 * @see
 * <a href="https://en.wikipedia.org/wiki/ESP_game"> Wikipedia</a>
 *
 * @see
 * <a href="https://en.wikipedia.org/wiki/Facade"> Link to a description of the
 * design pattern facade  </a>
 *
 * @see
 * <a href="https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter">
 * Wikipedia</a>
 *
 * @author jlc
 */
public interface Facade {

    /**
     * Starts the first match of the game. The first image is selected.
     */
    void start();

    /**
     * Refresh the dictionnary
     *
     * @throws RepositoryException if the database can't process a query.
     */
    void refreshDictionnary() throws RepositoryException;

    /**
     * Check if the loading of the dictionnary is in progress.
     *
     * @return true if the loading is in progress.
     */
    boolean isRunning();

    /**
     * Check if the loading of the dictionnary is finished.
     *
     * @return true if the loading is finished.
     */
    boolean isLoaded();

    /**
     * Return the labels for the current image.
     *
     * @param index
     * @return a list of labels.
     */
    List<String> getLabels(int index);

    /**
     * Returns the list of words send by the user for the current image.
     *
     * @param playerId player's id.
     * @return the list of words send by the user for the current image.
     */
    List<String> getWords(int playerId);

    /**
     * Adds the label found in the database if the match is over. The match is
     * over when a label is found for the current match.
     *
     * @throws RepositoryException if the database can't process a query.
     */
    void addNewLabel() throws RepositoryException;

    /**
     * Return the path of the current image.
     *
     * @param index
     * @return the path of the current image.
     */
    ImageDto getImage(int index);

    /**
     * Gets the current number round.
     *
     * @return the number round.
     */
    int getRound();

    /**
     * Sends the word given by the player of the given id.
     *
     * @param word word to check.
     * @param playerId player's id.
     * @return
     * @throws RepositoryException if the database can't process a query.
     */
    //void propose(String word, int playerId) throws RepositoryException;
    boolean propose(String word, int playerId) throws RepositoryException;

    /**
     * Check if the match is over. A match is over when a common word is found.
     *
     * @return true if the match is over.
     */
    boolean isMatchOver();

    /**
     * Set the current match to the next match when the current match is over.A
     * match is over when a common word is found.
     */
    void nextMatch();

    /**
     * Gets the common label found in the current match.
     *
     * @return the current word or null if there is not yet common word.
     */
    String getNewLabel();

    /**
     * Return true if the word is already proposed by the user for the current
     * image and false otherwise.
     *
     * @param word word to check.
     * @param playerId player's id.
     * @return true if the word is already proposed by the user for the current
     * image and false otherwise.
     */
    boolean isPreviousProposal(String word, int playerId);

    /**
     * Return true if there is no more image to display and the last match is
     * over.
     *
     * @return true if there is no more image to display and the last match is
     * over.
     */
    boolean isOver();

    /**
     * Adds an observer to the set of observers for this object, provided that
     * it is not the same as some observer already in the set. The order in
     * which notifications will be delivered to multiple observers is not
     * specified. See the class comment.
     *
     * @param observer an observer to be added.
     * @throws NullPointerException if the parameter o is null.
     */
    void addObserver(Observer observer);

    /**
     * Notify all of its observers.
     * <p>
     * Each observer has its {@code update} method called with two arguments:
     * this observable object and {@code null}. In other words, this method is
     * equivalent to:
     * <blockquote>{@code
     * notifyObservers(null)}</blockquote>
     *
     */
    void notifyObservers();

    /**
     * Notify all of its observers.
     * <p>
     * Each observer has its {@code update} method called with two arguments:
     * this observable object and the {@code arg} argument.
     *
     * @param arg any object.
     */
    void notifyObservers(Object arg);

}
