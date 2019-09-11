/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import interfaces.InterfaceRepositorioCurso;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import negocio.Curso;
import repositorios.RepositorioCurso;

/**
 *
 * @author Lucas
 */
@ManagedBean(name = "controleCurso")
@SessionScoped
public class ControleCurso implements Serializable{

    private InterfaceRepositorioCurso rc = null;
    private Curso selected;
    private static ControleCurso mySelf;

    public static ControleCurso getInstance() {
        if (mySelf == null) {
            mySelf = new ControleCurso();
        }
        return mySelf;
    }

    public ControleCurso() {
        this.rc = new RepositorioCurso();
    }

    public String AdicionarCurso(Curso curso) {

        if (curso.getNome().equals("") || curso.getNome() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o nome do curso!", ""));
            return "";
        }

        if (curso.getModalidade() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione a modalidade!", ""));
            return "";
        }

        curso.setAtivo(true);
        this.rc.inserir(curso);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Curso cadastrado com sucesso!", ""));
        return "VisualizarCursos.xhtml";

    }

    public String alterarCurso(Curso curso) {

        if (curso.getNome().equals("") || curso.getNome() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o nome do curso!", ""));
            return "";
        }

        if (curso.getModalidade() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione a modalidade!", ""));
            return "";
        }

        this.rc.alterar(curso);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Curso alterado com sucesso!", ""));
        return "VisualizarCursos.xhtml";
    }

    public Curso recuperarCurso(Integer codigo) {
        return this.rc.recuperar(codigo);
    }

    public void deletarCurso(Curso curso) {
        this.rc.deletar(curso);
    }
    
    public void desativar(Curso curso){
        curso.setAtivo(false);
        this.rc.alterar(curso);
    }

    public List<Curso> listarCursos() {
        return this.rc.listarCursos();
    }
    
    public List<Curso> listarCursosAtivos() {
        return this.rc.listarCursosAtivos();
    }

    public Curso getSelected() {
        return selected;
    }

    public void setSelected(Curso selected) {
        this.selected = selected;
    }
    
}
