/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorios;

import controladores.ControleCoordenacao;
import controladores.ControleFalta;
import controladores.ControleReposicao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import negocio.Coordenacao;
import negocio.Disciplina;
import negocio.Falta;
import negocio.Reposicao;
import negocio.Servidor;

/**
 *
 * @author MICROLINS
 */
public class RepositorioDisciplina implements interfaces.InterfaceRepositorioDisciplina, Serializable {

    @Override
    public void inserir(Disciplina d) {
        dao.DaoManagerHiber.persist(d);
    }

    @Override
    public void alterar(Disciplina d) {
        dao.DaoManagerHiber.update(d);
    }

    @Override
    public void deletar(Disciplina d) {
        dao.DaoManagerHiber.delete(d);
    }

    @Override
    public Disciplina recuperar(String codigo) {
        return (Disciplina) (dao.DaoManagerHiber.recover("from Disciplina where codigo=" + codigo).get(0));

    }

    @Override
    public Disciplina recuperarPorNome(String nome) {
        return (Disciplina) (dao.DaoManagerHiber.recover("from Disciplina where ativo = true and nome='" + nome + "'").get(0));

    }

    @Override
    public List<Disciplina> recuperarPorNomeCoordenacao(String nomeCoordenacao) {
        Coordenacao c = ControleCoordenacao.getInstance().recuperarCoordenacaoPorNome(nomeCoordenacao);
        return (List<Disciplina>) (dao.DaoManagerHiber.recover("from Disciplina where ativo = true and coordenacao_codigo=" + c.getCodigo()));

    }

    @Override
    public List<Disciplina> listarDisciplinas() {
        return (List<Disciplina>) dao.DaoManagerHiber.recover("from Disciplina");
    }

    @Override
    public List<Disciplina> listarDisciplinasServidor(Servidor servidor) {

        List<Disciplina> disciplinas = listarDisciplinasAtivas();
        List<Disciplina> disciplinasServidor = new ArrayList<Disciplina>();

        for (int i = 0; i < disciplinas.size(); i++) {
            for (int j = 0; j < disciplinas.get(i).getProfessores().size(); j++) {

                if (disciplinas.get(i).getProfessores().get(j).getCodigo() == servidor.getCodigo()) {
                    disciplinasServidor.add(disciplinas.get(i));
                }
            }
        }
        return disciplinasServidor;
    }

    //(carga horária da disciplina - quantidade de aulas de faltas) + quantidade de aulas de reposicao + quantidade de antecipacao
    @Override
    public void calcularCH() {
        List<Disciplina> listaDisciplinas = listarDisciplinasAtivas();
        List<Falta> listaFaltas = new ArrayList<Falta>();
        List<Reposicao> listaReposicoes = new ArrayList<Reposicao>();
        List<Reposicao> listaAntecipacoes = new ArrayList<Reposicao>();
        int chCalculada = 0;
        for (Disciplina disciplinaaux : listaDisciplinas) {
            listaFaltas = ControleFalta.getInstance().listarFaltasPorDisciplina(disciplinaaux);
            listaReposicoes = ControleReposicao.getInstance().listarReposicoesPorDisciplina(disciplinaaux);
            listaAntecipacoes = ControleReposicao.getInstance().listarAntecipacoesPorDisciplina(disciplinaaux);
            chCalculada = disciplinaaux.getCh() - ControleFalta.getInstance().somarAulas(listaFaltas)
                    + ControleReposicao.getInstance().somarAulas(listaReposicoes)
                    + ControleReposicao.getInstance().somarAulas(listaAntecipacoes);
            disciplinaaux.setChCalculada(chCalculada);
            disciplinaaux.setQuantidadeFaltas(disciplinaaux.getCh()-chCalculada);
        }
    }

    @Override
    public List<Disciplina> listarDisciplinasAtivas() {
        return (List<Disciplina>) dao.DaoManagerHiber.recover("from Disciplina where ativo = true");
    }
}
