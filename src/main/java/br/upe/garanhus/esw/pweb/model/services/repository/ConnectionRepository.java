package br.upe.garanhus.esw.pweb.model.services.repository;

import br.upe.garanhus.esw.pweb.RepositoryException;
import java.awt.font.TextHitInfo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionRepository {

  private static final String URL = "jdbc:postgresql://localhost:5432/";
  private static final String POSTGRES_DRIVER = "org.postgresql.Driver";

  private static final String CLASS_NOT_FOUND_EXCEPTION_ERROR_MESSAGE = "Ocorreu um erro de conexão com o banco de dados";
  private static final String SQL_EXCEPTION_ERROR_MESSAGE = "Ocorreu um erro ao fechar as conexões";
  private static final String GENERAL_ERROR_MESSAGE = "Ocorreu um erro inesperado durante a comunicação com o banco de dados";

  public Connection openConnection(String dataBaseName, String user, String password) {
    Connection connection = null;

    try {
      Class.forName(POSTGRES_DRIVER);
      connection = DriverManager.getConnection(URL + dataBaseName, user, password);

    } catch (Exception e) {
      this.handleError(e);
    }

    return connection;
  }

  public void closeConnection(Connection connection) {
    try {
      connection.close();
    } catch (Exception e) {
      this.handleError(e);
    }
  }

  public void closeStatement(Statement statement) {
    try {
      statement.close();
    } catch (Exception e) {
      this.handleError(e);
    }
  }

  public void closeResultSet(ResultSet resultSet) {
    try {
      resultSet.close();
    } catch (Exception e) {
      this.handleError(e);
    }
  }

  public void handleError(Exception e) {
    if (e instanceof ClassNotFoundException) {
      throw new RepositoryException(CLASS_NOT_FOUND_EXCEPTION_ERROR_MESSAGE);
    } else if (e instanceof SQLException) {
      throw new RepositoryException(SQL_EXCEPTION_ERROR_MESSAGE);
    } else {
      throw new RepositoryException(GENERAL_ERROR_MESSAGE, e);
    }
  }
}
