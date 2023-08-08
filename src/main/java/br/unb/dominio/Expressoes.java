package br.unb.dominio;

public class Expressoes {
	private String id;
	private String expressao;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getExpressao() {
		return expressao;
	}
	public void setExpressao(String expressao) {
		this.expressao = expressao;
	}
	public Expressoes(String id, String expressao) {
		super();
		this.id = id;
		this.expressao = expressao;
	}
	@Override
	public String toString() {
		return "Expressoes [id=" + id + ", expressao=" + expressao + "]";
	}
}
