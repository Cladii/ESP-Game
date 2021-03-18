/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g52010.espgame.view.fxml;

import g52010.espgame.view.fxml.ViewController;
import g52010.espgame.presenter.Presenter;
import g52010.espgame.view.fx.View;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 *
 * @author Schellekens
 */
public class ViewFXML implements View {

    final static DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SS");
    private ViewController viewController;
    private ObservableList<String> labels;
    private ObservableList<String> words;
    private ObservableList<String> logs;

    public ViewFXML(ViewController viewController) {
        labels = FXCollections.<String>observableArrayList();
        words = FXCollections.<String>observableArrayList();
        logs = FXCollections.<String>observableArrayList();
        this.viewController = viewController;
    }

    @Override
    public void initialize(int playerId) {
        addMessage("Nouveau match", LocalDateTime.now());
    }

    @Override
    public void addMessage(String text, LocalDateTime time) {
        logs.add(0, FORMAT.format(time) + "\t " + text);
        viewController.setLogs(logs);
    }

    @Override
    public void clear() {
        viewController.clear();
    }

    @Override
    public void disableInput() {
        viewController.disableButton();
    }

    @Override
    public void enableInput() {
        viewController.enableButton();
    }

    @Override
    public void setImage(String path) {
        viewController.setImage(path);
    }

    @Override
    public void setWords(List<String> words) {
        this.words.clear();
        for (String word : words) {
            this.words.add(word);
        }
        viewController.setWords(this.words);
    }

    @Override
    public void setlabels(List<String> labels) {
        this.labels.clear();
        for (String label : labels) {
            this.labels.add(label);
        }
        viewController.setLabelDB(this.labels);
    }

    @Override
    public void addHandlerSend(Presenter presenter) {
        viewController.setPresenter(presenter);
    }

    @Override
    public void addHandlerClose(Presenter presenter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void quit() {
        Platform.exit();
    }

    @Override
    public void showEndMatch() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Victoire");
        alert.setHeaderText(null);
        alert.setContentText("Félicitions! Un mot commun a été trouvé");
        alert.showAndWait();
    }
}
