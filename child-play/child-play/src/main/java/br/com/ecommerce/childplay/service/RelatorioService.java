
package br.com.ecommerce.childplay.service;

import br.com.ecommerce.childplay.dao.RelatorioDAO;
import br.com.ecommerce.childplay.model.Relatorio;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RelatorioService {
    public List<Relatorio> relatorio() throws ClassNotFoundException, SQLException {
        RelatorioDAO relatorioDao = new RelatorioDAO();
        return new ArrayList<>(relatorioDao.relatorio());
    }    
}
