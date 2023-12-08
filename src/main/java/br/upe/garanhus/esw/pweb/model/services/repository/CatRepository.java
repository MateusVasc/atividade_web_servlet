package br.upe.garanhus.esw.pweb.model.services.repository;

import br.upe.garanhus.esw.pweb.model.services.CatTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CatRepository {

  private static final String DB_NAME = "cats";
  private static final String DB_USER = "postgres";
  private static final String DB_PASSWORD = "noobnaoentra";

  private static final String SAVE_CATS_QUERY = "INSERT INTO cats (id, url, width, height) VALUES "
      + "(?, ?, ?, ?)";

  private final ConnectionRepository connectionRepository;
  private CatTO catTO;

  public CatRepository() {
    this.connectionRepository = new ConnectionRepository();
  }

  public void saveCatList(List<CatTO> catList) throws SQLException, ClassNotFoundException {
    Connection connection = null;
    PreparedStatement statement = null;

    connection = connectionRepository.openConnection(DB_NAME,DB_USER,DB_PASSWORD);
    statement = connection.prepareStatement(SAVE_CATS_QUERY);

    for (CatTO cat : catList) {
      statement.setString(1,cat.getId());
      statement.setString(2,cat.getUrl());
      statement.setInt(3,cat.getWidth());
      statement.setInt(4,cat.getHeight());

      statement.executeUpdate();
    }

    connectionRepository.closeStatement(statement);
    connectionRepository.closeConnection(connection);
  }

  public void getCat(String id) {
  }
}
