package g52010.espgame.main;

import g52010.espgame.config.ConfigManager;
import g52010.espgame.model.Facade;
import g52010.espgame.model.Game;
import g52010.espgame.presenter.MyPresenter;
import g52010.espgame.presenter.Presenter;
import g52010.espgame.view.fx.View;
import g52010.espgame.view.fxml.ViewController;
import g52010.espgame.view.fxml.ViewFXML;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Schellekens
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        ConfigManager.getInstance().load();

        Facade model = new Game();
        model.start();

        FXMLLoader fl1 = new FXMLLoader();
        FXMLLoader fl2 = new FXMLLoader();
        GridPane root1 = fl1.load(getClass().getResource("/fxml/view.fxml").openStream());
        GridPane root2 = fl2.load(getClass().getResource("/fxml/view.fxml").openStream());
        View view1 = new ViewFXML((ViewController) fl1.getController());
        View view2 = new ViewFXML((ViewController) fl2.getController());

        Scene scene1 = new Scene(root1);
        Scene scene2 = new Scene(root2);
        Stage stage1 = new Stage();
        Stage stage2 = new Stage();
        stage1.setScene(scene1);
        stage2.setScene(scene2);
        stage1.setTitle("Joueur 1");
        stage2.setTitle("Joueur 2");

        Presenter p1 = new MyPresenter(1, model, view1);
        Presenter p2 = new MyPresenter(2, model, view2);
        p1.initialize();
        p2.initialize();
        stage1.show();
        stage2.show();
    }
}
