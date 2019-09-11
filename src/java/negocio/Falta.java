package negocio;

import converter.AbstractEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

@RequestScoped
@Entity
@ManagedBean(name = "falta")

public class Falta extends AbstractEntity implements Serializable {

    @Id
    @GeneratedValue
    private Integer codigo;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataFalta;

    @ManyToOne
    private Servidor servidor;

    @ManyToOne
    private Disciplina disciplina;

    @ManyToOne
    private Turma turma;

    private Integer aulasRepor;

    @ManyToOne
    private Observacao observacao;

    private String anotacao;

    private boolean ativo;

    @ManyToMany(mappedBy = "faltasRepostas")
    private List<Reposicao> reposicoes;

    public Falta() {
    }

    public Integer getCodigo() {
        return codigo;
    }

    public Date getDataFalta() {
        return dataFalta;
    }

    public void setDataFalta(Date dataFalta) {
        this.dataFalta = dataFalta;
    }

    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Integer getAulasRepor() {
        return aulasRepor;
    }

    public void setAulasRepor(Integer aulasRepor) {
        this.aulasRepor = aulasRepor;
    }

    public Observacao getObservacao() {
        return observacao;
    }

    public String getAnotacao() {
        return anotacao;
    }

    public void setAnotacao(String anotacao) {
        this.anotacao = anotacao;
    }

    public void setObservacao(Observacao observacao) {
        this.observacao = observacao;
    }

    public List<Reposicao> getReposicoes() {
        return reposicoes;
    }

    public void setReposicoes(List<Reposicao> reposicoes) {
        this.reposicoes = reposicoes;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

}
