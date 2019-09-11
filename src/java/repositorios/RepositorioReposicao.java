/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package repositorios;

import auxiliar.ServidorReposicao;
import interfaces.InterfaceRepositorioReposicao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import negocio.Coordenacao;
import negocio.Disciplina;
import negocio.Falta;
import negocio.Reposicao;
import negocio.Servidor;

/**
 *
 * @author Alana Tenório
 */
public class RepositorioReposicao implements InterfaceRepositorioReposicao, Serializable{

    @Override
    public void inserir(Reposicao r) {
        dao.DaoManagerHiber.persist(r);
    }

    @Override
    public void alterar(Reposicao r) {
        dao.DaoManagerHiber.update(r);
    }

    @Override
    public void deletar(Reposicao r) {
        dao.DaoManagerHiber.delete(r);
    }

    @Override
    public Reposicao recuperar(String codigo) {
        return (Reposicao) dao.DaoManagerHiber.recover("from Reposicao where ativo = true AND codigo="+ codigo).get(0);
    }

    @Override
    public List<Reposicao> listarReposicoes() {
        return (List<Reposicao>) dao.DaoManagerHiber.recover("from Reposicao");
    }

    //Metódo para Relatório ADM e COORDENACAO MES
    @Override
    public List<Reposicao> listarReposicoesServidorMes(int mes, Servidor servidor, int ano){
        return (List<Reposicao>) dao.DaoManagerHiber.recover("from Reposicao WHERE ativo = true AND servidor_codigo = " + servidor.getCodigo() + " AND"
                + " month(dataReposicao) = " + mes + "AND tipo = 'Reposicao' AND year(dataReposicao) = " + ano);
    }
    
    //Metódo para Relatório ADM e COORDENACAO MES
    @Override
    public List<Reposicao> listarAntecipacoesServidorMes(int mes, Servidor servidor, int ano) {
        return (List<Reposicao>) dao.DaoManagerHiber.recover("from Reposicao WHERE ativo = true AND servidor_codigo = " + servidor.getCodigo() + " AND"
                + " month(dataReposicao) = " + mes + "AND tipo = 'Antecipacao' AND year(dataReposicao) = " + ano);
    }
    
    @Override
    public List<Reposicao> listarReposicoesPorDisciplina(Disciplina disciplina){
        return dao.DaoManagerHiber.recover("from Reposicao where ativo = true AND disciplina_codigo = " + disciplina.getCodigo() +  "and tipo = 'Reposicao'");
    }

    @Override
    public List<Reposicao> listarAntecipacoesPorDisciplina(Disciplina disciplina) {
        return dao.DaoManagerHiber.recover("from Reposicao where ativo = true AND disciplina_codigo = " + disciplina.getCodigo() + "and tipo = 'Antecipacao'");
    }

    @Override
    public Integer somarAulas(List<Reposicao> listaReposicoes) {
        Integer soma = 0;
        Integer quantidadeDeFaltas;
        for (Reposicao reposicaoaux : listaReposicoes) {
            soma += reposicaoaux.getQuantidadeAulas();
        }
        return soma;
    }
    
    //Método para Relatório Coordenação
    @Override
    public List<Reposicao> listarReposicoesPorFalta (Falta falta){
        return dao.DaoManagerHiber.recover("from reposicao_falta where ativo = true AND faltasRepostas_codigo = " + falta.getCodigo());
    }
    
    //Método para Relatório de faltas por Servidor
    public List<Reposicao> listarReposicoesServidorData(Servidor servidor, Date inicio, Date termino){
        List<Reposicao> listaReposicoes = new ArrayList<Reposicao>();
        List<Reposicao> lista = dao.DaoManagerHiber.recover("from Reposicao where ativo = true  and tipo = 'Reposicao' and servidor_codigo = " + servidor.getCodigo());
        for (Reposicao reposicao : lista) {
            if(reposicao.getDataReposicao().getTime() >= inicio.getTime() && reposicao.getDataReposicao().getTime() <= termino.getTime()){
                listaReposicoes.add(reposicao);
            }
        }
        return listaReposicoes;
    }
    //Método para Relatório de faltas por Servidor
    public List<Reposicao> listarAntecipacoesServidorData(Servidor servidor, Date inicio, Date termino){
        List<Reposicao> listaReposicoes = new ArrayList<Reposicao>();
        List<Reposicao> lista = dao.DaoManagerHiber.recover("from Reposicao where ativo = true  and tipo = 'Antecipacao' and servidor_codigo = " + servidor.getCodigo());
        for (Reposicao reposicao : lista) {
            if(reposicao.getDataReposicao().getTime() >= inicio.getTime() && reposicao.getDataReposicao().getTime() <= termino.getTime()){
                listaReposicoes.add(reposicao);
            }
        }
        return listaReposicoes;
    }

    @Override
    public List<Reposicao> listarReposicoesAtivas() {
        return (List<Reposicao>) dao.DaoManagerHiber.recover("from Reposicao where ativo = true");
    }

    @Override
    public List<Reposicao> listarReposicoesPorServidor(Servidor servidor) {
        return (List<Reposicao>) dao.DaoManagerHiber.recover("from Reposicao where ativo = true and servidor_codigo = " + servidor.getCodigo());
    }
    
    @Override
    public List<ServidorReposicao> listarServidorReposicoesPorCoordenacao(List<List<Reposicao>> listaReposicoes) {
        List<ServidorReposicao> listaServFaltas = new ArrayList<ServidorReposicao>();
        for (int i = 0; i < listaReposicoes.size(); i++) {
            for (int j = 0; j < listaReposicoes.get(i).size(); j++) {
                ServidorReposicao servidorReposicao = new ServidorReposicao();
                servidorReposicao.setReposicao(listaReposicoes.get(i).get(j));
                servidorReposicao.setServidor(listaReposicoes.get(i).get(j).getServidor());
                listaServFaltas.add(servidorReposicao);
            }
        }
        return listaServFaltas;
    }
    
   
    @Override
    public List<ServidorReposicao> listarReposicoesPorCoordenacao(Coordenacao coordenacao) {
        List<List<Reposicao>> listaReposicoes = new ArrayList<List<Reposicao>>();
        List<Servidor> listaServidores = (List<Servidor>) dao.DaoManagerHiber.recover("from Servidor where ativo = true and coordenacao_codigo=" + coordenacao.getCodigo());
        for (int i = 0; i < listaServidores.size(); i++) {
            listaReposicoes.add(dao.DaoManagerHiber.recover("from Reposicao where ativo = true and servidor_codigo=" + listaServidores.get(i).getCodigo()));
        }
        return listarServidorReposicoesPorCoordenacao(listaReposicoes);
    }
}
