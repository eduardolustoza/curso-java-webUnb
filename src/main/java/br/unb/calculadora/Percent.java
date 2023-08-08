package br.unb.calculadora;

public class Percent extends Operacao {
	
	
	public Percent(Nodo esquerda, Nodo direita) {
		super(esquerda, direita);
	}

	@Override
	protected int executa(int esquerda, int direita) {
		return (int) ((esquerda * direita) / 100.0);
	}

	@Override
	protected String simbolo() {
		return "%";
	}

}
