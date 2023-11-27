package br.upe.garanhus.esw.pweb;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;

public class CatException extends RuntimeException {

	public CatException(String errorMessage) {
		super(errorMessage);
	}

	public CatException(String errorMessage, Exception errorOrigin) {
		super(errorMessage, errorOrigin);
	}
}
