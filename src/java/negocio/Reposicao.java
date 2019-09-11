/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import converter.AbstractEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;


@RequestScoped
@Entity
@ManagedBean(name = "reposicao")
public class Reposicao extends AbstractEntity implements Serializable {

    @Id
    @GeneratedValue
    private Integer codigo;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataReposicao;

    @ManyToOne
    private Disciplina disciplina;

    private Integer quantidadeAulas;

    private String tipo;

    @ManyToOne
    private Servidor servidor;

    @ManyToMany
    private List<Falta> faltasRepostas;
    
    private boolean ativo;

    public Reposicao() {
    }

    public Integer getCodigo() {
        return codigo;
    }

    public Date getDataReposicao() {
        return dataReposicao;
    }

    public void setDataReposicao(Date dataReposicao) {
        this.dataReposicao = dataReposicao;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Integer getQuantidadeAulas() {
        return quantidadeAulas;
    }

    public void setQuantidadeAulas(Integer quantidadeAulas) {
        this.quantidadeAulas = quantidadeAulas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Falta> getFaltasRepostas() {
        return faltasRepostas;
    }

    public void setFaltasRepostas(List<Falta> faltasRepostas) {
        this.faltasRepostas = faltasRepostas;
    }

    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
}
