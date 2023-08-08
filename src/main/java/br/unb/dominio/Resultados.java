package br.unb.dominio;

public class Resultados {
	private String id;
	private int resultado;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getResultado() {
		return resultado;
	}
	public void setResultado(int resultado) {
		this.resultado = resultado;
	}
	@Override
	public String toString() {
		return "Resultados [id=" + id + ", resultado=" + resultado + "]";
	}
}
