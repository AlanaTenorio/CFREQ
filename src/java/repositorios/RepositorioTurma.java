/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorios;

import interfaces.InterfaceRepositorioTurma;
import java.io.Serializable;
import java.util.List;
import negocio.Turma;

/**
 *
 * @author Lucas
 */
public class RepositorioTurma implements InterfaceRepositorioTurma, Serializable {

    @Override
    public void inserir(Turma t) {
        dao.DaoManagerHiber.persist(t);
    }

    @Override
    public void alterar(Turma t) {
        dao.DaoManagerHiber.update(t);
    }

    @Override
    public void deletar(Turma t) {
        dao.DaoManagerHiber.delete(t);
    }

    @Override
    public Turma recuperar(Integer codigo) {
        return (Turma) (dao.DaoManagerHiber.recover("from Turma where ativo = true AND codigo=" + codigo).get(0));
    }

    @Override
    public List<Turma> listarTurmas() {
        return (List<Turma>) dao.DaoManagerHiber.recover("from Turma");

    }

    @Override
    public List<Turma> listarTurmasAtivas() {
        return (List<Turma>) dao.DaoManagerHiber.recover("from Turma where ativo = true");
    }

}
