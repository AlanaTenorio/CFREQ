/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import auxiliar.ServidorReposicao;
import interfaces.InterfaceRepositorioReposicao;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import negocio.Coordenacao;
import negocio.Disciplina;
import negocio.Falta;
import negocio.Reposicao;
import negocio.Servidor;
import org.primefaces.model.DualListModel;
import repositorios.RepositorioReposicao;
import sendmail.ControleEmail;
import sendmail.DadosEmail;

/**
 *
 * @author Alana Tenório
 */
@ManagedBean(name = "controleReposicao")
@SessionScoped
public class ControleReposicao implements Serializable {

    private InterfaceRepositorioReposicao rr;
    private Reposicao selected;

    // Atributos para Inicializar PickList de Faltas
    ControleFalta cf = new ControleFalta();
    List<Falta> faltasSource;
    List<Falta> faltasTarget;
    private DualListModel<Falta> faltas;

    private List<Disciplina> disciplinasFiltradas;
    private List<Falta> faltasFiltradas;
    private String tipoReposicao;

    private static ControleReposicao mySelf;

    public static ControleReposicao getInstance() {
        if (mySelf == null) {
            mySelf = new ControleReposicao();
        }
        return mySelf;
    }

    public ControleReposicao() {
        this.rr = new RepositorioReposicao();
    }

    //Método para inicializar PickList
    public void inicializarPickList(Reposicao r) {
        //PickList Faltas
        faltasTarget = r.getFaltasRepostas();
        faltasSource = cf.listarFaltasAtivas();
        faltas = new DualListModel<Falta>(faltasSource, faltasTarget);
    }

    public String adicionarReposicao(Reposicao reposicao) throws MessagingException {

        if (reposicao.getFaltasRepostas() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione as faltas referentes a essa reposição!", ""));
            return "";
        }

        if (reposicao.getDataReposicao() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite a data da reposição!", ""));
            return "";
        }

        if (reposicao.getDisciplina() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione a disciplina!", ""));
            return "";
        }

        if (reposicao.getQuantidadeAulas() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite a quantidade de aulas!", ""));
            return "";
        }

        reposicao.setTipo(this.tipoReposicao);

        reposicao.setAtivo(true);
        this.rr.inserir(reposicao);
        //enviarEmail(reposicao);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Reposição cadastrada com sucesso!", ""));
        return "VisualizarReposicoes.xhtml";
    }

    public String alterarReposicao(Reposicao reposicao) {

        if (reposicao.getFaltasRepostas() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione as faltas referentes a essa reposição!", ""));
            return "";
        }

        if (reposicao.getDataReposicao() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite a data da reposição!", ""));
            return "";
        }

        if (reposicao.getDisciplina() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione a disciplina!", ""));
            return "";
        }

        if (reposicao.getQuantidadeAulas() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite a quantidade de aulas!", ""));
            return "";
        }

        this.rr.alterar(reposicao);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Reposição alterada com sucesso!", ""));
        return "VisualizarReposicoes.xhtml";
    }

    public Reposicao recuperarReposicao(String codigo) {
        return this.rr.recuperar(codigo);
    }

    public void excluirReposicao(Reposicao reposicao) {
        this.rr.deletar(reposicao);
    }

    public void desativar(Reposicao reposicao) {
        reposicao.setAtivo(false);
        reposicao.getFaltasRepostas().removeAll(reposicao.getFaltasRepostas());
        this.rr.alterar(reposicao);
    }

    public List<Reposicao> listarReposicoes() {
        return this.rr.listarReposicoes();
    }

    public List<Reposicao> listarReposicoesPorServidor(Servidor servidor) {
        return this.rr.listarReposicoesPorServidor(servidor);
    }

    public List<Reposicao> listarReposicoesAtivas() {
        return this.rr.listarReposicoesAtivas();
    }

    //Metódo para Relatório ADM
    public List<Reposicao> listarReposicoesServidorMes(int mes, Servidor servidor, int ano) {
        return this.rr.listarReposicoesServidorMes(mes, servidor, ano);
    }

    //Metódo para Relatório ADM
    public List<Reposicao> listarAntecipacoesServidorMes(int mes, Servidor servidor, int ano) {
        return this.rr.listarReposicoesServidorMes(mes, servidor, ano);
    }

    //Método para Relatório de faltas por Servidor
    public List<Reposicao> listarReposicoesServidorData(Servidor servidor, Date inicio, Date termino) {
        return this.rr.listarReposicoesServidorData(servidor, inicio, termino);
    }

    //Método para Relatório de faltas por Servidor
    public List<Reposicao> listarAntecipacoesServidorData(Servidor servidor, Date inicio, Date termino) {
        return this.rr.listarReposicoesServidorData(servidor, inicio, termino);
    }

    public List<Reposicao> listarReposicoesPorDisciplina(Disciplina disciplina) {
        return this.rr.listarReposicoesPorDisciplina(disciplina);
    }

