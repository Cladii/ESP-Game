package g52010.espgame.view.fx;

import g52010.espgame.presenter.Presenter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * View of the Extrasensory perception game.
 *
 * @author jlc
 */
public class ViewFX implements View {

    /**
     * Minimal width of the view.
     */
    public static final int MIN_WIDTH = 630;

    /**
     * Minimal height of the view.
     */
    public static final int MIN_HEIGHT = 600;

    /**
     * Title of the view.
     */
    public static final String TITLE = "HE2B ESI - ESP Game";

    /**
     * Path to the css of the view.
     */
    private static final String CSS = "./css/FxView.css";

    /**
     * Stage of the view.
     */
    private final Stage stage;

    /**
     * The view.
     */
    private final MainPane mainPane;

    /**
     * Constructs the main view of the lotto application.
     *
     * @param stage javaFX stage build by the Application class.
     *
     * @throws IllegalArgumentException if stage, model or controller is null.
     */
    public ViewFX(Stage stage) {
        this.stage
                = Objects.requireNonNull(stage, "Aucune stage passée en paramètre");

        mainPane = new MainPane();
    }

    @Override
    public void initialize(int playerId) {
        initializeStage(playerId);
        initializeScene(playerId);
        stage.show();
    }

    /**
     * Initialize the stage. The size, the title and the css are initialized.
     *
     */
    private void initializeStage(int playerId) {
        stage.setMinWidth(MIN_WIDTH);
        stage.setMinHeight(MIN_HEIGHT);
        stage.setMaxWidth(MIN_WIDTH);
        stage.setMaxHeight(MIN_HEIGHT);
        stage.setTitle(TITLE + " - Joueur " + playerId);
        stage.setResizable(true);
    }

    private void initializeScene(int playerId) {
        mainPane.initialize(playerId);
        Scene scene = new Scene(mainPane);
        scene.getStylesheets().add(CSS);
        stage.setScene(scene);
    }

    @Override
    public void quit() {
        Platform.exit();
    }

    @Override
    public void addMessage(String text, LocalDateTime time) {
        if (text == null || text.isEmpty()) {
            text = "Texte absent du message";
        }
        mainPane.addMessage(text, time);
    }

    @Override
    public void addHandlerClose(Presenter presenter) {
        Objects.requireNonNull(presenter, "Aucune présentation passée en paramètre");
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                presenter.quit();
            }
        });

    }

    @Override
    public void addHandlerSend(Presenter presenter) {
        Objects.requireNonNull(presenter, "Aucune présentation passée en paramètre");
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                String word = mainPane.getInput();
                presenter.send(word);
            }
        };
        mainPane.addSendHandler(event);
    }

    @Override
    public void setImage(String path) {
        if (path == null || path.isEmpty()) {
            throw new IllegalArgumentException("Aucune chemin passé en paramètre");
        }
        mainPane.setImage(path);
    }

    @Override
    public void setlabels(List<String> labels) {
        Objects.requireNonNull(labels, "Aucune liste de libellés passée en paramètre");
        mainPane.setlabels(labels);
    }

    @Override
    public void setWords(List<String> words) {
        Objects.requireNonNull(words, "Aucune liste de mots passée en paramètre");
        mainPane.setWords(words);
    }

    @Override
    public void clear() {
        mainPane.clear();
    }

    @Override
    public void disableInput() {
        mainPane.disableInput();
    }

    @Override
    public void enableInput() {
        mainPane.enableInput();
    }

    @Override
    public void showEndMatch() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Victoire !");
        alert.setHeaderText(null);
        alert.setContentText("Félicitations! Un mot commun a été trouvé !");
        double centerXPosition = stage.getX() + stage.getWidth() / 3d;
        double centerYPosition = stage.getY() + stage.getHeight() / 3d;
        alert.setX(centerXPosition);
        alert.setY(centerYPosition);
        alert.showAndWait();
    }

}
