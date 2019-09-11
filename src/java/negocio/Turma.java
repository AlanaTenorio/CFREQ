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
import javax.persistence.ManyToOne;

@RequestScoped
@Entity
@ManagedBean(name = "turma")

public class Turma extends AbstractEntity implements Serializable {

    @Id
    @GeneratedValue
    private Integer codigo;

    private String nome;
    private String turno;

    @ManyToOne
    private Curso curso;

    @ManyToMany(mappedBy = "turmas")
    private List<Disciplina> disciplinas;

    private boolean ativo;
    
    public Turma() {
    }

    public Turma(Integer codigo, String nome, String turno, Curso curso) {
        this.codigo = codigo;
        this.nome = nome;
        this.turno = turno;
        this.curso = curso;
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

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
}
