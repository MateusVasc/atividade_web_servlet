package br.upe.garanhus.esw.pweb.controle;

import br.upe.garanhus.esw.pweb.modelo.servicos.ErrorTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import br.upe.garanhus.esw.pweb.CatException;
import br.upe.garanhus.esw.pweb.modelo.servicos.CatService;
import br.upe.garanhus.esw.pweb.modelo.servicos.CatTO;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/processa-imagem")
public class Exec02Servlet extends HttpServlet {

	private static final String UNEXPECTED_ERROR_MESSAGE = "Ocorreu um erro inesperado em sua solicitação.";
	
	private static final Jsonb jsonb = JsonbBuilder.create();
	
	private CatService catService = new CatService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		resp.setContentType("application/json");

		try {
			List<CatTO> catImages = catService.getCatList();

			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			resp.setStatus(HttpServletResponse.SC_OK);

			resp.getWriter().print(jsonb.toJson(catImages));
		} catch (Exception e) {
			this.handleError(e, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		resp.setContentType("application/json");

		try {
			CatTO catImage = catService.getCat("0XYvRd7oD"); // passar id de escolha.

			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			resp.setStatus(HttpServletResponse.SC_OK);

			resp.getWriter().print(jsonb.toJson(catImage));
		} catch (Exception e) {
			this.handleError(e, resp);
		}
	}

	public void handleError(Exception e, HttpServletResponse response) {
		PrintWriter out;

		try {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			ErrorTO error = new ErrorTO(HttpServletResponse.SC_BAD_REQUEST, e);

			out = response.getWriter();
			out.write(jsonb.toJson(error));
			out.flush();
		} catch (Exception exception) {
			throw new CatException(UNEXPECTED_ERROR_MESSAGE, e);
		}
	}
}
