package g52010.espgame.jdbc;

import g52010.espgame.dto.ImageDto;
import g52010.espgame.dto.WordsDto;
import g52010.espgame.exception.RepositoryException;
import g52010.espgame.repository.Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author g52010
 */
public class WordsDao implements Dao<Integer, WordsDto> {

    private Connection connexion;

    private WordsDao() throws RepositoryException {
        connexion = DBManager.getInstance().getConnection();
    }

    public static WordsDao getInstance() throws RepositoryException {
        return LabelsDaoHolder.getInstance();
    }

    @Override
    public Integer insert(WordsDto item) throws RepositoryException {
        if (item == null) {
            throw new RepositoryException("Aucune élément donné en paramètre");
        }
        Integer id = 0;
        String sql = "INSERT INTO LABELS(imageId, text, dateAdded) values(?, ?, ?)";
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setString(1, item.getImage().getKey());
            pstmt.setString(2, item.getText());
            pstmt.setString(3, item.getDate());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return id;
    }

    @Override
    public void delete(Integer key) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(WordsDto item) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<WordsDto> selectAll() throws RepositoryException {
        String sql = "SELECT * FROM LABELS";
        List<WordsDto> dtos = new ArrayList<>();
        try (Statement stmt = connexion.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                WordsDto dto = new WordsDto(rs.getInt(1), new ImageDto(rs.getString(2)),
                        rs.getString(3), rs.getString(4));
                dtos.add(dto);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return dtos;
    }

    @Override
    public WordsDto select(Integer key) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<String> getWords(ImageDto image) throws RepositoryException {
        if (image == null) {
            throw new RepositoryException("Aucune image donnée en paramètre");
        }
        String sql = "SELECT text FROM LABELS WHERE imageId = ?";
        ArrayList<String> dtos = new ArrayList<>();
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setString(1, image.getKey());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                dtos.add(rs.getString(1));
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return dtos;
    }

    private static class LabelsDaoHolder {

        private static WordsDao getInstance() throws RepositoryException {
            return new WordsDao();
        }
    }

}
