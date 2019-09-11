/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorios;

import interfaces.InterfaceRepositorioCurso;
import java.io.Serializable;
import java.util.List;
import negocio.Curso;

/**
 *
 * @author Lucas
 */
public class RepositorioCurso implements InterfaceRepositorioCurso, Serializable {

    @Override
    public void inserir(Curso c) {
        dao.DaoManagerHiber.persist(c);
    }

    @Override
    public void alterar(Curso c) {
        dao.DaoManagerHiber.update(c);
    }

    @Override
    public void deletar(Curso c) {
        dao.DaoManagerHiber.delete(c);
    }

    @Override
    public Curso recuperar(Integer codigo) {
        return (Curso) (dao.DaoManagerHiber.recover("from Curso where codigo=" + codigo).get(0));
    }

    @Override
    public List<Curso> listarCursos() {
        return (List<Curso>) dao.DaoManagerHiber.recover("from Curso");
    }

    @Override
    public List<Curso> listarCursosAtivos() {
        return (List<Curso>) dao.DaoManagerHiber.recover("from Curso where ativo = true");
    }

}
