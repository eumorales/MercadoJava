package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    public Connection getConexao(){
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Mercado?useTimezone=true&serverTimezone=UTC", "root", "laboratorio");
            System.out.println("Conexão realizada");
            return conn;
        }
        catch (Exception e){
            System.out.println ("Erro ao conectar no banco " +e.getMessage());
            return null;
        }
    }
}
