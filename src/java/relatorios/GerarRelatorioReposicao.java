/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package relatorios;

import auxiliar.RelatorioAdm;
import auxiliar.RelatorioReposicao;
import controladores.ControleFalta;
import controladores.ControleReposicao;
import controladores.ControleServidor;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import negocio.Falta;
import negocio.Reposicao;
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
@ManagedBean(name = "gerarRelatorioReposicao")
public class GerarRelatorioReposicao {
     public static void gerarRelatorio(List<RelatorioReposicao> reposicao) throws JRException, ParseException, IOException {


        JasperReport report = JasperCompileManager.compileReport("C:\\Users\\AlanaMaria\\Desktop\\CFREQ\\src\\java\\relatorios\\relatorioReposicao.jrxml");
        JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(reposicao));

        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
                .getCurrentInstance().getExternalContext().getResponse();

        httpServletResponse.addHeader("Content-disposition",
                "attachment; filename=relatorioReposicao.pdf");

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
                "Relatorio_Reposicao.pdf");

        FacesContext.getCurrentInstance().responseComplete();

    }

    public void preencherRelatorio(Reposicao reposicao) throws JRException, ParseException, IOException {
        List<RelatorioReposicao> lista = new ArrayList<RelatorioReposicao>();
        RelatorioReposicao rep = new RelatorioReposicao();
        rep.setDataReposicao(reposicao.getDataReposicao());
        rep.setDisciplina(reposicao.getDisciplina());
        rep.setQuantAulas(reposicao.getQuantidadeAulas());
        rep.setServidor(reposicao.getServidor());
        
        String faltas = "";
        if(reposicao.getFaltasRepostas() == null){
            rep.setFaltas("-");
        } else {
            List<Falta> listaFaltas = reposicao.getFaltasRepostas();
            for (Falta aux : listaFaltas) {
                faltas += aux.getAulasRepor() + "                " + aux.getDataFalta() + "                " + aux.getTurma().getNome() + "                    " + aux.getObservacao().getCodigo() + "\n ";
            }
            rep.setFaltas(faltas);
        }
        lista.add(rep);
        gerarRelatorio(lista);
    }
}