    public List<Reposicao> listarAntecipacoesPorDisciplina(Disciplina disciplina) {
        return this.rr.listarAntecipacoesPorDisciplina(disciplina);
    }

    public Integer somarAulas(List<Reposicao> listaReposicoes) {
        return this.rr.somarAulas(listaReposicoes);
    }

    //Método para Relatório Coordenação
    public List<Reposicao> listarReposicoesPorFalta(Falta falta) {
        return this.rr.listarReposicoesPorFalta(falta);
    }

    public Reposicao getSelected() {
        return selected;
    }

    public void setSelected(Reposicao selected) {
        this.selected = selected;
    }

    public DualListModel<Falta> getFaltas() {
        return faltas;
    }

    public void setFaltas(DualListModel<Falta> faltas) {
        this.faltas = faltas;
    }

    public List<Falta> getFaltasFiltradas() {
        return faltasFiltradas;
    }

    public void setFaltasFiltradas(List<Falta> faltasFiltradas) {
        this.faltasFiltradas = faltasFiltradas;
    }

    public List<Disciplina> getDisciplinasFiltradas() {
        return disciplinasFiltradas;
    }

    public void setDisciplinasFiltradas(List<Disciplina> disciplinasFiltradas) {
        this.disciplinasFiltradas = disciplinasFiltradas;
    }

    public String getTipoReposicao() {
        return tipoReposicao;
    }

    public void setTipoReposicao(String tipoReposicao) {
        this.tipoReposicao = tipoReposicao;
    }

    public String formatarData(Date d) {
        String dataFormatada = new SimpleDateFormat("dd/MM/yyyy").format(d);
        return dataFormatada;
    }

    public List<ServidorReposicao> listarServidorReposicoesPorCoordenacao(List<List<Reposicao>> listaReposicoes) {
        return this.rr.listarServidorReposicoesPorCoordenacao(listaReposicoes);
    }

    public List<ServidorReposicao> listarReposicoesPorCoordenacao(Coordenacao coordenacao) {
        return this.rr.listarReposicoesPorCoordenacao(coordenacao);
    }

    public void filtros(Servidor servidor) {
        this.disciplinasFiltradas = ControleFalta.getInstance().filtrarDisciplinasPorServidor(servidor);
        this.faltasFiltradas = ControleReposicao.getInstance().filtrarFaltasPorServidor(servidor);
        this.tipoReposicao = ControleReposicao.getInstance().filtrarTipoPelasFaltasServidor(servidor);
    }

    public List<Falta> filtrarFaltasPorServidor(Servidor servidor) {

        if (servidor == null) {
            return this.faltasFiltradas = null;
        }
        List<Falta> faltasCadastradas = ControleFalta.getInstance().listarFaltasAtivas();
        List<Falta> faltasFiltradass = new ArrayList<Falta>();

        for (int i = 0; i < faltasCadastradas.size(); i++) {
            if (faltasCadastradas.get(i).getServidor().getCodigo() == servidor.getCodigo()) {
                faltasFiltradass.add(faltasCadastradas.get(i));
            }
        }

        return this.faltasFiltradas = faltasFiltradass;

    }

    public String filtrarTipoPelasFaltasServidor(Servidor servidor) {

        List<Falta> faltasCadastradas = ControleFalta.getInstance().listarFaltasAtivas();
        List<Falta> faltaServidor = new ArrayList<Falta>();

        for (int i = 0; i < faltasCadastradas.size(); i++) {
            if (faltasCadastradas.get(i).getServidor().getCodigo() == servidor.getCodigo()) {
                faltaServidor.add(faltasCadastradas.get(i));
            }
        }

        if (faltaServidor.isEmpty() == true) {
            return this.tipoReposicao = "Antecipacao";
        } else {
            return this.tipoReposicao = "Reposicao";
        }

    }

    public void enviarEmail(Reposicao reposicao) throws MessagingException {

        String dataFormatada = new SimpleDateFormat("dd/MM/yyyy").format(reposicao.getDataReposicao());

        String assunto = "Inclusão de nova reposição";
        String mensagem = "\nReposição adicionada para servidor: " + reposicao.getFaltasRepostas().get(0).getServidor()
                + "\nData: " + dataFormatada
                + "\nDisciplina: " + reposicao.getDisciplina().getTurno()
                + "\nQuantidade de aulas: " + reposicao.getQuantidadeAulas();

        List<String> emails = new ArrayList<String>();
        emails.add(reposicao.getFaltasRepostas().get(0).getServidor().getEmail());
        emails.add(reposicao.getFaltasRepostas().get(0).getServidor().getCoordenacao().getEmail());

        ControleLogin cl = (ControleLogin) ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("controleLogin");
        DadosEmail dadosEmail = new DadosEmail();
        dadosEmail.setDestinatarios(emails);
        dadosEmail.setAssunto(assunto);
        dadosEmail.setMensagem(mensagem);
        dadosEmail.setLogin(cl.getServidor().getEmail());
        dadosEmail.setSenha(cl.getServidor().getSenha());

        ControleEmail.getInstance().PDados(dadosEmail);
    }

}
