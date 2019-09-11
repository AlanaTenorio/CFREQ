/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import interfaces.InterfaceRepositorioTurma;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import negocio.Turma;
import repositorios.RepositorioTurma;

/**
 *
 * @author Lucas
 */
@ManagedBean(name = "controleTurma")
@SessionScoped

public class ControleTurma implements Serializable {

    private InterfaceRepositorioTurma rt = null;
    private Turma selected;
    private static ControleTurma mySelf;

    public static ControleTurma getInstance() {
        if (mySelf == null) {
            mySelf = new ControleTurma();
        }
        return mySelf;
    }

    public ControleTurma() {
        this.rt = new RepositorioTurma();
    }

    public String adicionarTurma(Turma turma) {

        if (turma.getNome() == null || turma.getNome().equals("")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o nome da turma!", ""));
            return "";
        }

        if (turma.getTurno() == null || turma.getTurno().equals("")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione o turno!", ""));
            return "";
        }

        if (turma.getCurso() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione o curso!", ""));
            return "";
        }

        turma.setAtivo(true);
        this.rt.inserir(turma);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Turma cadastrada com sucesso!", ""));
        return "VisualizarTurmas.xhtml";
    }

    public String alterarTurma(Turma turma) {

        if (turma.getNome() == null || turma.getNome().equals("")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o novo nome da turma!", ""));
            return "";
        }

        if (turma.getTurno() == null || turma.getTurno().equals("")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione o novo turno!", ""));
            return "";
        }

        if (turma.getCurso() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione o novo curso!", ""));
            return "";
        }

        this.rt.alterar(turma);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Turma alterada com sucesso!", ""));
        return "VisualizarTurmas.xhtml";
    }

    public void removerTurma(Turma turma) {
        this.rt.deletar(turma);
    }
    
    public void desativar(Turma turma){
        turma.setAtivo(false);
        this.alterarTurma(turma);
    }

    public Turma recuperarTurma(Integer codigo) {
        return this.rt.recuperar(codigo);
    }

    public List<Turma> listarTurmas() {
        return this.rt.listarTurmas();
    }
    
    public List<Turma> listarTurmasAtivas() {
        return this.rt.listarTurmasAtivas();
    }

    public Turma getSelected() {
        return selected;
    }

    public void setSelected(Turma selected) {
        this.selected = selected;
    }

}
