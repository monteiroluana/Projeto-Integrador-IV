package br.com.ecommerce.childplay.service;

import br.com.ecommerce.childPlay.model.Cliente;
import br.com.ecommerce.childplay.dao.ClienteDAO;
import java.sql.SQLException;

public class ClienteService {

    public String saveCliente(Cliente cliente) throws ClassNotFoundException, SQLException {
        ClienteDAO clienteDao = new ClienteDAO();
        String msg = null;
        if (clienteDao.saveCliente(cliente)) {
            msg = "Cliente cadastrado com Sucesso!";
        } else {
            msg = "Falha ao cadastrar cliente!";
        }
        return msg;
    }
}
