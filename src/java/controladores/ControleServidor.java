/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import criptografia.Criptografia;
import interfaces.InterfaceRepositorioServidor;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import negocio.Coordenacao;
import negocio.Servidor;
import repositorios.RepositorioServidor;

/**
 *
 * @author Lucas
 */
@ManagedBean(name = "controleServidor")
@SessionScoped

public class ControleServidor implements Serializable {

    private InterfaceRepositorioServidor rs;
    private Servidor selected;
    private boolean validaSiape;
    private boolean validaNome;
    private boolean validaEmail;
    private String confirmaSenha;

    private static ControleServidor mySelf = null;

    public static ControleServidor getInstance() {
        if (mySelf == null) {
            mySelf = new ControleServidor();
        }
        return mySelf;
    }

    public ControleServidor() {
        this.rs = new RepositorioServidor();
        this.confirmaSenha = null;
    }

    public String adicionarServidor(Servidor servidor) {

        if (servidor.getSiape().equals("") || servidor.getSiape() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o SIAPE!", ""));
            return "";

        } else {

            this.validaSiape = servidor.getSiape().matches("[0-9]{7}");

            if (this.validaSiape == false) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SIAPE é composto apenas por números e contém 7 digitos!", ""));
                return "";

            } else {

                Servidor consultaSiapeCadastro = null;

                List<Servidor> ls = this.rs.listarServidoresAtivos();

                for (int i = 0; i < ls.size(); i++) {
                    if (servidor.getSiape().equals(ls.get(i).getSiape())) {
                        consultaSiapeCadastro = ls.get(i);
                    }
                }

                if (consultaSiapeCadastro != null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SIAPE já usado!", ""));
                    return "";
                }
            }
        }

        if (servidor.getSenha().equals("") || servidor.getSenha() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite a senha!", ""));
            return "";
        }

