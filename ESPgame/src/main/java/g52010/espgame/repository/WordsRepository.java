package g52010.espgame.repository;

import g52010.espgame.dto.ImageDto;
import g52010.espgame.dto.WordsDto;
import g52010.espgame.exception.RepositoryException;
import g52010.espgame.jdbc.WordsDao;
import java.util.List;

/**
 *
 * @author Schellekens
 */
public class WordsRepository implements Repository<Integer, WordsDto> {

    private final WordsDao dao;

    public WordsRepository() throws RepositoryException {
        dao = WordsDao.getInstance();
    }

    WordsRepository(WordsDao dao) {
        this.dao = dao;
    }
    
    public List<String> getWords(ImageDto image) throws RepositoryException {
        return dao.getWords(image);
    }

    @Override
    public Integer add(WordsDto item) throws RepositoryException {
        return dao.insert(item);
    }

    @Override
    public void remove(Integer key) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<WordsDto> getAll() throws RepositoryException {
        return dao.selectAll();
    }

    @Override
    public WordsDto get(Integer key) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean contains(Integer key) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
