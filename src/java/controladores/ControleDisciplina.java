/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import interfaces.InterfaceRepositorioDisciplina;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import negocio.Coordenacao;
import negocio.Disciplina;
import negocio.Servidor;
import negocio.Turma;
import org.primefaces.model.DualListModel;
import repositorios.RepositorioDisciplina;

/**
 *
 * @author Lucas
 */
@ManagedBean(name = "controleDisciplina")
@SessionScoped
public class ControleDisciplina implements Serializable {

    private InterfaceRepositorioDisciplina rd;
    private Disciplina selected;
    private List<Turma> turmasfiltradas;
    
    // Atributos para Inicializar PickList de Servidores
    ControleServidor cs = new ControleServidor();
    List<Servidor> servidoresSource;
    List<Servidor> servidoresTarget;
    private DualListModel<Servidor> servidores;
    
    //Atributos para Inicializar PickList de Coordenações
    ControleCoordenacao cc = new ControleCoordenacao();
    List<Coordenacao> coordenacoesSource;
    List<Coordenacao> coordenacoesTarget;
    private DualListModel<Coordenacao> coordenacoes;
    
    //Atributos para Inicializar PickList de Turmas
    ControleTurma ct = new ControleTurma();
    List<Turma> turmasSource;
    List<Turma> turmasTarget;
    private DualListModel<Turma> turmas;
    
    private static ControleDisciplina mySelf = null;

    public static ControleDisciplina getInstance() {
        if (mySelf == null) {
            mySelf = new ControleDisciplina();
        }
        return mySelf;
    }

    public ControleDisciplina() {
        this.rd = new RepositorioDisciplina();

    }

    //Método para inicializar PickList
    public void inicializarPickList(Disciplina d){
        //PickList Professores
        servidoresTarget = d.getProfessores();
        servidoresSource = cs.listarServidoresAtivos();
        servidores = new DualListModel<Servidor>(servidoresSource, servidoresTarget);
        
        //PickList Coordenações
        coordenacoesTarget = d.getCoordenacoes();
        coordenacoesSource = cc.listarCoordenacoesAtivas();
        coordenacoes = new DualListModel<Coordenacao>(coordenacoesSource, coordenacoesTarget);
        
        
        //PickList Turmas
        turmasTarget = d.getTurmas();
        turmasSource = ct.listarTurmasAtivas();
        turmas = new DualListModel<Turma>(turmasSource, turmasTarget);
    }
    
    public String adicionarDisciplina(Disciplina disciplina) {

        if (disciplina.getNome().equals("") || disciplina.getNome() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o nome!", ""));
            return "";
        }

        if (disciplina.getTurno().equals("") || disciplina.getTurno() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione o turno!", ""));
            return "";
        }

        if (disciplina.getCh() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite a carga horária!", ""));
            return "";
        }

        if (disciplina.getCh() <= 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Carga horária inválida!", ""));
            return "";
        }

        if (disciplina.getProfessores() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione os professores!", ""));
            return "";
        }

        if (disciplina.getCoordenacoes() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione as coordenações!", ""));
            return "";
        }

        disciplina.setAtivo(true);
        this.rd.inserir(disciplina);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Disciplina cadastrada com sucesso!", ""));
        return "VisualizarDisciplinas.xhtml";
    }

    public String alterarDisciplina(Disciplina disciplina) {

        if (disciplina.getNome().equals("") || disciplina.getNome() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o novo nome!", ""));
            return "";
        }

        if (disciplina.getTurno().equals("") || disciplina.getTurno() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione o novo turno!", ""));
            return "";
        }

        if (disciplina.getCh() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite a nova carga horária!", ""));
            return "";
        }

        if (disciplina.getCh() <= 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Carga horária inválida!", ""));
            return "";
        }

        if (disciplina.getProfessores() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione os professores!", ""));
            return "";
        }

        if (disciplina.getCoordenacoes() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione as coordenações!", ""));
            return "";
        }
        
        disciplina.setProfessores(getServidores().getTarget());
        disciplina.setCoordenacoes(getCoordenacoes().getTarget());
        this.rd.alterar(selected);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Disciplina alterada com sucesso!", ""));
        return "VisualizarDisciplinas.xhtml";
    }

    public Disciplina recuperarDisciplina(String codigo) {
        return this.rd.recuperar(codigo);
    }

    public Disciplina recuperarDisciplinaPorNome(String nome) {
        return this.rd.recuperarPorNome(nome);
    }

    public void excluirDisciplina(Disciplina disciplina) {
        this.rd.deletar(disciplina);
    }

    public void desativar(Disciplina disciplina){
        disciplina.setAtivo(false);
        this.rd.alterar(disciplina);
    }
    
    public List<Disciplina> listarDisciplinas() {
        this.rd.calcularCH();
        return this.rd.listarDisciplinas();
    }
 
    public List<Disciplina> listarDisciplinasAtivas() {
        this.rd.calcularCH();
        return this.rd.listarDisciplinasAtivas();
    }

    public List<Disciplina> recuperarPorNomeCoordenacao(String nomeCoordenacao) {
        return this.rd.recuperarPorNomeCoordenacao(nomeCoordenacao);
    }
    
    public List<Disciplina>listarDisciplinasPorServidor(Servidor servidor){
        return this.rd.listarDisciplinasServidor(servidor);
    }

    public void calcularCH(){
        this.rd.calcularCH();
    }
    
    public Disciplina getSelected() {
        return selected;
    }

    public void setSelected(Disciplina selected) {
        this.selected = selected;
    }

    public DualListModel<Servidor> getServidores() {
        return servidores;
    }

    public void setServidores(DualListModel<Servidor> servidores) {
        this.servidores = servidores;
    }

    public DualListModel<Coordenacao> getCoordenacoes() {
        return coordenacoes;
    }

    public void setCoordenacoes(DualListModel<Coordenacao> coordenacoes) {
        this.coordenacoes = coordenacoes;
    }

    public DualListModel<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(DualListModel<Turma> turmas) {
        this.turmas = turmas;
    }
    
    public List<Turma> getTurmasfiltradas() {
        return turmasfiltradas;
    }

    public void setTurmasfiltradas(List<Turma> turmasfiltradas) {
        this.turmasfiltradas = turmasfiltradas;
    }
    
    public List<Turma> filtrarTurmasporTurno(String turno) {
        
        if(turno.equals("")){
            return this.turmasfiltradas = null;
        }

        List<Turma> turmasCadastradas = ControleTurma.getInstance().listarTurmas();
        List<Turma> turmasfiltradass = new ArrayList<Turma>();

        for (int i = 0; i < turmasCadastradas.size(); i++) {
            if (turmasCadastradas.get(i).getTurno().equals(turno)) {
                turmasfiltradass.add(turmasCadastradas.get(i));
            }
        }
        return this.turmasfiltradas = turmasfiltradass;
    }
}
