/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar;

import java.util.Date;
import negocio.Disciplina;
import negocio.Servidor;

/**
 *
 * @author Alana Tenório
 */
public class RelatorioReposicao {
    
    private Date dataReposicao;
    private Disciplina disciplina;
    private Integer quantAulas;
    private Servidor servidor;
    private String faltas; // Data, Turma, Observação, quant

    public RelatorioReposicao() {
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

    public Integer getQuantAulas() {
        return quantAulas;
    }

    public void setQuantAulas(Integer quantAulas) {
        this.quantAulas = quantAulas;
    }

    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    public String getFaltas() {
        return faltas;
    }

    public void setFaltas(String faltas) {
        this.faltas = faltas;
    }
}
