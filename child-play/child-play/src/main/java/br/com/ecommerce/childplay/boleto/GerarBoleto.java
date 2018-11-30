package br.com.ecommerce.childplay.boleto;

import br.com.caelum.stella.boleto.Banco;
import br.com.caelum.stella.boleto.Beneficiario;
import br.com.caelum.stella.boleto.Boleto;
import br.com.caelum.stella.boleto.Datas;
import br.com.caelum.stella.boleto.Endereco;
import br.com.caelum.stella.boleto.Pagador;
import br.com.caelum.stella.boleto.bancos.BancoDoBrasil;
import br.com.caelum.stella.boleto.transformer.GeradorDeBoleto;
import br.com.ecommerce.childplay.model.PlanZ;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boleto")
public class GerarBoleto {

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = {"text/plain;charset=UTF-8", "application/*"})
    public void generateReport(@RequestBody PlanZ pedido, HttpServletResponse response) throws IOException {

        Calendar cal = new GregorianCalendar();
        cal.setTime(pedido.getDataPedido());
        cal.add(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.MONTH, 1);
        
        Calendar cal2 = new GregorianCalendar();
        cal2.setTime(cal.getTime());
        cal2.add(Calendar.DAY_OF_MONTH,5);

        Datas datas = Datas.novasDatas()
                .comDocumento(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH), cal.get(Calendar.YEAR))
                .comProcessamento(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH), cal.get(Calendar.YEAR))
                .comVencimento(cal2.get(Calendar.DAY_OF_MONTH), cal2.get(Calendar.MONTH), cal2.get(Calendar.YEAR));

        Endereco enderecoBeneficiario = Endereco.novoEndereco()
                .comLogradouro("Av das Empresas, 555")
                .comBairro("Bairro Grande")
                .comCep("01234-555")
                .comCidade("São Paulo")
                .comUf("SP");

        //Quem emite o boleto
        Beneficiario beneficiario = Beneficiario.novoBeneficiario()
                .comNomeBeneficiario("D-Evolution")
                .comAgencia("xxxx").comDigitoAgencia("0")
                .comCodigoBeneficiario("76000")
                .comDigitoCodigoBeneficiario("5")
                .comNumeroConvenio("1207113")
                .comCarteira("18")
                .comEndereco(enderecoBeneficiario)
                .comNossoNumero("1155661133");

        //Quem paga o boleto
        Endereco enderecoPagador = Endereco.novoEndereco()
                .comLogradouro(pedido.getLogradouro())
                .comBairro(pedido.getBairro())
                .comCep(pedido.getCep())
                .comCidade(pedido.getCidade())
                .comUf(pedido.getUf());

        Pagador pagador = Pagador.novoPagador()
                .comNome(pedido.getCliente().getNome())
                .comDocumento("cpf: "+pedido.getCliente().getCpf())
                .comEndereco(enderecoPagador);

        Banco banco = new BancoDoBrasil();

        Boleto boleto = Boleto.novoBoleto()
                .comBanco(banco)
                .comDatas(datas)
                .comBeneficiario(beneficiario)
                .comPagador(pagador)
                .comValorBoleto(pedido.getValorTotal())
                .comNumeroDoDocumento(pedido.getProtocolo())
                .comInstrucoes("Não receber após vencimento")
                .comLocaisDePagamento("Pagável em qualquer agẽncia bancária até a data de vencimento");

        GeradorDeBoleto gerador = new GeradorDeBoleto(boleto);
        byte[] bPDF = gerador.geraPDF();

        streamReport(response, bPDF, "my_report.pdf");
    }

    protected void streamReport(HttpServletResponse response, byte[] data, String name)
            throws IOException {

        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "attachment; filename=" + name);
        response.setContentLength(data.length);

        response.getOutputStream().write(data);
        response.getOutputStream().flush();
    }
}
