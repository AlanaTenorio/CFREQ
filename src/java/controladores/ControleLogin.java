/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import negocio.Servidor;
import org.primefaces.context.RequestContext;
import repositorios.RepositorioServidor;

/**
 *
 * @author Lucas
 */
@ManagedBean(name = "controleLogin")
@SessionScoped
public class ControleLogin implements Serializable {

    private interfaces.InterfaceRepositorioServidor rs;
    private boolean loggedIn = false;
    private Servidor servidor;
    private boolean administrador = false;
    private boolean professor = false;
    private boolean coordenador = false;
    private Integer codigo;

    private static ControleLogin mySelf = null;

    public static ControleLogin getInstance() {
        if (mySelf == null) {
            mySelf = new ControleLogin();
        }
        return mySelf;
    }

    public ControleLogin() {
        rs = new RepositorioServidor();
    }

    public String login(Servidor servidor) {
        RequestContext context = RequestContext.getCurrentInstance();

        if (servidor.getSiape().equals("") || servidor.getSiape() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o SIAPE!", ""));
        }
        if (servidor.getSenha().equals("") || servidor.getSenha() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite a senha!", ""));
        }
        if (servidor.getSiape().equals("") || servidor.getSiape() == null || servidor.getSenha().equals("") || servidor.getSenha() == null) {
            return "";
        }
        
        servidor.setSenha(criptografia.Criptografia.criptografar(servidor.getSenha()));
        
        Servidor s = null;
        List<Servidor> ls = this.rs.listarServidoresAtivos();

        for (int i = 0; i < ls.size(); i++) {
            if (servidor.getSiape().equals(ls.get(i).getSiape())) {
                s = ls.get(i);
            }
        }

        if (s == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SIAPE não cadastrado ou desativado do sistema.", ""));
            return "";
        }
        if (servidor.getSiape().equals(s.getSiape())) {
            
            if (servidor.getSenha().equals(s.getSenha())) {
                
                loggedIn = true;
                this.servidor = s;
                this.codigo = s.getCodigo();
                
                if (s.getPerfil().equals("Administrador")) {
                    this.administrador = true;
                }
                if (s.getPerfil().equals("Coordenador")) {
                    this.coordenador = true;
                }
                if (s.getPerfil().equals("Professor")) {
                    this.professor = true;
                }
                
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha inválida!", ""));
                return "";
            }
        }

        context.addCallbackParam("loggedIn", loggedIn);
        return null;
    }

    public boolean getLoggedIn() {
        return loggedIn;
    }

    public void sair() {
        this.loggedIn = false;
        this.administrador = false;
        this.coordenador = false;
        this.professor = false;
    }

    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public boolean isCoordenador() {
        return coordenador;
    }

    public boolean isProfessor() {
        return professor;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
}
