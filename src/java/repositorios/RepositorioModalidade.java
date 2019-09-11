/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package repositorios;

import java.io.Serializable;
import java.util.List;
import negocio.Modalidade;

/**
 *
 * @author Lucas
 */
public class RepositorioModalidade implements interfaces.InterfaceRepositorioModalidade, Serializable {

    @Override
    public void inserir(Modalidade m) {
       dao.DaoManagerHiber.persist(m);
    }

    @Override
    public void alterar(Modalidade m) {
       dao.DaoManagerHiber.update(m);
    }

    @Override
    public void deletar(Modalidade m) {
        dao.DaoManagerHiber.delete(m);
    }

    @Override
    public Modalidade recuperar(Integer codigo) {
       return (Modalidade) (dao.DaoManagerHiber.recover("from Modalidade where ativo = true and codigo=" + codigo).get(0));
    }

    @Override
    public List<Modalidade> listarModalidades() {
        return (List<Modalidade>) dao.DaoManagerHiber.recover("from Modalidade");
    }

    @Override
    public List<Modalidade> listarModalidadesAtivas() {
        return (List<Modalidade>) dao.DaoManagerHiber.recover("from Modalidade where ativo = true");
    }
    
    
    
}
