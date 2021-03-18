package g52010.espgame.model;

import g52010.espgame.dto.ImageDto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Schellekens
 */
public class Match {

    private final ImageDto image;
    private List<String> listP1;
    private List<String> listP2;
    private String newLabel;

    public Match(ImageDto image) {
        this.image = image;
        listP1 = new ArrayList<>();
        listP2 = new ArrayList<>();
    }

    public boolean propose(String word, int playerId) {
        if (playerId == 1) {
            return proposeP1(word);
        } else {
            return proposeP2(word);
        }
    }

    private boolean proposeP1(String word) {
        if (!listP1.contains(word)) {
            listP1.add(word);
            if (isNewLabel(word)) {
                newLabel = word;
            }
            return true;
        } else {
            return false;
        }
    }

    private boolean proposeP2(String word) {
        if (!listP2.contains(word)) {
            listP2.add(word);
            if (isNewLabel(word)) {
                newLabel = word;
            }
            return true;
        } else {
            return false;
        }
    }

    private boolean isNewLabel(String word) {
        return listP1.stream().anyMatch(element -> listP2.contains(element));
    }

    public boolean isOver() {
        return newLabel != null;
    }

    public List<String> getWords(int playerId) {
        return (playerId == 1) ? listP1 : listP2;
    }

    public String getNewLabel() {
        return newLabel;
    }

    public ImageDto getImage() {
        return image;
    }
}
