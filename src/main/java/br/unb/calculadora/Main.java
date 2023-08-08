package br.unb.calculadora;

import java.util.List;

public class Main {
	public static void main(String[] args) {
//		Armazenamento armazenamento = new ArmazenamentoArquivo();
		Armazenamento armazenamento = new ArmazenamentoH2();

		// Expressões a serem armazenadas
		String expressao1 = "(5 + (10 / 2))";
		String expressao2 = "(3 * (8 + 2))";

		// ID das expressões
		String id1 = "expressao1";
		String id2 = "expressao2";

		// Removendo espaços em branco
		String semEspacos1 = expressao1.replaceAll("\\s+", "");
		String semEspacos2 = expressao2.replaceAll("\\s+", "");

		// Criando as operações
		Nodo operacao1 = criarOperacao(semEspacos1);
		Nodo operacao2 = criarOperacao(semEspacos2);

		// Executando as operações e obtendo os resultados
		int resultado1 = operacao1.calcula();
		int resultado2 = operacao2.calcula();

		// Salvando as expressões, operações e resultados
		armazenamento.salvarExpressao(id1, expressao1);
		armazenamento.salvarOperacao(id1, operacao1);
		armazenamento.salvarResultado(id1, resultado1);

		armazenamento.salvarExpressao(id2, expressao2);
		armazenamento.salvarOperacao(id2, operacao2);
		armazenamento.salvarResultado(id2, resultado2);

		// Recuperando uma expressão, operação e resultado pelo ID
		String expressaoRecuperada = armazenamento.recuperarExpressao(id1);
		Operacao operacaoRecuperada = armazenamento.recuperarOperacao(id1);
		int resultadoRecuperado = armazenamento.recuperarResultado(id1);

		System.out.println("Expressão recuperada: " + expressaoRecuperada);
		System.out.println("Operação recuperada: " + operacaoRecuperada);
		System.out.println("Resultado recuperado: " + resultadoRecuperado);

		// Listando todas as expressões
		List<String> expressoes = armazenamento.listarExpressoes();
		System.out.println("Expressões armazenadas:");
		for (String id : expressoes) {
			System.out.println("- ID: " + id);
			System.out.println("  Expressão: " + armazenamento.recuperarExpressao(id));
			System.out.println("  Operação: " + armazenamento.recuperarOperacao(id));
			System.out.println("  Resultado: " + armazenamento.recuperarResultado(id));
		}
	}

	// Método recursivo para criar os objetos de operação a partir da expressão
	private static Nodo criarOperacao(String expressao) {
		// Verificando se é um valor numérico
		try {
			int valor = Integer.parseInt(expressao);
			return new Valor(valor);
		} catch (NumberFormatException e) {
			// Não é um valor numérico, trata-se de uma operação
			int nivelParenteses = 0;
			int indexOperador = -1;

			// Percorrendo a expressão para encontrar o operador de maior nível de
			// parenteses
			for (int i = 0; i < expressao.length(); i++) {
				char caractere = expressao.charAt(i);
				if (caractere == '(') {
					nivelParenteses++;
				} else if (caractere == ')') {
					nivelParenteses--;
				} else if (nivelParenteses == 1
						&& (caractere == '+' 
						|| caractere == '-' 
						|| caractere == '*' 
						|| caractere == '/' 
						|| caractere == '^' 
						|| caractere == '%')) {
					indexOperador = i;
				}
			}

			if (indexOperador != -1) {
				// Separando a expressão nos operandos e operador
				String operador = expressao.substring(indexOperador, indexOperador + 1);
				String operando1 = expressao.substring(1, indexOperador);
				String operando2 = expressao.substring(indexOperador + 1, expressao.length() - 1);

				// Chamando recursivamente o método para criar as operações dos operandos
				Nodo op1 = criarOperacao(operando1);
				Nodo op2 = criarOperacao(operando2);

				// Criando a operação com base no operador encontrado
				switch (operador) {
				case "+":
					return new Soma(op1, op2);
				case "-":
					// Implemente a classe Subtracao caso necessário
					throw new UnsupportedOperationException("Operador '-' não suportado.");
				case "*":
					// Implemente a classe Multiplicacao caso necessário
					return new Multiplicacao(op1, op2);
				case "/":
					return new Divisao(op1, op2);
				case "^":
					return new Exponenciacao(op1, op2);
				case "%":
					return new Percent(op1, op2);
				default:
					throw new IllegalArgumentException("Operador inválido.");
				}
			} else {
				throw new IllegalArgumentException("Expressão inválida.");
			}
		}
	}
}
