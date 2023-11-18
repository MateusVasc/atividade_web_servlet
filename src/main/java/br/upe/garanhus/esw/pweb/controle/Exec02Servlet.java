package br.upe.garanhus.esw.pweb.controle;

import java.io.IOException;
import java.util.List;

import br.upe.garanhus.esw.pweb.AplicacaoException;
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

	private static final long serialVersionUID = 1L;
	
	private static final Jsonb jsonb = JsonbBuilder.create();
	
	private CatService catService = new CatService();
	private AplicacaoException appEx = new AplicacaoException();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json");
		try {
			List<CatTO> imagens = catService.coletaListaJSON();
			resp.getWriter().print(jsonb.toJson(imagens));
		} catch (Exception e) {
			String exResp = appEx.capturaExcecao(e, resp);
			resp.getWriter().print(exResp);
			Thread.currentThread().interrupt();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json");
		try {
			CatTO imagem = catService.coletaImagemJSON("0XYvRd7oD"); // passar id de escolha.
			resp.getWriter().print(jsonb.toJson(imagem));
		} catch (Exception e) {
			String exResp = appEx.capturaExcecao(e, resp);
			resp.getWriter().print(exResp);
			Thread.currentThread().interrupt();
		}
	}
}
