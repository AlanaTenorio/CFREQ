/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import auxiliar.ServidorFalta;
import java.util.List;
import negocio.Coordenacao;
import negocio.Disciplina;
import negocio.Falta;
import negocio.Servidor;

/**
 *
 * @author Lucas
 */
public interface InterfaceRepositorioFalta {

    public void inserir(Falta f);

    public void alterar(Falta f);

    public void deletar(Falta f);

    public Falta recuperar(Integer codigo);

    public List<Falta> listarFaltas();
    
    public List<Falta> listarFaltasAtivas();
    
    public List<Falta> listarFaltasPorServidor(Servidor servidor);
  
    public List<ServidorFalta> listarFaltasPorCoordenacao(Coordenacao coordenacao);
    
    public List<ServidorFalta> listarServidorFaltasPorCoordenacao(List<List<Falta>> listaFaltas);
    
    public List<Falta> listarFaltasPorMes(int mes);
    
    public List<Falta> listarFaltasPorServidorMes(int mes, Servidor servidor, int ano);
    
    public List<Falta> listarFaltasPorDisciplina(Disciplina disciplina);

    public Integer somarAulas(List<Falta> listaFaltas);
    
    public List<Falta> contarFaltas(int inicio, int termino, int ano);
    
    public List<Falta> contarFaltasServidor(Servidor servidor, int inicio, int termino, int ano);
    
    public Integer contarFaltas1011(List<Falta> lista);
    public Integer contarFaltas1012(List<Falta> lista);
    public Integer contarFaltas1013(List<Falta> lista);
    public Integer contarFaltas1014(List<Falta> lista);
    public Integer contarFaltas1015(List<Falta> lista);
    public Integer contarFaltas1016(List<Falta> lista);
    public Integer contarFaltas1017(List<Falta> lista);
    public Integer contarFaltas1018(List<Falta> lista);
    public Integer contarFaltas1019(List<Falta> lista);
    public Integer contarFaltas1020(List<Falta> lista);
    
    public Integer contarFaltasJaneiro(List<Falta> lista);
    public Integer contarFaltasFevereiro(List<Falta> lista);
    public Integer contarFaltasMarco(List<Falta> lista);
    public Integer contarFaltasAbril(List<Falta> lista);
    public Integer contarFaltasMaio(List<Falta> lista);
    public Integer contarFaltasJunho(List<Falta> lista);
    public Integer contarFaltasJulho(List<Falta> lista);
    public Integer contarFaltasAgosto(List<Falta> lista);
    public Integer contarFaltasSetembro(List<Falta> lista);
    public Integer contarFaltasOutubro(List<Falta> lista);
    public Integer contarFaltasNovembro(List<Falta> lista);
    public Integer contarFaltasDezembro(List<Falta> lista);
}
