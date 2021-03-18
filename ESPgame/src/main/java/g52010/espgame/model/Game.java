package g52010.espgame.model;

import g52010.espgame.dto.ImageDto;
import g52010.espgame.dto.WordsDto;
import g52010.espgame.exception.RepositoryException;
import g52010.espgame.presenter.Observer;
import g52010.espgame.repository.WordsRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Schellekens
 */
public class Game implements Facade {

    private Match currentMatch;
    private Dictionnary dictionnary;
    private WordsRepository wordsRepo;
    private Images images;
    private List<Observer> observers;
    private int round;

    public Game() throws RepositoryException {
        observers = new ArrayList<>();
        wordsRepo = new WordsRepository();
        dictionnary = new Dictionnary();
        round = 0;
    }

    @Override
    public void refreshDictionnary() throws RepositoryException {
        dictionnary.refreshData();
    }

    @Override
    public void start() {
        this.images = new Images();
        images.initialize();
        if (!images.isEmpty()) {
            currentMatch = new Match(images.getImage(round));
        } else {
            throw new IllegalStateException("Pas d'image dans la db");
        }
    }

    @Override
    public List<String> getWords(int playerId) {
        return currentMatch.getWords(playerId);
    }

    @Override
    public String getNewLabel() {
        return currentMatch.getNewLabel();
    }

    @Override
    public ImageDto getImage(int index) {
        return images.getImage(index);
    }

    @Override

    public int getRound() {
        return round;
    }

    @Override
    public boolean propose(String word, int playerId) throws RepositoryException {
        boolean isWordOk = isValid(word) && currentMatch.propose(word, playerId);
        notifyObservers();
        return isWordOk;
    }

    @Override
    public boolean isMatchOver() {
        return currentMatch.isOver();
    }

    @Override
    public void addNewLabel() throws RepositoryException {
        if (currentMatch.isOver()) {
            wordsRepo.add(new WordsDto(-1, currentMatch.getImage(), currentMatch.getNewLabel(), LocalDateTime.now()));
        }
    }

    @Override
    public void nextMatch() {
        if (!isOver()) {
            currentMatch = new Match(images.getImage(round + 1));
            round++;
        }
    }

    private boolean isValid(String word) {
        boolean isValid = false;
        try {
            isValid = dictionnary.isInDictionnary(word) && !getLabels(round).contains(word);
        } catch (RepositoryException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isValid;
    }

    @Override
    public boolean isPreviousProposal(String word, int playerId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isOver() {
        return round == images.getSize() - 1;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this, null);
        }
    }

    @Override
    public void notifyObservers(Object arg) {
        for (Observer observer : observers) {
            observer.update(this, arg);
        }
    }

    @Override
    public List<String> getLabels(int index) {
        try {
            return wordsRepo.getWords(images.getImage(index));
        } catch (RepositoryException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean isRunning() {
        return dictionnary.isRunning();
    }

    @Override
    public boolean isLoaded() {
        return dictionnary.isLoaded();
    }
}
