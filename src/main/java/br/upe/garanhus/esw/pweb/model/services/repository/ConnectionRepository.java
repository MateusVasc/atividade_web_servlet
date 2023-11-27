package br.upe.garanhus.esw.pweb.model.services.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionRepository {

  private static final String URL = "jdbc:postgresql://localhost:5432/";
  private static final String POSTGRES_DRIVER = "org.postgresql.Driver";

  public Connection openConnection(String dataBaseName, String user, String password)
      throws ClassNotFoundException, SQLException {
    Connection connection;
    Class.forName(POSTGRES_DRIVER);
    connection = DriverManager.getConnection(URL + dataBaseName, user, password);

    return connection;
  }

  public void closeConnection(Connection connection) throws SQLException {
    connection.close();
  }

  public void closeStatement(Statement statement) throws SQLException {
    statement.close();
  }

  public void closeResultSet(ResultSet resultSet) throws SQLException {
    resultSet.close();
  }

}
