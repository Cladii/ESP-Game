package g52010.espgame.repository;

import g52010.espgame.dto.DictionnaryDto;
import g52010.espgame.exception.RepositoryException;
import g52010.espgame.jdbc.DictionnaryDao;
import java.util.List;

/**
 *
 * @author Schellekens
 */
public class DictionnaryRepository implements Repository<String, DictionnaryDto> {

    private final DictionnaryDao dao;

    public DictionnaryRepository() throws RepositoryException {
        dao = DictionnaryDao.getInstance();
    }

    DictionnaryRepository(DictionnaryDao dao) {
        this.dao = dao;
    }

    @Override
    public String add(DictionnaryDto item) throws RepositoryException {
        return dao.insert(item);
    }

    @Override
    public void remove(String key) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DictionnaryDto> getAll() throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DictionnaryDto get(String key) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean contains(String key) throws RepositoryException {
        DictionnaryDto refreshItem = dao.select(key);
        return refreshItem != null;
    }

    public void deleteAll() throws RepositoryException {
        dao.deleteAll();
    }
}
