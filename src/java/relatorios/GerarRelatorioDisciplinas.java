/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorios;

import auxiliar.RelatorioDisciplinas;
import controladores.ControleDisciplina;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import negocio.Coordenacao;
import negocio.Disciplina;
import negocio.Servidor;
import negocio.Turma;
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
@ManagedBean(name = "gerarRelatorioDisciplinas")
public class GerarRelatorioDisciplinas {

    public static void gerarRelatorio(List<RelatorioDisciplinas> disciplinas) throws JRException, ParseException, IOException {

        JasperReport report = JasperCompileManager.compileReport("C:\\Users\\AlanaMaria\\Desktop\\CFREQ\\src\\java\\relatorios\\relatorioDisciplinas.jrxml");
        JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(disciplinas));

        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
                .getCurrentInstance().getExternalContext().getResponse();

        httpServletResponse.addHeader("Content-disposition",
                "attachment; filename=relatorioDisciplinas.pdf");

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
                "Relatorio_Disciplinas.pdf");

        FacesContext.getCurrentInstance().responseComplete();

    }

    public void preencherRelatorio() throws JRException, ParseException, IOException {
        List<Disciplina> listaDisciplinas = ControleDisciplina.getInstance().listarDisciplinasAtivas();
        List<RelatorioDisciplinas> listaRelatorio = new ArrayList<RelatorioDisciplinas>();

        for (Disciplina disciplina : listaDisciplinas) {
            RelatorioDisciplinas disciplinaRelatorio = new RelatorioDisciplinas();
            disciplinaRelatorio.setNome(disciplina.getNome());
            disciplinaRelatorio.setCh(disciplina.getCh());
            disciplinaRelatorio.setChPendente(disciplina.getCh() - disciplina.getChCalculada());
            disciplinaRelatorio.setTurno(disciplina.getTurno());
            disciplinaRelatorio.setCoordenacao(recuperarCoordenacoes(disciplina));
            disciplinaRelatorio.setTurma(recuperarTurmas(disciplina));
            disciplinaRelatorio.setServidores(recuperarServidores(disciplina));

            listaRelatorio.add(disciplinaRelatorio);
        }
        gerarRelatorio(listaRelatorio);
    }

    public String recuperarServidores(Disciplina d) {
        List<Servidor> servidores = d.getProfessores();
        String servidoresStr = "";
        for (Servidor servidor : servidores) {
            servidoresStr += servidor.getNome() + ", ";
        }
        servidoresStr = servidoresStr.substring(0, servidoresStr.length() - 2);
        return servidoresStr;
    }

    public String recuperarTurmas(Disciplina d) {
        String turmasStr = "";
        List<Turma> turmas = d.getTurmas();
        for (Turma turma : turmas) {
            turmasStr += turma.getNome() + ", ";
        }
        turmasStr = turmasStr.substring(0, turmasStr.length() - 2);
        return turmasStr;
    }

    public String recuperarCoordenacoes(Disciplina d) {
        String coordenacoesStr = "";
        List<Coordenacao> coordenacoes = d.getCoordenacoes();
        for (Coordenacao coordenacao : coordenacoes) {
            coordenacoesStr += coordenacao.getNome() + ", ";
        }
        coordenacoesStr = coordenacoesStr.substring(0, coordenacoesStr.length() - 2);
        return coordenacoesStr;
    }

}
