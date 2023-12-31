package br.upe.garanhus.esw.pweb.model.services;

import jakarta.json.bind.annotation.JsonbProperty;

public class CatTO {

    @JsonbProperty("id")
    String id;
    @JsonbProperty("url")
    String url;
    @JsonbProperty("width")
    int width;
    @JsonbProperty("height")
    int height;
    
    public CatTO() {
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "Imagem [id=" + id + ", url=" + url + ", width=" + width + ", height=" + height + "]";
	}
}
