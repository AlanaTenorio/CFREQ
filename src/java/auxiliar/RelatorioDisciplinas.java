/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auxiliar;

/**
 *
 * @author Alana Tenório
 */
public class RelatorioDisciplinas {

    private String nome;
    private String servidores;
    private Integer ch;
    private Integer chPendente;
    private String turma;
    private String turno;
    private String coordenacao;

    public RelatorioDisciplinas() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getServidores() {
        return servidores;
    }

    public void setServidores(String servidores) {
        this.servidores = servidores;
    }

    public Integer getCh() {
        return ch;
    }

    public void setCh(Integer ch) {
        this.ch = ch;
    }

    public Integer getChPendente() {
        return chPendente;
    }

    public void setChPendente(Integer chPendente) {
        this.chPendente = chPendente;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getCoordenacao() {
        return coordenacao;
    }

    public void setCoordenacao(String coordenacao) {
        this.coordenacao = coordenacao;
    }

}
