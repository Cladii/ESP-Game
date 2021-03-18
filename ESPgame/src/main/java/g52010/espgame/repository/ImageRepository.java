package g52010.espgame.repository;

import g52010.espgame.dto.ImageDto;
import g52010.espgame.exception.RepositoryException;
import g52010.espgame.jdbc.ImagesDao;
import java.util.List;

/**
 *
 * @author g52010
 */
public class ImageRepository implements Repository<String, ImageDto>{
    
    private final ImagesDao dao;
    
    public ImageRepository() throws RepositoryException{
        dao= ImagesDao.getInstance();
    }
    
    ImageRepository (ImagesDao dao) {
        this.dao = dao;
    }

    @Override
    public String add(ImageDto item) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(String key) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ImageDto> getAll() throws RepositoryException {
        return dao.selectAll();
    }

    @Override
    public ImageDto get(String key) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean contains(String key) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
