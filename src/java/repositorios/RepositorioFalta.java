/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorios;

import auxiliar.ServidorFalta;
import interfaces.InterfaceRepositorioFalta;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import negocio.Coordenacao;
import negocio.Disciplina;
import negocio.Falta;
import negocio.Servidor;

/**
 *
 * @author Lucas
 */
public class RepositorioFalta implements InterfaceRepositorioFalta, Serializable {

    @Override
    public void inserir(Falta f) {
        dao.DaoManagerHiber.persist(f);
    }

    @Override
    public void alterar(Falta f) {
        dao.DaoManagerHiber.update(f);
    }

    @Override
    public void deletar(Falta f) {
        dao.DaoManagerHiber.delete(f);
    }

    @Override
    public Falta recuperar(Integer codigo) {
        return (Falta) (dao.DaoManagerHiber.recover("from Falta where ativo = true and codigo=" + codigo).get(0));
    }

    @Override
    public List<Falta> listarFaltas() {
        return (List<Falta>) dao.DaoManagerHiber.recover("from Falta");
    }

    @Override
    public List<Falta> listarFaltasPorServidor(Servidor servidor) {
        return (List<Falta>) dao.DaoManagerHiber.recover("from Falta where ativo = true and servidor_codigo=" + servidor.getCodigo());
    }

    @Override
    public List<ServidorFalta> listarServidorFaltasPorCoordenacao(List<List<Falta>> listaFaltas) {
        List<ServidorFalta> listaServFaltas = new ArrayList<ServidorFalta>();
        for (int i = 0; i < listaFaltas.size(); i++) {
            for (int j = 0; j < listaFaltas.get(i).size(); j++) {
                ServidorFalta servidorFalta = new ServidorFalta();
                servidorFalta.setFalta(listaFaltas.get(i).get(j));
                servidorFalta.setServidor(listaFaltas.get(i).get(j).getServidor());
                listaServFaltas.add(servidorFalta);
            }
        }
        return listaServFaltas;
    }
    
   
    @Override
    public List<ServidorFalta> listarFaltasPorCoordenacao(Coordenacao coordenacao) {
        List<List<Falta>> listaFaltas = new ArrayList<List<Falta>>();
        List<Servidor> listaServidores = (List<Servidor>) dao.DaoManagerHiber.recover("from Servidor where ativo = true and coordenacao_codigo=" + coordenacao.getCodigo());
        for (int i = 0; i < listaServidores.size(); i++) {
            listaFaltas.add(dao.DaoManagerHiber.recover("from Falta where ativo = true and servidor_codigo=" + listaServidores.get(i).getCodigo()));
        }
        return listarServidorFaltasPorCoordenacao(listaFaltas);
    }
    
    //Método para Relatório ADM
    @Override
    public List<Falta> listarFaltasPorServidorMes(int mes, Servidor servidor, int ano){
        return dao.DaoManagerHiber.recover("from Falta WHERE ativo = true AND servidor_codigo = " + servidor.getCodigo() + " AND"
                + " month(dataFalta) = " + mes + " AND year(dataFalta) = " + ano);
    }
    
    
    @Override
    public List<Falta> listarFaltasPorMes(int mes) {
       List<Falta> listaFaltas = dao.DaoManagerHiber.recover("from Falta where ativo = true and month(dataFalta) = " + mes);
       return listaFaltas;
    }
    
    @Override
    public List<Falta> listarFaltasPorDisciplina(Disciplina disciplina){
        return dao.DaoManagerHiber.recover("from Falta where ativo = true and disciplina_codigo = " + disciplina.getCodigo());
    }
    
    @Override
    public Integer somarAulas(List<Falta> listaFaltas){
        Integer soma = 0;
        for (Falta faltaaux : listaFaltas) {
            soma += faltaaux.getAulasRepor();
        }
        return soma;
    }

    @Override
    public List<Falta> listarFaltasAtivas() {
        return (List<Falta>) dao.DaoManagerHiber.recover("from Falta where ativo = true");
    }
    
    @Override
    public List<Falta> contarFaltas(int inicio, int termino, int ano){
        return dao.DaoManagerHiber.recover("from Falta WHERE ativo = true and month(dataFalta) >= " + inicio + "AND month(dataFalta) <= " + termino +
        " AND year(dataFalta) = " + ano);
    }
    
    @Override
    public List<Falta> contarFaltasServidor(Servidor servidor, int inicio, int termino, int ano){
        return dao.DaoManagerHiber.recover("from Falta WHERE ativo = true and month(dataFalta) >= " + inicio + "AND month(dataFalta) <= " + termino +
                " AND servidor_codigo = " + servidor.getCodigo() + " AND year(dataFalta) = " + ano);
    }
    
