package br.unb.struts;

import org.apache.struts.action.ActionForm;

public class CalculadoraForm extends ActionForm {
	public String expressao;
	public int resultado;

	public String getExpressao() {
		return expressao;
	}

	public void setExpressao(String expressao) {
		this.expressao = expressao;
	}

	public int getResultado() {
		return resultado;
	}

	public void setResultado(int resultado) {
		this.resultado = resultado;
	}

}
