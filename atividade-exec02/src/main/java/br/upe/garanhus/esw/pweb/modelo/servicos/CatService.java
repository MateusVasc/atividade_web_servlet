package br.upe.garanhus.esw.pweb.modelo.servicos;

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
	
	private static final Jsonb jsonb = JsonbBuilder.create();
	
	private HttpClient httpClient = HttpClient.newHttpClient();
	private static final Logger logger = Logger.getLogger(CatService.class.getName());

	public List<CatTO> coletaListaJSON() throws URISyntaxException, IOException, InterruptedException {
			HttpRequest request = HttpRequest.newBuilder()
					.uri(new URI(API_URL_LISTA_IMAGENS))
					.GET()
					.build();
			
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			
			CatTO[] imagensArray = jsonb.fromJson(response.body(), CatTO[].class);
			return List.of(imagensArray);
	}
	
	public CatTO coletaImagemJSON(String imagemId) throws URISyntaxException, IOException, InterruptedException {
			HttpRequest request = HttpRequest.newBuilder()
					.uri(new URI(API_URL_UNICA_IMAGEM + imagemId))
					.GET()
					.build();
			
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			
			CatTO imagem = jsonb.fromJson(response.body(), CatTO.class);
			return imagem;
	}
}
