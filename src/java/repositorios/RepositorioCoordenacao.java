/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorios;

import java.io.Serializable;
import java.util.List;
import negocio.Coordenacao;

/**
 *
 * @author MICROLINS
 */
public class RepositorioCoordenacao implements interfaces.InterfaceRepositorioCoordenacao, Serializable {

    @Override
    public void inserir(Coordenacao c) {
        dao.DaoManagerHiber.persist(c);
    }

    @Override
    public void alterar(Coordenacao c) {
        dao.DaoManagerHiber.update(c);
    }

    @Override
    public void deletar(Coordenacao c) {
        dao.DaoManagerHiber.delete(c);

    }

    @Override
    public Coordenacao recuperar(String codigo) {
        return (Coordenacao) (dao.DaoManagerHiber.recover("from Coordenacao where codigo=" + codigo).get(0));

    }

    @Override
    public Coordenacao recuperarPorNome(String nome) {
        return (Coordenacao) (dao.DaoManagerHiber.recover("from Coordenacao where ativo = true and nome='" + nome + "'").get(0));

    }

    @Override
    public List<Coordenacao> listarCoordenacoes() {
        return (List<Coordenacao>) dao.DaoManagerHiber.recover("from Coordenacao");
    }

    @Override
    public List<Coordenacao> listarCoordenacoesAtivas() {
        return (List<Coordenacao>) dao.DaoManagerHiber.recover("from Coordenacao where ativo = true");
    }

}
