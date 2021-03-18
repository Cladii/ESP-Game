package g52010.espgame.view.fx;

import java.io.File;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;

/**
 * Displays the a fixed size image and the labels linked to it. The view is
 * split in 2 parts.
 *
 * @author jlc
 */
class ImagePane extends SplitPane {

    /**
     * Displays a fixed size image.
     */
    private final WrappedImageView imageview;
    /**
     * Displays the labels of the image.
     */
    private final ListView<String> labels;
    /**
     * List of words to display by the ListView.
     */
    private final ObservableList<String> listOfMessages;

    /**
     * Builds the image-view and the labels.
     */
    ImagePane() {
        imageview = new WrappedImageView();
        labels = new ListView<>();
        listOfMessages = FXCollections.<String>observableArrayList();
        labels.setItems(listOfMessages);
    }

    /**
     * Initializes the imagView and the divider position.
     *
     * @param horizontalRatio divider position.
     */
    void initialize(double horizontalRatio) {
        imageview.setPreserveRatio(true);
        imageview.setSmooth(true);
        imageview.setCache(true);
        setOrientation(Orientation.HORIZONTAL);
        getItems().addAll(imageview, labels);
        setDividerPositions(horizontalRatio);
    }

    /**
     * Displays the image found at the given path.
     *
     * @param path path to the image.
     */
    void setImage(String path) {
        File file = new File("src/main/resources/images/" + path);
        if (!file.exists()) {
            throw new IllegalArgumentException("L'image n'existe pas : " + file.getName());
        }
        Image image = new Image(file.toURI().toString());
        imageview.setImage(image);
    }

    /**
     * Displays the labels of the current image.
     *
     * @param labels list of words linked to the current image.
     */
    void setLabels(List<String> labels) {
        listOfMessages.clear();
        for (String label : labels) {
            listOfMessages.add(label);
        }
    }
}
