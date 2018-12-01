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

    public Usuario authUsuarioByLoginSenha(String login, String senha) throws ClassNotFoundException, SQLException {
        UsuarioDAO usuarioDao = new UsuarioDAO();
        return usuarioDao.authUsuarioByLoginSenha(login, senha);

    }

    public Usuario getUsuario(String login) throws ClassNotFoundException, SQLException {
        UsuarioDAO usuarioDao = new UsuarioDAO();
        return usuarioDao.getUsuario(login);
    }

    public String save(Usuario usuario) throws ClassNotFoundException, SQLException {
        UsuarioDAO usuarioDao = new UsuarioDAO();
        String msg = null;
        if (usuarioDao.save(usuario)) {
            msg = "Conta cadastrada com Sucesso!";
        } else {
            msg = "Erro ao cadastrar conta!";
        }
        return msg;
    }

    public String update(Usuario usuario) throws ClassNotFoundException, SQLException {
        UsuarioDAO usuarioDao = new UsuarioDAO();
        String msg = null;
        if (usuarioDao.update(usuario)) {
            msg = "Conta atualizada com Sucesso!";
        } else {
            msg = "Erro ao atualizar conta!";
        }
        return msg;
    }

    public String enable(Usuario usuario) throws ClassNotFoundException, SQLException {
        UsuarioDAO usuarioDao = new UsuarioDAO();
        String msg = null;
        if (usuarioDao.enable(usuario)) {
            msg = "Conta desativada  com Sucesso!";
        } else {
            msg = "Erro ao desativar conta!";
        }
        return msg;
    }

}
