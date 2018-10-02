package br.com.ecommerce.childPlay.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexao {

    private static Connection connection;
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/childplaydb";
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    private Conexao() {
    }

    public static synchronized Connection getConnection() {

        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar o driver de conexão\n" + e);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao estabelecer conexão com o banco de dados\n" + e);
        }
//        }        
        return connection;
    }

}
