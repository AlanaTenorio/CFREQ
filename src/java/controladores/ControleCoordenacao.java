/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import interfaces.InterfaceRepositorioCoordenacao;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import negocio.Coordenacao;
import repositorios.RepositorioCoordenacao;

@ManagedBean(name = "controleCoordenacao")
@SessionScoped
public class ControleCoordenacao implements Serializable {

    private InterfaceRepositorioCoordenacao rc;
    private Coordenacao selected;
    private boolean validaEmail;

    private static ControleCoordenacao mySelf = null;

    public static ControleCoordenacao getInstance() {
        if (mySelf == null) {
            mySelf = new ControleCoordenacao();
        }
        return mySelf;
    }

    public ControleCoordenacao() {
        this.rc = new RepositorioCoordenacao();

    }

    public String adicionarCoordenacao(Coordenacao coordenacao) {

        if (coordenacao.getNome().equals("") || coordenacao.getNome() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o nome!", ""));
            return "";
        }

        if (coordenacao.getEmail().equals("") || coordenacao.getEmail() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o email!", ""));
            return "";
        } else {
            this.validaEmail = coordenacao.getEmail().matches("[^@,\\s]+" + "@{1}" + "[^\\s,@]+");
            if (this.validaEmail == false) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Insira um email válido do tipo exemplo@exemplo.com", ""));
                return "";
            }
        }
        coordenacao.setAtivo(true);
        this.rc.inserir(coordenacao);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Coordenacao cadastrada com sucesso!", ""));
        return "VisualizarCoordenacoes.xhtml";
    }

    public String alterarCoordenacao(Coordenacao selected) {

        if (selected.getNome().equals("") || selected.getNome() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o novo nome!", ""));
            return "";
        }

        if (selected.getEmail().equals("") || selected.getEmail() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o novo email!", ""));
            return "";
        } else {
            this.validaEmail = selected.getEmail().matches("[^@,\\s]+" + "@{1}" + "[^\\s,@]+");
            if (this.validaEmail == false) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Insira um email válido do tipo exemplo@exemplo.com", ""));
                return "";
            }
        }

        this.rc.alterar(selected);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Coordenacao alterada com sucesso!", ""));
        return "VisualizarCoordenacoes.xhtml";
    }

    public Coordenacao recuperarCoordenacao(String codigo) {
        return this.rc.recuperar(codigo);
    }

    public Coordenacao recuperarCoordenacaoPorNome(String nome) {
        return this.rc.recuperarPorNome(nome);
    }

    public void excluirCoordenacao(Coordenacao coordenacao) {
        this.rc.deletar(coordenacao);
    }
    
    public void desativar(Coordenacao coordenacao){
        coordenacao.setAtivo(false);
        this.rc.alterar(coordenacao);
    }

    public List<Coordenacao> listarCoordenacoes() {
        return this.rc.listarCoordenacoes();
    }

    public List<Coordenacao> listarCoordenacoesAtivas() {
        return this.rc.listarCoordenacoesAtivas();
    }

    public Coordenacao getSelected() {
        return selected;
    }

    public void setSelected(Coordenacao selected) {
        this.selected = selected;
    }

    public boolean isValidaEmail() {
        return validaEmail;
    }

    public void setValidaEmail(boolean validaEmail) {
        this.validaEmail = validaEmail;
    }
   
  
}
