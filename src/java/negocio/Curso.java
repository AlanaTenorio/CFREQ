package negocio;

import converter.AbstractEntity;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@RequestScoped
@Entity
@ManagedBean(name = "curso")

public class Curso extends AbstractEntity implements Serializable {

    @Id
    @GeneratedValue
    private Integer codigo;

    private String nome;
    
    @ManyToOne
    private Modalidade modalidade;
    
    private boolean ativo;

    public Curso() {
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

    public Modalidade getModalidade() {
        return modalidade;
    }

    public void setModalidade(Modalidade modalidade) {
        this.modalidade = modalidade;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

}
