package g52010.espgame.view.fxml;

import g52010.espgame.presenter.Presenter;
import java.io.File;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Schellekens
 */
public class ViewController {

    private Presenter presenter;
    @FXML
    private Button sendButton;
    @FXML
    private Button refreshButton;
    @FXML
    private TextField inputText;
    @FXML
    private ListView<String> labelDB;
    @FXML
    private ListView<String> words;
    @FXML
    private ListView<String> logs;
    @FXML
    private ImageView image;

    public ViewController() {
    }

    @FXML
    public void sendButton(ActionEvent e) {
        presenter.send(inputText.getText());
    }

    @FXML
    public void refreshButton(ActionEvent e) {
        presenter.refreshData();
    }

    public void disableButton() {
        sendButton.setDisable(true);
    }

    public void clear() {
        inputText.clear();
    }

    public void setImage(String path) {
        File file = new File("src/main/resources/images/" + path);
        if (!file.exists()) {
            throw new IllegalArgumentException("no image " + file.getName());
        }
        Image image = new Image(file.toURI().toString());
        this.image.setImage(image);
    }

    public void setLogs(ObservableList<String> logs) {
        this.logs.setItems(logs);
    }

    public void setWords(ObservableList<String> words) {
        this.words.setItems(words);
    }

    public void setLabelDB(ObservableList<String> words) {
        this.labelDB.setItems(words);
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    void enableButton() {
        sendButton.setDisable(false);
    }
}
