/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import interfaces.InterfaceRepositorioModalidade;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import negocio.Modalidade;
import repositorios.RepositorioModalidade;

/**
 *
 * @author Lucas
 */
@ManagedBean(name = "controleModalidade")
@SessionScoped

public class ControleModalidade implements Serializable {

    private InterfaceRepositorioModalidade rm = null;
    private Modalidade selected;
    private static ControleModalidade mySelf;

    public ControleModalidade() {
        this.rm = new RepositorioModalidade();
    }

    public static ControleModalidade getInstance() {
        if (mySelf == null) {
            mySelf = new ControleModalidade();
        }
        return mySelf;
    }

    public String adicionarModalidade(Modalidade modalidade) {

        if (modalidade.getNome().equals("") || modalidade.getNome() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o nome!", ""));
            return "";
        }

        modalidade.setAtivo(true);
        this.rm.inserir(modalidade);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modalidade cadastrada com sucesso!", ""));
        return "VisualizarModalidades.xhtml";
    }

    public String alterarModalidade(Modalidade modalidade) {

        if (modalidade.getNome().equals("") || modalidade.getNome() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o nome!", ""));
            return "";
        }

        this.rm.alterar(modalidade);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modalidade alterada com sucesso!", ""));
        return "VisualizarModalidades.xhtml";
    }

    public void deletarModalidade(Modalidade modalidade) {
        this.rm.deletar(modalidade);
    }

    public void desativar(Modalidade modalidade) {
        modalidade.setAtivo(false);
        this.rm.alterar(modalidade);
    }

    public Modalidade recuperar(Integer codigo) {
        return this.rm.recuperar(codigo);
    }

    public List<Modalidade> listarModalidades() {
        return this.rm.listarModalidades();
    }

    public List<Modalidade> listarModalidadesAtivas() {
        return this.rm.listarModalidadesAtivas();
    }

    public Modalidade getSelected() {
        return selected;
    }

    public void setSelected(Modalidade selected) {
        this.selected = selected;
    }

}
