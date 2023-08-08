package br.unb.calculadora;

public class CalculadoraHelper {
	
	public static int Calcular (String expressao) {
//		Armazenamento armazenamento = new ArmazenamentoArquivo();
		Armazenamento armazenamento = new ArmazenamentoH2();

		// Removendo espaços em branco
		String semEspacos = expressao.replaceAll("\\s+", "");

		// Criando as operações
		Nodo operacao = criarOperacao(semEspacos);

		// Executando as operações e obtendo os resultados
		int resultado = operacao.calcula();
		return resultado;
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
