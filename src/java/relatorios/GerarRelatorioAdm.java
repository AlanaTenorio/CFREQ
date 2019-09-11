/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorios;

import controladores.ControleFalta;
import controladores.ControleReposicao;
import controladores.ControleServidor;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import negocio.Servidor;
import auxiliar.RelatorioAdm;
import java.io.IOException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
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
@ManagedBean(name = "gerarRelatorioAdm")
public class GerarRelatorioAdm {

    public static void gerarRelatorio(List<RelatorioAdm> faltas) throws JRException, ParseException, IOException {

        JasperReport report = JasperCompileManager.compileReport("C:\\Users\\AlanaMaria\\Desktop\\CFREQ\\src\\java\\relatorios\\relatorioADM.jrxml");
        JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(faltas));

        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
                .getCurrentInstance().getExternalContext().getResponse();

        httpServletResponse.addHeader("Content-disposition",
                "attachment; filename=relatorioFaltasADM.pdf");

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
                "Relatorio_Faltas_ADM.pdf");

        FacesContext.getCurrentInstance().responseComplete();

    }

    public void preencherRelatorio(int mes, int ano) throws JRException, ParseException, IOException {
        List<Servidor> listaServidores = ControleServidor.getInstance().listarServidoresAtivos();
        List<RelatorioAdm> faltas = new ArrayList<RelatorioAdm>();
        for (Servidor auxServ : listaServidores) {
            RelatorioAdm sr = new RelatorioAdm();
            sr.setNome(auxServ.getNome());
            sr.setTotalFaltas(ControleFalta.getInstance().listarFaltasPorServidorMes(mes, auxServ, ano).size());
            sr.setTotalReposicoes(ControleReposicao.getInstance().listarReposicoesServidorMes(mes, auxServ, ano).size());
            sr.setTotalAntecipacoes(ControleReposicao.getInstance().listarAntecipacoesServidorMes(mes, auxServ, ano).size());
            sr.setFaltas1011(ControleFalta.getInstance().listarFaltasPorServidorMes1011(mes, auxServ, ano).size());
            sr.setFaltas1012(ControleFalta.getInstance().listarFaltasPorServidorMes1012(mes, auxServ, ano).size());
            sr.setFaltas1013(ControleFalta.getInstance().listarFaltasPorServidorMes1013(mes, auxServ, ano).size());
            sr.setFaltas1014(ControleFalta.getInstance().listarFaltasPorServidorMes1014(mes, auxServ, ano).size());
            sr.setFaltas1015(ControleFalta.getInstance().listarFaltasPorServidorMes1015(mes, auxServ, ano).size());
            sr.setFaltas1016(ControleFalta.getInstance().listarFaltasPorServidorMes1016(mes, auxServ, ano).size());
            sr.setFaltas1017(ControleFalta.getInstance().listarFaltasPorServidorMes1017(mes, auxServ, ano).size());
            sr.setFaltas1018(ControleFalta.getInstance().listarFaltasPorServidorMes1018(mes, auxServ, ano).size());
            sr.setFaltas1019(ControleFalta.getInstance().listarFaltasPorServidorMes1019(mes, auxServ, ano).size());
            sr.setFaltas1020(ControleFalta.getInstance().listarFaltasPorServidorMes1020(mes, auxServ, ano).size());
            sr.setMes(GerarRelatorioCoordenacaoMes.retornarMes(mes));
            if (sr.getTotalFaltas() != 0) {
                faltas.add(sr);
            }
        }
        gerarRelatorio(faltas);
    }
}
