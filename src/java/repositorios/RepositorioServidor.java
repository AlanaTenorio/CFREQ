/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorios;

import java.io.Serializable;
import java.util.List;
import negocio.Coordenacao;
import negocio.Servidor;

/**
 *
 * @author MICROLINS
 */
public class RepositorioServidor implements interfaces.InterfaceRepositorioServidor, Serializable {

    @Override
    public void inserir(Servidor s) {
        dao.DaoManagerHiber.persist(s);
    }

    @Override
    public void alterar(Servidor s) {
        dao.DaoManagerHiber.update(s);
    }

    @Override
    public void deletar(Servidor s) {
        dao.DaoManagerHiber.delete(s);
    }

    @Override
    public Servidor recuperar(String siape) {
        return (Servidor) (dao.DaoManagerHiber.recover("from Servidor where ativo = true AND siape=" + siape).get(0));
    }

    @Override
    public Servidor recuperarPorCodigo(Integer codigo) {
        return (Servidor) (dao.DaoManagerHiber.recover("from Servidor where ativo = true AND codigo=" + codigo).get(0));
    }

    @Override
    public List<Servidor> listarServidores() {
        return (List<Servidor>) dao.DaoManagerHiber.recover("from Servidor");
    }
    
    @Override
    public List<Servidor> listarServidoresPorCoordenacao(Coordenacao coordenacao){
        return (List<Servidor>) dao.DaoManagerHiber.recover("from Servidor where ativo = true AND coordenacao_codigo="+coordenacao.getCodigo());
    }

    @Override
    public List<Servidor> listarServidoresAtivos() {
        return (List<Servidor>) dao.DaoManagerHiber.recover("from Servidor where ativo = true");
    }

}
