package g52010.espgame.model;

import g52010.espgame.dto.DictionnaryDto;
import g52010.espgame.exception.RepositoryException;
import g52010.espgame.repository.DictionnaryRepository;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Schellekens
 */
public class Dictionnary {

    private DictionnaryRepository dictionnaryRepo;
    private boolean running;
    private boolean loaded;
    private final String PATH = "src/main/resources/dictionnary/liste_francais.txt";

    public Dictionnary() throws RepositoryException {
        dictionnaryRepo = new DictionnaryRepository();
        loaded = false;
    }

    public void refreshData() throws RepositoryException {
        try {
            File file = new File(PATH);
            Scanner sc = new Scanner(file);
            running = true;
            loaded = false;
            dictionnaryRepo.deleteAll();
            while (sc.hasNextLine()) {
                dictionnaryRepo.add(new DictionnaryDto(sc.nextLine()));
            }
            running = false;
            loaded = true;
            sc.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Dictionnary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isInDictionnary(String word) throws RepositoryException {
        return dictionnaryRepo.contains(word);
    }

    public boolean isLoaded() {
        return loaded;
    }

}
