package br.upe.garanhus.esw.pweb.model.services;

public class ErrorTO {
  private String error;
  private String code;
  private String detail;

  public ErrorTO() {
  }

  public ErrorTO(int code, Exception error) {
    this();
    this.error = error.getMessage();
    this.code = String.valueOf(code);
    this.detail = error.getCause().toString();
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }
}
