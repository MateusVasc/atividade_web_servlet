package br.upe.garanhus.esw.pweb.model.services.repository;

import br.upe.garanhus.esw.pweb.model.services.CatTO;
import java.util.List;

public class CatRepository {

  private CatTO catTO;

  public CatRepository() {
    ConnectionRepository connectionRepository = new ConnectionRepository();
  }

  public void saveCatList(List<CatTO> catList) {
  }

  public void getCat(String id) {
  }

  public void createTable() {
  }
}
