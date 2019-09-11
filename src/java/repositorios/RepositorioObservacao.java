/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package repositorios;

import interfaces.InterfaceRepositorioObservacao;
import java.io.Serializable;
import java.util.List;
import negocio.Observacao;

/**
 *
 * @author Alana Tenório
 */
public class RepositorioObservacao implements InterfaceRepositorioObservacao, Serializable{

    @Override
    public void inserir(Observacao o) {
        dao.DaoManagerHiber.persist(o);
    }

    @Override
    public void alterar(Observacao o) {
        dao.DaoManagerHiber.update(o);
    }

    @Override
    public void deletar(Observacao o) {
        dao.DaoManagerHiber.delete(o);
    }

    @Override
    public Observacao recuperar(String codigo) {
        return (Observacao)(dao.DaoManagerHiber.recover("from Observacao where ativo = true and codigo="+ codigo).get(0));    
    }

    @Override
    public List<Observacao> listarObservacoes() {
        return (List<Observacao>)dao.DaoManagerHiber.recover("from Observacao");
    }

    @Override
    public List<Observacao> listarObservacoesAtivas() {
        return (List<Observacao>)dao.DaoManagerHiber.recover("from Observacao where ativo = true");
    }
    
}
