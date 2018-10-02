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
}
