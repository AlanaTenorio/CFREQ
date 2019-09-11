
package negocio;

import converter.AbstractEntity;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Entity;
import javax.persistence.Id;

@RequestScoped
@Entity
@ManagedBean(name = "observacao")
public class Observacao extends AbstractEntity implements Serializable{
    @Id
    private Integer codigo;  
    private String observacao;
    private boolean ativo;

    public Observacao() {
    }
    
    public Integer getCodigo() {
        return codigo;
    }
    
    public void setCodigo(Integer codigo){
        this.codigo = codigo;
    }
    
    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

}
