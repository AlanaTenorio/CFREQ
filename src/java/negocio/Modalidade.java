/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@ManagedBean(name = "modalidade")

public class Modalidade extends AbstractEntity implements Serializable {

    @Id
    @GeneratedValue
    private Integer codigo;
    
    private String nome;

    private boolean ativo;
    
    public Modalidade(String nome) {
        this.nome = nome;
    }

    public Modalidade() {
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

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
