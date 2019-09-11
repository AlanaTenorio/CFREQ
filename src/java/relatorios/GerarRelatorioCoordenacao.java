
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorios;

import auxiliar.RelatorioCoordenacao;
import controladores.ControleFalta;
import controladores.ControleServidor;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import negocio.Coordenacao;
import negocio.Falta;
import negocio.Servidor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Alana Tenório
 */
@ManagedBean(name = "gerarRelatorioCoordenacaoData")
public class GerarRelatorioCoordenacao {

    public static void gerarRelatorio(List<RelatorioCoordenacao> faltas) throws JRException, ParseException, IOException {

        JasperReport report = JasperCompileManager.compileReport("C:\\Users\\AlanaMaria\\Desktop\\CFREQ\\src\\java\\relatorios\\relatorioCoordenacao.jrxml");
        JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(faltas));

        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
                .getCurrentInstance().getExternalContext().getResponse();

        httpServletResponse.addHeader("Content-disposition",
                "attachment; filename=relatorioFaltasCoordenacao.pdf");

        ExternalContext externalContext = FacesContext.getCurrentInstance()
                .getExternalContext();

        ServletContext servletContext = (ServletContext) externalContext
                .getContext();

        ServletOutputStream servletOutputStream = httpServletResponse
                .getOutputStream();

        byte[] bytes = JasperExportManager.exportReportToPdf(print);

        httpServletResponse.setContentLength(bytes.length);
        httpServletResponse.getOutputStream().write(bytes, 0, bytes.length);
        httpServletResponse.getOutputStream().flush();
        httpServletResponse.getOutputStream().close();
        FacesContext.getCurrentInstance().responseComplete();
        JasperExportManager.exportReportToPdfStream(print,
                servletOutputStream);
        JasperExportManager.exportReportToPdfFile(print,
                "Relatorio_Falta_Coordenacao.pdf");

        FacesContext.getCurrentInstance().responseComplete();

    }

    public void preencherRelatorio(Date inicio, Date termino, Coordenacao coordenacao) throws JRException, ParseException, IOException {
        List<RelatorioCoordenacao> listaRelatorio = new ArrayList<RelatorioCoordenacao>();
        ControleFalta cFalta = new ControleFalta();
        List<Servidor> listaServidores = ControleServidor.getInstance().listarServidoresPorCoordenacao(coordenacao);
        List<Falta> listaFaltas;
        for (Servidor servaux : listaServidores) {
            listaFaltas = cFalta.listarFaltasPorServidorInicioTermino(inicio, termino, servaux);
            for (Falta faltaaux : listaFaltas) {
                RelatorioCoordenacao relatorio = new RelatorioCoordenacao();
                relatorio.setServidor(servaux);
                relatorio.setObservacao(faltaaux.getObservacao());
                relatorio.setDataFalta(faltaaux.getDataFalta());
                relatorio.setTurma(faltaaux.getTurma());
                relatorio.setCoordenacao(coordenacao);
                listaRelatorio.add(relatorio);
            }
        }

        gerarRelatorio(listaRelatorio);
    }

}
