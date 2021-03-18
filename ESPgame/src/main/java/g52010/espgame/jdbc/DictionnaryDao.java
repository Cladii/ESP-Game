package g52010.espgame.jdbc;

import g52010.espgame.dto.DictionnaryDto;
import g52010.espgame.exception.RepositoryException;
import g52010.espgame.repository.Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author Schellekens
 */
public class DictionnaryDao implements Dao<String, DictionnaryDto> {

    private Connection connexion;

    private DictionnaryDao() throws RepositoryException {
        connexion = DBManager.getInstance().getConnection();
    }

    public static DictionnaryDao getInstance() throws RepositoryException {
        return DictionnaryDaoHolder.getInstance();
    }

    @Override
    public String insert(DictionnaryDto item) throws RepositoryException {
        if (item == null) {
            throw new RepositoryException("Aucun élément donné en paramètre");
        }
        String id = "";
        String sql = "INSERT INTO DICTIONNARY (mot) values(?)";
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setString(1, item.getKey());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return item.getKey();
    }

    @Override
    public void delete(String key) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(DictionnaryDto item) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DictionnaryDto> selectAll() throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DictionnaryDto select(String key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("Aucune clé donnée en paramètre");
        }
        String sql = "SELECT * FROM DICTIONNARY WHERE mot = ?";
        DictionnaryDto dto = null;
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setString(1, key);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                dto = new DictionnaryDto(rs.getString(1));
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return dto;
    }

    public void deleteAll() throws RepositoryException {
        String sql = "DELETE FROM DICTIONNARY";
        try (Statement stmt = connexion.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    private static class DictionnaryDaoHolder {

        private static DictionnaryDao getInstance() throws RepositoryException {
            return new DictionnaryDao();
        }
    }
}
