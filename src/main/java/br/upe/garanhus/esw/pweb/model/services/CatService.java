package br.upe.garanhus.esw.pweb.model.services;

import br.upe.garanhus.esw.pweb.CatException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.logging.Logger;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

public class CatService {
	// API URL
	private static final String API_URL_LISTA_IMAGENS = "https://api.thecatapi.com/v1/images/search?limit=10";
	private static final String API_URL_UNICA_IMAGEM = "https://api.thecatapi.com/v1/images/";

	private static final String URI_ERROR_MESSAGE = "Ocorreu um erro na formação do URI.";
	private static final String IO_ERROR_MESSAGE = "Ocorreu um erro durante a leitura ou gravação de dados.";
	private static final String INTERRUPTED_ERROR_MESSAGE = "A thread que estava executando foi interrompida.";
	private static final String UNEXPECTED_ERROR_MESSAGE = "Ocorreu um erro inesperado na aplicação.";

	private static final Jsonb jsonb = JsonbBuilder.create();
	
	private HttpClient httpClient = HttpClient.newHttpClient();
	private static final Logger logger = Logger.getLogger(CatService.class.getName());

	public List<CatTO> getCatList() {
		HttpResponse<String> response = null;

		try {
			HttpRequest request = HttpRequest.newBuilder()
					.uri(new URI(API_URL_LISTA_IMAGENS))
					.GET()
					.build();

			response = httpClient.send(request,
					HttpResponse.BodyHandlers.ofString());
		} catch (Exception e) {
			this.handleError(e);
		}

		CatTO[] catImages = jsonb.fromJson(response.body(), CatTO[].class);
		return List.of(catImages);
	}

	public CatTO getCat(String imagemId) {
		HttpResponse<String> response = null;

		try {
			HttpRequest request = HttpRequest.newBuilder()
					.uri(new URI(API_URL_UNICA_IMAGEM + imagemId))
					.GET()
					.build();

			response = httpClient.send(request,
					HttpResponse.BodyHandlers.ofString());
		} catch (Exception e) {
			this.handleError(e);
		}

		CatTO catImage = jsonb.fromJson(response.body(), CatTO.class);
		return catImage;
	}

	public void handleError(Exception e) {
		if (e instanceof URISyntaxException) {
			throw new CatException(URI_ERROR_MESSAGE);
		} else if (e instanceof IOException) {
			throw new CatException(IO_ERROR_MESSAGE);
		} else if (e instanceof InterruptedException) {
			throw new CatException(INTERRUPTED_ERROR_MESSAGE);
		} else {
			throw new CatException(UNEXPECTED_ERROR_MESSAGE, e);
		}
	}
}
