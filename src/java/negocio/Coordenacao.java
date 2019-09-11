package negocio;

import converter.AbstractEntity;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@RequestScoped
@Entity
@ManagedBean(name = "coordenacao")

public class Coordenacao extends AbstractEntity implements Serializable {

    @Id
    @GeneratedValue
    private Integer codigo;
    private String nome;
    private String email;
    private boolean ativo;

    public Coordenacao() {
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
