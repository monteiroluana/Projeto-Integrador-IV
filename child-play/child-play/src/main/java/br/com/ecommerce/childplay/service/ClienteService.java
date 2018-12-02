package br.com.ecommerce.childplay.service;

import br.com.ecommerce.childPlay.model.Cliente;
import br.com.ecommerce.childplay.dao.ClienteDAO;
import br.com.ecommerce.childplay.utils.Email;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.mail.MessagingException;

public class ClienteService {

    public List<Cliente> listClientes() throws ClassNotFoundException, SQLException {
        ClienteDAO clienteDao = new ClienteDAO();
        return new ArrayList<>(clienteDao.listClientes());
    }

    public Cliente authClienteByEmailSenha(String login, String senha) throws ClassNotFoundException, SQLException {
        ClienteDAO clienteDao = new ClienteDAO();
        return clienteDao.authClienteByEmailSenha(login, senha);
    }
    
    public Cliente getClientePorEmail(String email) throws ClassNotFoundException, SQLException {
        ClienteDAO clienteDao = new ClienteDAO();
        return clienteDao.getClientePorEmail(email);
    }

    public String save(Cliente cliente) throws ClassNotFoundException, SQLException, MessagingException {
        ClienteDAO clienteDao = new ClienteDAO();
        String msg = null;
        if (clienteDao.save(cliente)) {
            msg = "Conta cadastrada com Sucesso!";
            Email email = new Email();
            String subject = "D.Evolution! ";
            String emailBody = "Parabéns por se cadastrar em nosso site! " +
                    "<br><br> Login: "+cliente.getEmail()+"<br> Senha: "+cliente.getSenha()+
                    "<br><br>Atenciosamente,<br> D.Evolution.Admin";
    
            email.generateAndSendEmail(cliente.getEmail(), emailBody, subject);
        } else {
            msg = "Erro ao cadastrar conta!";
        }
        return msg;
    }

    public String update(Cliente cliente) throws ClassNotFoundException, SQLException {
        ClienteDAO clienteDao = new ClienteDAO();
        String msg = null;
        if (clienteDao.update(cliente)) {
            msg = "Conta atualizada com Sucesso!";
        } else {
            msg = "Erro ao atualizar conta!";
        }
        return msg;
    }

    public String enable(Cliente cliente) throws ClassNotFoundException, SQLException {
        ClienteDAO clienteDao = new ClienteDAO();
        String msg = null;
        if (clienteDao.enable(cliente)) {
           msg = "Conta desativada  com Sucesso!";
        } else {
            msg = "Erro ao desativar conta!";
        }
        return msg;
    }
}