        if (this.confirmaSenha.equals("") || this.confirmaSenha == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Por segurança, confirme a senha!", ""));
            return "";
        }
        if (!servidor.getSenha().equals(this.confirmaSenha)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "A senhas devem ser idênticas!", ""));
            return "";
        }

        if (servidor.getNome().equals("") || servidor.getNome() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o nome!", ""));
            return "";
        } else {
            this.validaNome = servidor.getNome().matches(".*[^0-9]+");
            if (this.validaNome == false) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não insira números no nome!", ""));
                return "";
            }
        }

        if (servidor.getEmail().equals("") || servidor.getEmail() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o email!", ""));
            return "";

        } else {
            this.validaEmail = servidor.getEmail().matches("[^@,\\s]+" + "@{1}" + "[^\\s,@]+");
            if (this.validaEmail == false) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Insira um email válido do tipo exemplo@exemplo.com", ""));
                return "";
            }
        }

        if (servidor.getPerfil().equals("") || servidor.getPerfil() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escolha o perfil!", ""));
            return "";
        }
        
        servidor.setSenha(criptografia.Criptografia.criptografar(servidor.getSenha()));

        servidor.setAtivo(true);
        this.rs.inserir(servidor);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Servidor cadastrado com sucesso!", ""));
        return "VisualizarServidores.xhtml";

    }
    
    public String adicionarServidorPrimeiroAcesso(Servidor servidor) {

        if (servidor.getSiape().equals("") || servidor.getSiape() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o SIAPE!", ""));
            return "";

        } else {

            this.validaSiape = servidor.getSiape().matches("[0-9]{7}");

            if (this.validaSiape == false) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SIAPE é composto apenas por números e contém 7 digitos!", ""));
                return "";

            } else {

                Servidor consultaSiapeCadastro = null;

                List<Servidor> ls = this.rs.listarServidoresAtivos();

                for (int i = 0; i < ls.size(); i++) {
                    if (servidor.getSiape().equals(ls.get(i).getSiape())) {
                        consultaSiapeCadastro = ls.get(i);
                    }
                }

                if (consultaSiapeCadastro != null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SIAPE já usado!", ""));
                    return "";
                }
            }
        }

        if (servidor.getSenha().equals("") || servidor.getSenha() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite a senha!", ""));
            return "";
        }

        if (this.confirmaSenha.equals("") || this.confirmaSenha == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Por segurança, confirme a senha!", ""));
            return "";
        }
        if (!servidor.getSenha().equals(this.confirmaSenha)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "A senhas devem ser idênticas!", ""));
            return "";
        }

        if (servidor.getNome().equals("") || servidor.getNome() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o nome!", ""));
            return "";
        } else {
            this.validaNome = servidor.getNome().matches(".*[^0-9]");
            if (this.validaNome == false) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não insira números no nome!", ""));
                return "";
            }
        }

        if (servidor.getEmail().equals("") || servidor.getEmail() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o email!", ""));
            return "";

        } else {
            this.validaEmail = servidor.getEmail().matches("[^@,\\s]+" + "@{1}" + "[^\\s,@]+");
            if (this.validaEmail == false) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Insira um email válido do tipo exemplo@exemplo.com", ""));
                return "";
            }
        }

        if (servidor.getPerfil().equals("") || servidor.getPerfil() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escolha o perfil!", ""));
            return "";
        }
        
        servidor.setSenha(criptografia.Criptografia.criptografar(servidor.getSenha()));

        servidor.setAtivo(true);
        this.rs.inserir(servidor);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Servidor cadastrado com sucesso!", ""));
        return "Login.xhtml";

    }

    public String alterarServidor(Servidor servidor) {

        if (servidor.getSiape().equals("") || servidor.getSiape() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o SIAPE!", ""));
            return "";

        } else {
            this.validaSiape = servidor.getSiape().matches("[0-9]{7}");

            if (this.validaSiape == false) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SIAPE é composto apenas por números e contém 7 digitos!", ""));
                return "";
            } else {
                Servidor consultaSiapeAlterar = null;
                List<Servidor> ls = this.rs.listarServidoresAtivos();

                for (int i = 0; i < ls.size(); i++) {
                    if (servidor.getSiape().equals(ls.get(i).getSiape()) && servidor.getCodigo() != ls.get(i).getCodigo()) {
                        consultaSiapeAlterar = ls.get(i);
                    }
                }

                if (consultaSiapeAlterar != null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SIAPE já cadastrado!", ""));
                    return "";
                }
            }
        }

        if (servidor.getSenha().equals("") || servidor.getSenha() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite a senha!", ""));
            return "";
        }

        if (this.confirmaSenha.equals("") || this.confirmaSenha == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Por segurança, confirme a senha!", ""));
            return "";
        }

        if (!servidor.getSenha().equals(this.confirmaSenha)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "A senhas devem ser idênticas!", ""));
            return "";
        }

        if (servidor.getNome().equals("") || servidor.getNome() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o nome!", ""));
            return "";

        } else {
            this.validaNome = servidor.getNome().matches(".*[^0-9]+");
            if (this.validaNome == false) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não insira números no nome!", ""));
                return "";
            }
        }

        if (servidor.getEmail().equals("") || servidor.getEmail() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o email!", ""));
            return "";
        } else {
            this.validaEmail = servidor.getEmail().matches("[^@,\\s]+" + "@{1}" + "[^\\s,@]+");
            if (this.validaEmail == false) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Insira um email válido do tipo exemplo@exemplo.com", ""));
                return "";
            }
        }

        if (servidor.getPerfil().equals("") || servidor.getPerfil() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escolha o perfil!", ""));
            return "";
        }
        
        servidor.setSenha(Criptografia.criptografar(servidor.getSenha()));
        
        this.rs.alterar(servidor);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Servidor alterado com sucesso!", ""));
        return "VisualizarServidores.xhtml";
    }

    
    public String alterarServidorCoordenador(Servidor servidor) {

        if (servidor.getSiape().equals("") || servidor.getSiape() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o SIAPE!", ""));
            return "";

        } else {
            this.validaSiape = servidor.getSiape().matches("[0-9]{7}");

            if (this.validaSiape == false) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SIAPE é composto apenas por números e contém 7 digitos!", ""));
                return "";
            } else {
                Servidor consultaSiapeAlterar = null;
                List<Servidor> ls = this.rs.listarServidoresAtivos();

                for (int i = 0; i < ls.size(); i++) {
                    if (servidor.getSiape().equals(ls.get(i).getSiape()) && servidor.getCodigo() != ls.get(i).getCodigo()) {
                        consultaSiapeAlterar = ls.get(i);
                    }
                }

                if (consultaSiapeAlterar != null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SIAPE já cadastrado!", ""));
                    return "";
                }
            }
        }

        if (servidor.getSenha().equals("") || servidor.getSenha() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite a senha!", ""));
            return "";
        }

        if (this.confirmaSenha.equals("") || this.confirmaSenha == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Por segurança, confirme a senha!", ""));
            return "";
        }

        if (!servidor.getSenha().equals(this.confirmaSenha)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "A senhas devem ser idênticas!", ""));
            return "";
        }

        if (servidor.getNome().equals("") || servidor.getNome() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o nome!", ""));
            return "";

        } else {
            this.validaNome = servidor.getNome().matches(".*[^0-9]+");
            if (this.validaNome == false) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não insira números no nome!", ""));
                return "";
            }
        }

        if (servidor.getEmail().equals("") || servidor.getEmail() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o email!", ""));
            return "";
        } else {
            this.validaEmail = servidor.getEmail().matches("[^@,\\s]+" + "@{1}" + "[^\\s,@]+");
            if (this.validaEmail == false) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Insira um email válido do tipo exemplo@exemplo.com", ""));
                return "";
            }
        }

        if (servidor.getPerfil().equals("") || servidor.getPerfil() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escolha o perfil!", ""));
            return "";
        }

        servidor.setSenha(Criptografia.criptografar(servidor.getSenha()));

        this.rs.alterar(servidor);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Servidor alterado com sucesso!", ""));

        return "PerfilServidorCoordenador.xhtml";
    }

    public String alterarServidorProfessor(Servidor servidor) {

        if (servidor.getSiape().equals("") || servidor.getSiape() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o SIAPE!", ""));
            return "";

        } else {
            this.validaSiape = servidor.getSiape().matches("[0-9]{7}");

            if (this.validaSiape == false) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SIAPE é composto apenas por números e contém 7 digitos!", ""));
                return "";
            } else {
                Servidor consultaSiapeAlterar = null;
                List<Servidor> ls = this.rs.listarServidoresAtivos();

                for (int i = 0; i < ls.size(); i++) {
                    if (servidor.getSiape().equals(ls.get(i).getSiape()) && servidor.getCodigo() != ls.get(i).getCodigo()) {
                        consultaSiapeAlterar = ls.get(i);
                    }
                }

                if (consultaSiapeAlterar != null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SIAPE já cadastrado!", ""));
                    return "";
                }
            }
        }

        if (servidor.getSenha().equals("") || servidor.getSenha() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite a senha!", ""));
            return "";
        }

        if (this.confirmaSenha.equals("") || this.confirmaSenha == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Por segurança, confirme a senha!", ""));
            return "";
        }

        if (!servidor.getSenha().equals(this.confirmaSenha)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "A senhas devem ser idênticas!", ""));
            return "";
        }

        if (servidor.getNome().equals("") || servidor.getNome() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o nome!", ""));
            return "";

        } else {
            this.validaNome = servidor.getNome().matches(".*[^0-9]+");
            if (this.validaNome == false) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não insira números no nome!", ""));
                return "";
            }
        }

        if (servidor.getEmail().equals("") || servidor.getEmail() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite o email!", ""));
            return "";
        } else {
            this.validaEmail = servidor.getEmail().matches("[^@,\\s]+" + "@{1}" + "[^\\s,@]+");
            if (this.validaEmail == false) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Insira um email válido do tipo exemplo@exemplo.com", ""));
                return "";
            }
        }

        if (servidor.getPerfil().equals("") || servidor.getPerfil() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Escolha o perfil!", ""));
            return "";
        }

        servidor.setSenha(Criptografia.criptografar(servidor.getSenha()));

        this.rs.alterar(servidor);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Servidor alterado com sucesso!", ""));

        return "PerfilProfessor.xhtml";

    }
    public Servidor recuperarServidor(String SIAPE) {
        return this.rs.recuperar(SIAPE);
    }

    public Servidor recuperarServidorPorCodigo(Integer codigo) {
        return this.rs.recuperarPorCodigo(codigo);
    }

    public void excluirServidor(Servidor servidor) {
        this.rs.deletar(servidor);
    }
    
    public void desativar(Servidor servidor){
        servidor.setAtivo(false);
        this.rs.alterar(servidor);
    }

    public List<Servidor> listarServidores() {
        return this.rs.listarServidores();
    }
    
    public List<Servidor> listarServidoresAtivos() {
        return this.rs.listarServidoresAtivos();
    }
    
    public List<Servidor> listarServidoresPorCoordenacao(Coordenacao coordenacao) {
        return this.rs.listarServidoresPorCoordenacao(coordenacao);
    }

    public Servidor getSelected() {
        return selected;
    }

    public void setSelected(Servidor selected) {
        this.selected = selected;
    }

    public boolean isValidaSiape() {
        return validaSiape;
    }

    public void setValidaSiape(boolean validaSiape) {
        this.validaSiape = validaSiape;
    }

    public boolean isValidaNome() {
        return validaNome;
    }

    public void setValidaNome(boolean validaNome) {
        this.validaNome = validaNome;
    }

    public boolean isValidaEmail() {
        return validaEmail;
    }

    public void setValidaEmail(boolean validaEmail) {
        this.validaEmail = validaEmail;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

}