    @Override
    public Integer contarFaltas1011(List<Falta> lista){
        List<Falta> faltas = new ArrayList<Falta>();
        for (Falta aux: lista) {
            if(aux.getObservacao().getCodigo() == 1011){
                faltas.add(aux);
            }
        }
        return faltas.size();
    }
    @Override
    public Integer contarFaltas1012(List<Falta> lista){
        List<Falta> faltas = new ArrayList<Falta>();
        for (Falta aux: lista) {
            if(aux.getObservacao().getCodigo() == 1012){
                faltas.add(aux);
            }
        }
        return faltas.size();
    }
    @Override
    public Integer contarFaltas1013(List<Falta> lista){
        List<Falta> faltas = new ArrayList<Falta>();
        for (Falta aux: lista) {
            if(aux.getObservacao().getCodigo() == 1013){
                faltas.add(aux);
            }
        }
        return faltas.size();
    }
    @Override
    public Integer contarFaltas1014(List<Falta> lista){
        List<Falta> faltas = new ArrayList<Falta>();
        for (Falta aux: lista) {
            if(aux.getObservacao().getCodigo() == 1014){
                faltas.add(aux);
            }
        }
        return faltas.size();
    }
    @Override
    public Integer contarFaltas1015(List<Falta> lista){
        List<Falta> faltas = new ArrayList<Falta>();
        for (Falta aux: lista) {
            if(aux.getObservacao().getCodigo() == 1015){
                faltas.add(aux);
            }
        }
        return faltas.size();
    }
    @Override
    public Integer contarFaltas1016(List<Falta> lista){
        List<Falta> faltas = new ArrayList<Falta>();
        for (Falta aux: lista) {
            if(aux.getObservacao().getCodigo() == 1016){
                faltas.add(aux);
            }
        }
        return faltas.size();
    }
    @Override
    public Integer contarFaltas1017(List<Falta> lista){
        List<Falta> faltas = new ArrayList<Falta>();
        for (Falta aux: lista) {
            if(aux.getObservacao().getCodigo() == 1017){
                faltas.add(aux);
            }
        }
        return faltas.size();
    }
    @Override
    public Integer contarFaltas1018(List<Falta> lista){
        List<Falta> faltas = new ArrayList<Falta>();
        for (Falta aux: lista) {
            if(aux.getObservacao().getCodigo() == 1018){
                faltas.add(aux);
            }
        }
        return faltas.size();
    }
    @Override
    public Integer contarFaltas1019(List<Falta> lista){
        List<Falta> faltas = new ArrayList<Falta>();
        for (Falta aux: lista) {
            if(aux.getObservacao().getCodigo() == 1019){
                faltas.add(aux);
            }
        }
        return faltas.size();
    }
    @Override
    public Integer contarFaltas1020(List<Falta> lista){
        List<Falta> faltas = new ArrayList<Falta>();
        for (Falta aux: lista) {
            if(aux.getObservacao().getCodigo() == 1020){
                faltas.add(aux);
            }
        }
        return faltas.size();
    }
    
    @Override
    public Integer contarFaltasJaneiro(List<Falta> lista){
        List<Falta> faltas = new ArrayList<Falta>();
        for (Falta falta : lista) {
            if(falta.getDataFalta().getMonth() == 0){
                faltas.add(falta);
            }
        }
        return faltas.size();
    }
    
    @Override
    public Integer contarFaltasFevereiro(List<Falta> lista){
        List<Falta> faltas = new ArrayList<Falta>();
        for (Falta falta : lista) {
            if(falta.getDataFalta().getMonth() == 1){
                faltas.add(falta);
            }
        }
        return faltas.size();
    }
    @Override
    public Integer contarFaltasMarco(List<Falta> lista){
        List<Falta> faltas = new ArrayList<Falta>();
        for (Falta falta : lista) {
            if(falta.getDataFalta().getMonth() == 2){
                faltas.add(falta);
            }
        }
        return faltas.size();
    }
    @Override
    public Integer contarFaltasAbril(List<Falta> lista){
        List<Falta> faltas = new ArrayList<Falta>();
        for (Falta falta : lista) {
            if(falta.getDataFalta().getMonth() == 3){
                faltas.add(falta);
            }
        }
        return faltas.size();
    }
    @Override
    public Integer contarFaltasMaio(List<Falta> lista){
        List<Falta> faltas = new ArrayList<Falta>();
        for (Falta falta : lista) {
            if(falta.getDataFalta().getMonth() == 4){
                faltas.add(falta);
            }
        }
        return faltas.size();
    }
    @Override
    public Integer contarFaltasJunho(List<Falta> lista){
        List<Falta> faltas = new ArrayList<Falta>();
        for (Falta falta : lista) {
            if(falta.getDataFalta().getMonth() == 5){
                faltas.add(falta);
            }
        }
        return faltas.size();
    }
    
    @Override
    public Integer contarFaltasJulho(List<Falta> lista){
        List<Falta> faltas = new ArrayList<Falta>();
        for (Falta falta : lista) {
            if(falta.getDataFalta().getMonth() == 6){
                faltas.add(falta);
            }
        }
        return faltas.size();
    }
    
    @Override
    public Integer contarFaltasAgosto(List<Falta> lista){
        List<Falta> faltas = new ArrayList<Falta>();
        for (Falta falta : lista) {
            if(falta.getDataFalta().getMonth() == 7){
                faltas.add(falta);
            }
        }
        return faltas.size();
    }
    
    @Override
    public Integer contarFaltasSetembro(List<Falta> lista){
        List<Falta> faltas = new ArrayList<Falta>();
        for (Falta falta : lista) {
            if(falta.getDataFalta().getMonth() == 8){
                faltas.add(falta);
            }
        }
        return faltas.size();
    }
    @Override
    public Integer contarFaltasOutubro(List<Falta> lista){
        List<Falta> faltas = new ArrayList<Falta>();
        for (Falta falta : lista) {
            if(falta.getDataFalta().getMonth() == 9){
                faltas.add(falta);
            }
        }
        return faltas.size();
    }
    @Override
    public Integer contarFaltasNovembro(List<Falta> lista){
        List<Falta> faltas = new ArrayList<Falta>();
        for (Falta falta : lista) {
            if(falta.getDataFalta().getMonth() == 10){
                faltas.add(falta);
            }
        }
        return faltas.size();
    }
    
    @Override
    public Integer contarFaltasDezembro(List<Falta> lista){
        List<Falta> faltas = new ArrayList<Falta>();
        for (Falta falta : lista) {
            if(falta.getDataFalta().getMonth() == 11){
                faltas.add(falta);
            }
        }
        return faltas.size();
    }
}

