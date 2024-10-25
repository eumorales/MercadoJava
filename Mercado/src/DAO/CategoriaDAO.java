package DAO;
import Beans.Categoria;
import Conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    private Conexao conexao;
    private Connection conn;
    
    public CategoriaDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public void inserir(Categoria categoria) {
        String sql = "INSERT INTO categorias (nome) VALUES (?);";
        
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, categoria.getNome());
            stmt.execute();
        } catch(SQLException ex) {
            System.out.println("Erro ao inserir categoria: " + ex.getMessage());
        }
    }
    
    public List<Categoria> getCategorias() {
        String sql = "SELECT * FROM categoria";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            ResultSet rs = stmt.executeQuery();
            List<Categoria> listaCategorias = new ArrayList<>();
            
            while (rs.next()) {
                Categoria c = new Categoria();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                listaCategorias.add(c);
            }
            return listaCategorias;
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar categorias: " + ex.getMessage());
            return null;
        }
    }
    
    public Categoria getCategoria(int id) {
        String sql = "SELECT * FROM categoria WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            Categoria c = new Categoria();
            rs.first();
            c.setId(id);
            c.setNome(rs.getString("nome"));
            return c;
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar categoria: " + ex.getMessage());
            return null;
        }
    }

    public void editar(Categoria categoria) {
        try {
            String sql = "UPDATE categoria SET nome = ? WHERE id = ?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, categoria.getNome());
            stmt.setInt(2, categoria.getId());
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar categoria: " + ex.getMessage());
        }
    }
    
    public void excluir(int id) {
        try {
            String sql = "DELETE FROM categoria WHERE id = ?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro ao excluir categoria: " + ex.getMessage());
        }
    }
    
    public List<Categoria> getCategoriasNome(String nome) {
        String sql = "SELECT * FROM categoria WHERE nome LIKE ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();
            
            List<Categoria> listaCategorias = new ArrayList<>();
            
            while (rs.next()) {
                Categoria c = new Categoria();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                listaCategorias.add(c);
            }
            return listaCategorias;
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar categorias pelo nome: " + ex.getMessage());
            return null;
        }
    }
}
