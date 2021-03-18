package g52010.espgame.jdbc;

import g52010.espgame.dto.ImageDto;
import g52010.espgame.exception.RepositoryException;
import g52010.espgame.repository.Dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Schellekens
 */
public class ImagesDao implements Dao<String, ImageDto> {

    private Connection connexion;

    private ImagesDao() throws RepositoryException {
        connexion = DBManager.getInstance().getConnection();
    }

    public static ImagesDao getInstance() throws RepositoryException {
        return ImagesDaoHolder.getInstance();
    }

    @Override
    public String insert(ImageDto item) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String key) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(ImageDto item) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ImageDto> selectAll() throws RepositoryException {
        String sql = "SELECT path FROM IMAGES";
        List<ImageDto> dtos = new ArrayList<>();
        try (Statement stmt = connexion.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                ImageDto dto = new ImageDto(rs.getString(1));
                dtos.add(dto);
            }
        } catch( SQLException e){
            throw new RepositoryException(e);
        }
        return dtos;
    }

    @Override
    public ImageDto select(String key) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static class ImagesDaoHolder {

        private static ImagesDao getInstance() throws RepositoryException {
            return new ImagesDao();
        }
    }
}
