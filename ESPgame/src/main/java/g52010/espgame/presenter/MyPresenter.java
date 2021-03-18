package g52010.espgame.presenter;

import g52010.espgame.exception.RepositoryException;
import g52010.espgame.model.Facade;
import g52010.espgame.view.fx.View;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;

/**
 *
 * @author Schellekens
 */
public class MyPresenter implements Presenter {

    private View view;
    private Facade model;
    private final int playerId;

    public MyPresenter(int playerId, Facade model, View view) throws RepositoryException {
        this.view = view;
        this.model = model;
        this.playerId = playerId;
    }

    @Override
    public void initialize() {
        view.initialize(playerId);
        view.setImage(model.getImage(model.getRound()).getKey());
        view.setlabels(model.getLabels(model.getRound()));
        view.addMessage("Lancement de la partie", LocalDateTime.now());
        view.addHandlerSend(this);
        model.addObserver(this);
    }

    @Override
    public void quit() {
        view.quit();
    }

    @Override
    public void send(String word) {
        try {
            if (!model.isLoaded()) {
                view.addMessage("Dictionnaire pas encore chargé", LocalDateTime.now());
            } else {
                if (!model.propose(word, playerId)) {
                    view.addMessage("Le mot n'est pas valide", LocalDateTime.now());
                }
                view.clear();
            }
        } catch (RepositoryException ex) {
            Logger.getLogger(MyPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Facade observable, Object arg) {
        LocalDateTime now = LocalDateTime.now();
        if (observable.isMatchOver() && observable.isOver()) {
            endGame(observable, now);
        } else {
            if (observable.isMatchOver()) {
                nextMatch(observable, now);
            } else {
                view.setWords(observable.getWords(playerId));
            }
        }
    }

    private void nextMatch(Facade observable, LocalDateTime now) {
        try {
            view.addMessage("Mot trouvé : " + observable.getNewLabel(), now);
            addLabel();
            view.showEndMatch();
            view.addMessage("Nouveau match", now);
            updateView(observable);

        } catch (RepositoryException ex) {
            Logger.getLogger(MyPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateView(Facade observable) {
        if (playerId == 1) {
            view.setImage(observable.getImage(observable.getRound() + 1).getKey());
            view.setlabels(observable.getLabels(observable.getRound() + 1));
            view.setWords(new ArrayList<>());
        } else {
            view.setImage(observable.getImage(observable.getRound()).getKey());
            view.setlabels(observable.getLabels(observable.getRound()));
            view.setWords(new ArrayList<>());
        }
    }

    private void addLabel() throws RepositoryException {
        if (playerId == 2) {
            model.addNewLabel();
            model.nextMatch();
        }
    }

    private void endGame(Facade observable, LocalDateTime now) {
        try {
            if (playerId == 2) {
                model.addNewLabel();
            }
            view.addMessage("Mot trouvé : " + observable.getNewLabel(), now);
            view.showEndMatch();
            view.disableInput();
        } catch (RepositoryException ex) {
            Logger.getLogger(MyPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void refreshData() {
        if (!model.isRunning()) {
            view.addMessage("chargement du dictionnaire", LocalDateTime.now());
            Service<Void> bgThread = new Service<Void>() {
                @Override
                protected Task<Void> createTask() {
                    return new Task<Void>() {
                        @Override
                        protected Void call() throws Exception {
                            model.refreshDictionnary();
                            return null;
                        }
                    };
                }
            };
            bgThread.setOnSucceeded((WorkerStateEvent t) -> {
                view.addMessage("dictionnaire chargé", LocalDateTime.now());
            });
            bgThread.restart();
        } else {
            view.addMessage("dictionnaire déjà en chargement", LocalDateTime.now());
        }
    }
}
