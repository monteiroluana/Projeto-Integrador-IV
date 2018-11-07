package br.com.ecommerce.childplay.z;

import br.com.caelum.stella.boleto.Banco;
import br.com.caelum.stella.boleto.Beneficiario;
import br.com.caelum.stella.boleto.Boleto;
import br.com.caelum.stella.boleto.Datas;
import br.com.caelum.stella.boleto.Endereco;
import br.com.caelum.stella.boleto.Pagador;
import br.com.caelum.stella.boleto.bancos.BancoDoBrasil;
import br.com.caelum.stella.boleto.transformer.GeradorDeBoleto;
import br.com.ecommerce.childplay.model.Pedido;
import br.com.ecommerce.childplay.model.PlanZ;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/zday")
public class zDay {

   @CrossOrigin(origins = "*")
    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = {"text/plain;charset=UTF-8", "application/*"})
    public void generateReport(@RequestBody PlanZ pedido,HttpServletResponse response) throws IOException {

        Datas datas = Datas.novasDatas()
                .comDocumento(1, 5, 2008)
                .comProcessamento(1, 5, 2008)
                .comVencimento(2, 5, 2008);

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
                .comLogradouro(pedido.getEndereco().getLogradouro())
                .comBairro(pedido.getEndereco().getBairro())
                .comCep(pedido.getEndereco().getCep())
                .comCidade(pedido.getEndereco().getCidade())
                .comUf(pedido.getEndereco().getEstado());

        Pagador pagador = Pagador.novoPagador()
                .comNome(pedido.getCliente().getNome())
                .comDocumento(pedido.getCliente().getCpf())
                .comEndereco(enderecoPagador);

        Banco banco = new BancoDoBrasil();

        Boleto boleto = Boleto.novoBoleto()
                .comBanco(banco)
                .comDatas(datas)
                .comBeneficiario(beneficiario)
                .comPagador(pagador)
                .comValorBoleto(pedido.getValorTotal())
                .comNumeroDoDocumento(pedido.getProtocolo())
                .comInstrucoes(pedido.listItens())
                .comLocaisDePagamento("local 1", "local 2");

        System.out.println("número código de barras: " + boleto.getCodigoDeBarras());

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
