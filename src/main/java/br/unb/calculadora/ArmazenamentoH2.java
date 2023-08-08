package br.unb.calculadora;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;


// Implementação do armazenamento em banco de dados H2
public class ArmazenamentoH2 implements Armazenamento {
    private Connection connection;

    public ArmazenamentoH2() {
        try {
            // Carrega o driver JDBC do H2 e cria a conexão com o banco de dados
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void salvarExpressao(String id, String expressao) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO expressoes (id, expressao) VALUES (?, ?)")) {
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, expressao);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void salvarOperacao(String id, Nodo operacao) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO operacoes (id, operacao) VALUES (?, ?)")) {
            preparedStatement.setString(1, id);
            preparedStatement.setObject(2, operacao, Types.OTHER);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void salvarResultado(String id, int resultado) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO resultados (id, resultado) VALUES (?, ?)")) {
            preparedStatement.setString(1, id);
            preparedStatement.setInt(2, resultado);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String recuperarExpressao(String id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT expressao FROM expressoes WHERE id = ?")) {
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
            	return   resultSet.getString("expressao"); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Operacao recuperarOperacao(String id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT operacao FROM operacoes WHERE id = ?")) {
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
            	return  (Operacao) resultSet.getObject("operacao"); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int recuperarResultado(String id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT resultado FROM resultados WHERE id = ?")) {
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
            	return   resultSet.getInt("resultado"); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<String> listarExpressoes() {
    	List<String> expressoes = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM expressoes")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            	expressoes.add(resultSet.getString("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return expressoes;
    	
    }
    
}
