package br.upe.garanhus.esw.pweb;

public class RepositoryException extends RuntimeException {

  public RepositoryException(String errorMessage) {
    super(errorMessage);
  }

  public RepositoryException(String errorMessage, Exception errorOrigin) {
    super(errorMessage, errorOrigin);
  }

}
