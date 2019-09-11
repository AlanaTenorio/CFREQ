/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package relatorios;

import auxiliar.RelatorioAdm;
import controladores.ControleFalta;
import controladores.ControleReposicao;
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
@ManagedBean(name = "gerarRelatorioFaltasServidor")
public class GerarRelatorioFaltasServidor {
    public static void gerarRelatorio(List<RelatorioAdm> faltas) throws JRException, ParseException, IOException {

        JasperReport report = JasperCompileManager.compileReport("C:\\Users\\AlanaMaria\\Desktop\\CFREQ\\src\\java\\relatorios\\relatorioFaltasServidor.jrxml");
        JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(faltas));

        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
                .getCurrentInstance().getExternalContext().getResponse();

        httpServletResponse.addHeader("Content-disposition",
                "attachment; filename=relatorioFaltasServidor.pdf");

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
                "Relatorio_Faltas_Servidor.pdf");

        FacesContext.getCurrentInstance().responseComplete();

    }

    public void preencherRelatorio(Servidor servidor, Date incio, Date termino) throws JRException, ParseException, IOException {
        List<RelatorioAdm> listaRelatorio = new ArrayList<RelatorioAdm>();
        RelatorioAdm relatorio = new RelatorioAdm();
        List<Falta> listaFaltas = ControleFalta.getInstance().listarFaltasPorServidorInicioTermino(incio, termino, servidor);
        
        relatorio.setNome(servidor.getNome());
        relatorio.setTotalFaltas(listaFaltas.size());
        relatorio.setTotalReposicoes(ControleReposicao.getInstance().listarReposicoesServidorData(servidor, incio, termino).size());
        relatorio.setTotalReposicoes(ControleReposicao.getInstance().listarAntecipacoesServidorData(servidor, incio, termino).size());
        relatorio.setFaltas1011(ControleFalta.getInstance().contarFaltas1011(listaFaltas));
        relatorio.setFaltas1011(ControleFalta.getInstance().contarFaltas1012(listaFaltas));
        relatorio.setFaltas1011(ControleFalta.getInstance().contarFaltas1013(listaFaltas));
        relatorio.setFaltas1011(ControleFalta.getInstance().contarFaltas1014(listaFaltas));
        relatorio.setFaltas1011(ControleFalta.getInstance().contarFaltas1015(listaFaltas));
        relatorio.setFaltas1011(ControleFalta.getInstance().contarFaltas1016(listaFaltas));
        relatorio.setFaltas1011(ControleFalta.getInstance().contarFaltas1017(listaFaltas));
        relatorio.setFaltas1011(ControleFalta.getInstance().contarFaltas1018(listaFaltas));
        relatorio.setFaltas1011(ControleFalta.getInstance().contarFaltas1019(listaFaltas));
        relatorio.setFaltas1011(ControleFalta.getInstance().contarFaltas1020(listaFaltas));
        if(relatorio.getTotalFaltas()== null){
            relatorio.setTotalFaltas(0);
        }
        
        if(relatorio.getTotalReposicoes() == null){
            relatorio.setTotalReposicoes(0);
        }
        
        if(relatorio.getTotalAntecipacoes() == null){
            relatorio.setTotalAntecipacoes(0);
        }
        
        if(relatorio.getFaltas1011() == null){
            relatorio.setFaltas1011(0);
        }
        if(relatorio.getFaltas1012() == null){
            relatorio.setFaltas1012(0);
        }
        if(relatorio.getFaltas1013() == null){
            relatorio.setFaltas1013(0);
        }
        if(relatorio.getFaltas1014() == null){
            relatorio.setFaltas1014(0);
        }
        if(relatorio.getFaltas1015() == null){
            relatorio.setFaltas1015(0);
        }
        if(relatorio.getFaltas1016() == null){
            relatorio.setFaltas1016(0);
        }
        if(relatorio.getFaltas1017() == null){
            relatorio.setFaltas1017(0);
        }
        if(relatorio.getFaltas1018() == null){
            relatorio.setFaltas1018(0);
        }
        if(relatorio.getFaltas1019() == null){
            relatorio.setFaltas1019(0);
        }
        if(relatorio.getFaltas1020() == null){
            relatorio.setFaltas1020(0);
        }
        
        listaRelatorio.add(relatorio);
        
        gerarRelatorio(listaRelatorio);
    }
}
