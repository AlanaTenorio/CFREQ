package negocio;

import converter.AbstractEntity;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@RequestScoped
@Entity
@ManagedBean(name = "disciplina")

public class Disciplina extends AbstractEntity implements Serializable {

    @GeneratedValue
    @Id
    private Integer codigo;

    private String nome;
    private String turno;
    private Integer ch;
    private Integer quantidadeFaltas;
    
    
    @ManyToMany
    private List<Turma>turmas;
    
    @ManyToMany
    private List<Servidor> professores;

    @ManyToMany
    
    private List<Coordenacao> coordenacoes;

    private Integer chCalculada;
    private boolean ativo;

    public Disciplina() {
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

    public Integer getCh() {
        return ch;
    }

    public void setCh(Integer ch) {
        this.ch = ch;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public List<Servidor> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Servidor> professores) {
        this.professores = professores;
    }

    public List<Coordenacao> getCoordenacoes() {
        return coordenacoes;
    }

    public void setCoordenacoes(List<Coordenacao> coordenacoes) {
        this.coordenacoes = coordenacoes;
    }

    public Integer getChCalculada() {
        return chCalculada;
    }

    public void setChCalculada(Integer chCalculada) {
        this.chCalculada = chCalculada;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Integer getQuantidadeFaltas() {
        return quantidadeFaltas;
    }

    public void setQuantidadeFaltas(Integer quantidadeFaltas) {
        this.quantidadeFaltas = quantidadeFaltas;
    }
   
}
