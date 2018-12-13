package br.com.ecommerce.childplay.service;

import br.com.ecommerce.childPlay.dao.ProdutoDAO;
import br.com.ecommerce.childPlay.model.Cliente;
import br.com.ecommerce.childPlay.model.Produto;
import br.com.ecommerce.childplay.dao.ClienteDAO;
import br.com.ecommerce.childplay.model.ClienteInteressado;
import br.com.ecommerce.childplay.model.ContatoCliente;
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
    
    public String enviaEmailAviseMe(ClienteInteressado cliInteressado) throws ClassNotFoundException, SQLException, MessagingException {        
        String msg = null;        
        ProdutoDAO produtodao = new ProdutoDAO();
        Produto produto = produtodao.getProdutoById(cliInteressado.getIdProduto());
        
        
        
        try{
            msg = "Você será avisado por email quando o produto de seu interesse estiver em estoque";
            Email email = new Email();
            String subject = "ChildPlay - Interesse em produto";
            String emailBody = "Que pena " + cliInteressado.getNome() + "! O produto "+ produto.getNome() + " da marca " + produto.getMarca() + " não está disponível no momento." +
                    "<br><br> Mas não se preocupe, assim que chegar, avisaremos por email!" +
                    "<br><br>Atenciosamente,<br> BackOffice ChildPlay";
    
            email.generateAndSendEmail(cliInteressado.getEmail(), emailBody, subject);
        } catch(Exception ex) {
            msg = "Erro ao cadastrar conta!";
        }
        return msg;
    }
    
    //EmailContatoCliente
    public String EmailContatoCliente(ContatoCliente contatoCliente) throws ClassNotFoundException, SQLException, MessagingException {        
        String msg = null;                                
        
        try{
            msg = "Email enviado! Assim que um de nossos colaboradores visualizare o email, retornará em contato!";
            Email email = new Email();
            String subject = "Contato de cliente. Nome: " + contatoCliente.getNome();
            String emailBody = "O cliente  " + contatoCliente.getNome() + "entrou em contato com a seguinte mensagem: " +
                    "<br>" + contatoCliente.getMensagem() +
                    "<br><br> Caro Backoffice, retorne em 24 horas para o email " +contatoCliente.getEmail() +
                    "<br> BackOffice ChildPlay";
    
            email.generateAndSendEmail("devolution.tads@gmail.com", emailBody, subject);
        } catch(Exception ex) {
            msg = "Erro!";
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
