/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar;

import java.util.Date;
import negocio.Coordenacao;
import negocio.Observacao;
import negocio.Servidor;
import negocio.Turma;

/**
 *
 * @author Alana Tenório
 */
public class RelatorioCoordenacao {
    Servidor servidor;
    Date dataFalta;
    String datasReposicoes;
    Turma turma;
    Observacao observacao;
    Coordenacao coordenacao;

    public RelatorioCoordenacao() {
    }

    public Coordenacao getCoordenacao() {
        return coordenacao;
    }

    public void setCoordenacao(Coordenacao coordenacao) {
        this.coordenacao = coordenacao;
    }

    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    public Date getDataFalta() {
        return dataFalta;
    }

    public void setDataFalta(Date dataFalta) {
        this.dataFalta = dataFalta;
    }

    public String getDatasReposicoes() {
        return datasReposicoes;
    }

    public void setDatasReposicoes(String datasReposicoes) {
        this.datasReposicoes = datasReposicoes;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Observacao getObservacao() {
        return observacao;
    }

    public void setObservacao(Observacao observacao) {
        this.observacao = observacao;
    }
}
