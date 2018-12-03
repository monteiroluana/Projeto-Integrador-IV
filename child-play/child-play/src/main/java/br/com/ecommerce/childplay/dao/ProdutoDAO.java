package br.com.ecommerce.childPlay.dao;

import br.com.ecommerce.childPlay.conexao.Conexao;
import br.com.ecommerce.childPlay.model.Produto;
import br.com.ecommerce.childplay.model.Imagem;
import br.com.ecommerce.childplay.dao.ImagemDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public List<Produto> listProduto() throws ClassNotFoundException, SQLException {

        String sql = "SELECT idProduto, nome, marca, descricao, caracteristicas, idade,"
                + "categoria, preco, estoque, desconto, enable "
                + "FROM produto WHERE enable = ?;";

        List<Produto> lista = new ArrayList<>();

        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement p = null;

        try {
            connection = Conexao.getConnection();
            p = connection.prepareStatement(sql);
            p.setBoolean(1, true);
            //Armazenando os resultados
            rs = p.executeQuery();

            //Enquanto tiver linha de resultado execute esse trecho
            while (rs.next()) {
                Produto produto = new Produto();

                produto.setIdProduto(rs.getInt("idProduto"));
                produto.setNome(rs.getString("nome"));
                produto.setMarca(rs.getString("marca"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setCaracteristicas(rs.getString("caracteristicas"));
                produto.setCategoria(rs.getString("categoria"));
                produto.setIdade(rs.getInt("idade"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setEstoque(rs.getInt("estoque"));
                produto.setDesconto(rs.getInt("desconto"));
                produto.setEnable(rs.getBoolean("enable"));

                ImagemDAO imagemDao = new ImagemDAO();
                List<Imagem> imgList = imagemDao.getImagemByProdutoId(produto.getIdProduto());
                produto.setImagem(imgList);

                lista.add(produto);
            }
        } catch (SQLException e) {

        } finally {
            //Fechando todas as conexões que foram abertas
            try {
                if (connection != null) {
                    connection.close();
                }
                if (p != null) {
                    p.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {

            }
        }
        return lista;
    }

    public Produto getProdutoById(int id) throws ClassNotFoundException, SQLException {

        String sql = "SELECT idProduto, nome, marca ,descricao ,caracteristicas ,idade ,"
                + "categoria ,preco ,estoque ,desconto, enable "
                + "FROM produto WHERE enable = ? AND idProduto = ? ;";

        Produto produto = new Produto();
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement p = null;

        try {
            connection = Conexao.getConnection();
            p = connection.prepareStatement(sql);
            p.setBoolean(1, true);
            p.setInt(2, id);
            //Armazenando os resultados
            rs = p.executeQuery();

            if (rs.next()) {
                produto.setIdProduto(rs.getInt("idProduto"));
                produto.setNome(rs.getString("nome"));
                produto.setMarca(rs.getString("marca"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setCaracteristicas(rs.getString("caracteristicas"));
                produto.setCategoria(rs.getString("categoria"));
                produto.setIdade(rs.getInt("idade"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setEstoque(rs.getInt("estoque"));
                produto.setDesconto(rs.getInt("desconto"));
                produto.setEnable(rs.getBoolean("enable"));

                ImagemDAO imagemDao = new ImagemDAO();
                List<Imagem> imgList = imagemDao.getImagemByProdutoId(produto.getIdProduto());
                produto.setImagem(imgList);
            }
        } catch (SQLException e) {

        } finally {
            //Fechando todas as conexões que foram abertas

            if (connection != null) {
                connection.close();
            }
            if (p != null) {
                p.close();
            }
            if (rs != null) {
                rs.close();
            }

        }
        return produto;
    }

    public List<Produto> getProdutoByNome(String nome) throws ClassNotFoundException, SQLException {

        String sql = "SELECT idProduto, nome, marca, descricao, caracteristicas, idade,"
                + "categoria, preco, estoque, desconto, enable "
                + "FROM produto WHERE enable = ? AND nome LIKE ? ;";

        List<Produto> lista = new ArrayList<>();
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement p = null;

        try {
            System.out.println("Produto DAO");
            connection = Conexao.getConnection();
            p = connection.prepareStatement(sql);
            p.setBoolean(1, true);
            p.setString(2, "%" + nome + "%");
            //Armazenando os resultados
            rs = p.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setIdProduto(rs.getInt("idProduto"));
                produto.setNome(rs.getString("nome"));
                produto.setMarca(rs.getString("marca"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setCaracteristicas(rs.getString("caracteristicas"));
                produto.setCategoria(rs.getString("categoria"));
                produto.setIdade(rs.getInt("idade"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setEstoque(rs.getInt("estoque"));
                produto.setDesconto(rs.getInt("desconto"));
                produto.setEnable(rs.getBoolean("enable"));

                ImagemDAO imagemDao = new ImagemDAO();
                List<Imagem> imgList = imagemDao.getImagemByProdutoId(produto.getIdProduto());
                produto.setImagem(imgList);
                lista.add(produto);
            }
        } catch (SQLException e) {

        } finally {
            //Fechando todas as conexões que foram abertas

            if (connection != null) {
                connection.close();
            }
            if (p != null) {
                p.close();
            }
            if (rs != null) {
                rs.close();
            }

        }
        return lista;
    }

    public boolean save(Produto produto) throws SQLException {
        String sql = "INSERT INTO PRODUTO (nome, marca, descricao, caracteristicas, idade, "
                + "categoria, preco, estoque, desconto, enable) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?)";

        Connection connection = null;
        PreparedStatement p = null;
        int idGerado = -1;

        try {
            connection = Conexao.getConnection();
            p = connection.prepareStatement(sql, p.RETURN_GENERATED_KEYS);

            p.setString(1, produto.getNome());
            p.setString(2, produto.getMarca());
            p.setString(3, produto.getDescricao());
            p.setString(4, produto.getCaracteristicas());
            p.setInt(5, produto.getIdade());
            p.setString(6, produto.getCategoria());
            p.setDouble(7, produto.getPreco());
            p.setInt(8, produto.getEstoque());
            p.setInt(9, produto.getDesconto());
            p.setBoolean(10, true);

            p.execute();

            ResultSet rs = p.getGeneratedKeys();
            if (rs.next()) {
                idGerado = rs.getInt(1);
                System.out.println("idGerado: " + idGerado);
            }

            List<Imagem> listImagens = new ArrayList<>();
            listImagens = produto.getImagem();

            if (!listImagens.isEmpty()) {
                for (Imagem img : listImagens) {
                    img.setIdProduto(idGerado);
                    ImagemDAO imagemDao = new ImagemDAO();
                    imagemDao.save(img);
                }
            }

            return (true);

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return (false);

        } finally {

            if (connection != null) {
                connection.close();
            }
            if (p != null) {
                p.close();
            }

        }
    }

    public boolean update(Produto produto) throws SQLException {

        String sql = "UPDATE PRODUTO SET nome = ?, marca = ?, descricao = ?, caracteristicas = ?, idade = ?, "
                + "categoria = ?, preco = ?, estoque = ?, desconto = ? WHERE idProduto = ?";

        Connection connection = null;
        PreparedStatement p = null;

        try {
            connection = Conexao.getConnection();
            p = connection.prepareStatement(sql);

            p.setString(1, produto.getNome());
            p.setString(2, produto.getMarca());
            p.setString(3, produto.getDescricao());
            p.setString(4, produto.getCaracteristicas());
            p.setInt(5, produto.getIdade());
            p.setString(6, produto.getCategoria());
            p.setDouble(7, produto.getPreco());
            p.setInt(8, produto.getEstoque());
            p.setInt(9, produto.getDesconto());
            p.setInt(10, produto.getIdProduto());

            p.execute();

            return true;

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return false;

        } finally {
            if (connection != null) {
                connection.close();
            }
            if (p != null) {
                p.close();
            }
        }
    }

    public boolean enable(Produto produto) throws SQLException {

        String sql = "UPDATE PRODUTO SET enable = ? WHERE idProduto = ?";

        System.out.println("idProduto: "+produto.getIdProduto());
        Connection connection = null;
        PreparedStatement p = null;

        try {
            connection = Conexao.getConnection();
            p = connection.prepareStatement(sql);
            p.setBoolean(1, false);
            p.setInt(2, produto.getIdProduto());
            p.execute();

            return true;

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return false;

        } finally {
            if (connection != null) {
                connection.close();
            }
            if (p != null) {
                p.close();
            }
        }
    }

    public boolean setEstoqueProduto(int idProduto, int qtdProduto) throws SQLException {

        String sql = "UPDATE produto SET estoque = ?  where idProduto = ?";

        Connection connection = null;
        PreparedStatement p = null;

        try {
            connection = Conexao.getConnection();
            p = connection.prepareStatement(sql);

            p.setInt(1, qtdProduto);
            p.setInt(2, idProduto);
            p.execute();

            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }

    }

}
