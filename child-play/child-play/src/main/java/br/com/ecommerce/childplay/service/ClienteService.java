package br.com.ecommerce.childplay.service;

import br.com.ecommerce.childPlay.model.Cliente;
import br.com.ecommerce.childplay.dao.ClienteDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteService {

    public List<Cliente> listClientes() throws ClassNotFoundException, SQLException {
        ClienteDAO clienteDao = new ClienteDAO();
        return new ArrayList<>(clienteDao.listClientes());
    }
    
    public Cliente getClienteByLoginSenha(String login, String senha) throws ClassNotFoundException, SQLException {
        ClienteDAO clienteDao = new ClienteDAO();
        return clienteDao.getClienteByLoginSenha(login, senha);
    }
   
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
