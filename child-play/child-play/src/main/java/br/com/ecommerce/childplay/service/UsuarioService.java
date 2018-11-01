package br.com.ecommerce.childPlay.service;

import br.com.ecommerce.childPlay.dao.UsuarioDAO;
import br.com.ecommerce.childPlay.model.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioService {

    public List<Usuario> listar() throws ClassNotFoundException, SQLException {
        UsuarioDAO usuarioDao = new UsuarioDAO();
        return new ArrayList<>(usuarioDao.listUsuarios());
    }

    public boolean authUsuarioByLoginSenha(String login, String senha) throws ClassNotFoundException, SQLException {
        UsuarioDAO usuarioDao = new UsuarioDAO();
        return usuarioDao.authUsuarioByLoginSenha(login, senha);
       
    }

    public String saveUsuario(Usuario usuario) throws ClassNotFoundException, SQLException {
        UsuarioDAO usuarioDao = new UsuarioDAO();
        String msg = null;
        if (usuarioDao.saveUsuario(usuario)) {
            msg = "Usuário foi inserido com Sucesso!";
        } else {
            msg = "Falha ao inserir o Usuário!";
        }
        return msg;
    }

}
