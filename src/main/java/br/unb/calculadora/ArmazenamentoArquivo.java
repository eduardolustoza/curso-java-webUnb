package br.unb.calculadora;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ArmazenamentoArquivo implements  Armazenamento {
	private String diretorio = "./";

	@Override
	public void salvarExpressao(String id, String expressao) {
		try (FileWriter fileWriter = new FileWriter(diretorio + id + "_expressao.txt");
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
				bufferedWriter.write(expressao);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void salvarOperacao(String id, Nodo operacao) {
		try (FileOutputStream fileOutputStream = new FileOutputStream(diretorio + id + "_operacao.obj");
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
			objectOutputStream.writeObject(operacao);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void salvarResultado(String id, int resultado) {
		try (FileWriter fileWriter = new FileWriter(diretorio + id + "_resultado.txt");
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
				bufferedWriter.write(Integer.toString(resultado));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String recuperarExpressao(String id) {
		try (FileReader fileReader = new FileReader(diretorio + id + "_expressao.txt");
				BufferedReader bufferedReader = new BufferedReader(fileReader)) {
			return bufferedReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Operacao recuperarOperacao(String id) {
		try (FileInputStream fileInputStream = new FileInputStream(diretorio + id + "_operacao.obj");
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
			return (Operacao) objectInputStream.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int recuperarResultado(String id) {
		try (FileReader fileReader = new FileReader(diretorio + id + "_resultado.txt");
				BufferedReader bufferedReader = new BufferedReader(fileReader)) {
			return Integer.parseInt(bufferedReader.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<String> listarExpressoes() {
		List<String> expressoes = new ArrayList<>();
		File dir = new File(diretorio);
		File[] files = dir.listFiles();
		if(files != null) {
			for (File file : files) {
				if (file.getName().endsWith("_expressao.txt")) {
					String id = file.getName().replace("_expressao.txt", "");
					expressoes.add(id);
				}
			}
		}
		return expressoes;
	}

	
		
}