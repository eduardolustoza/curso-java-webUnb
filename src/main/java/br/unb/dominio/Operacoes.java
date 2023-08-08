package br.unb.dominio;

public class Operacoes {
	private String id;
	private String operacao;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOperacao() {
		return operacao;
	}
	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	@Override
	public String toString() {
		return "Operacoes [id=" + id + ", operacao=" + operacao + "]";
	}

}
