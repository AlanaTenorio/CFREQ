/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import interfaces.InterfaceRepositorioObservacao;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import negocio.Observacao;
import org.hibernate.NonUniqueObjectException;
import repositorios.RepositorioObservacao;

/**
 *
 * @author Alana Tenório
 */
@ManagedBean(name = "controleObservacao")
@SessionScoped

public class ControleObservacao implements Serializable {

    private InterfaceRepositorioObservacao ro = null;
    private Observacao selected;
    private static ControleObservacao mySelf;

    public static ControleObservacao getInstance() {
        if (mySelf == null) {
            mySelf = new ControleObservacao();
        }
        return mySelf;
    }

    public ControleObservacao() {
        this.ro = new RepositorioObservacao();
    }

    public String adicionarObservacao(Observacao observacao) {
        try {
            if (observacao.getCodigo() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite a observaçao!", ""));
                return "";
            }

            if (observacao.getObservacao().equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite a observaçao!", ""));
                return "";
            }

            observacao.setAtivo(true);
            this.ro.inserir(observacao);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Observação cadastrada com sucesso!", ""));
            return "VisualizarObservacoes.xhtml";
        } catch (NonUniqueObjectException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Observação já cadastrada!", ""));
            return "";
        }

    }

    public String alterarObservacao(Observacao selected) {

        if (selected.getObservacao().equals("") || selected.getObservacao() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite a nova observaçao!", ""));
            return "";
        }

        this.ro.alterar(selected);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Observação alterada com sucesso!", ""));
        return "VisualizarObservacoes.xhtml";
    }

    public Observacao recuperarObservacao(String codigo) {
        return this.ro.recuperar(codigo);
    }

    public void excluirObservacao(Observacao observacao) {
        this.ro.deletar(observacao);
    }

    public void desativar(Observacao observacao) {
        observacao.setAtivo(false);
        this.ro.alterar(observacao);
    }

    public List<Observacao> listarObservacoes() {
        return this.ro.listarObservacoes();
    }

    public List<Observacao> listarObservacoesAtivas() {
        return this.ro.listarObservacoesAtivas();
    }

    public Observacao getSelected() {
        return selected;
    }

    public void setSelected(Observacao selected) {
        this.selected = selected;
    }

}
