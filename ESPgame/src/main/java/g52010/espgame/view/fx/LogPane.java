package g52010.espgame.view.fx;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

/**
 * Displays messages to the user. The latest message is display on top.
 *
 * @author jlc
 */
class LogPane extends ListView<String> {

    /**
     * Timestamp formatter. e.g. 17:48:01.071
     */
    final static DateTimeFormatter FORMATTER_TIME = DateTimeFormatter.ofPattern("HH:mm:ss:SSS");
    /**
     * List of messages to display by the ListView.
     */
    private ObservableList<String> listOfMessages;

    /**
     * Binds the list of strings to the ListView for display.
     */
    void initialize() {
        listOfMessages = FXCollections.<String>observableArrayList();
        setItems(listOfMessages);
    }

    /**
     * Adds a message to the view. The message is add to the top of the list.
     *
     * @param text message to display.
     * @param time timestamp if the message.
     */
    void add(String text, LocalDateTime time) {
        listOfMessages.add(0, FORMATTER_TIME.format(time) + "\t" + text);
    }
}
