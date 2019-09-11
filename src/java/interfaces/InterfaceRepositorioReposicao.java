
package interfaces;

import auxiliar.ServidorReposicao;
import java.util.Date;
import java.util.List;
import negocio.Coordenacao;
import negocio.Disciplina;
import negocio.Falta;
import negocio.Reposicao;
import negocio.Servidor;

public interface InterfaceRepositorioReposicao {
    
    public void inserir(Reposicao r);
    
    public void alterar(Reposicao r);
    
    public void deletar(Reposicao r);
    
    public Reposicao recuperar(String codigo);
    
    public List<Reposicao> listarReposicoes();
    
    public List<Reposicao> listarReposicoesAtivas();

    public List<Reposicao> listarReposicoesServidorMes(int mes, Servidor servidor, int ano);
    
    public List<Reposicao> listarAntecipacoesServidorMes(int mes, Servidor servidor, int ano);
    
    public List<Reposicao> listarReposicoesPorDisciplina(Disciplina disciplina);
    
    public List<Reposicao> listarAntecipacoesPorDisciplina(Disciplina disciplina);
    
    public Integer somarAulas(List<Reposicao> listaReposicoes);
    
    public List<Reposicao> listarReposicoesPorFalta (Falta falta);
    
    public List<Reposicao> listarReposicoesPorServidor (Servidor servidor);
    
    public List<ServidorReposicao> listarServidorReposicoesPorCoordenacao(List<List<Reposicao>> listaReposicoes);
    
    public List<ServidorReposicao> listarReposicoesPorCoordenacao(Coordenacao coordenacao);
    
    public List<Reposicao> listarReposicoesServidorData(Servidor servidor, Date inicio, Date termino);
    
    public List<Reposicao> listarAntecipacoesServidorData(Servidor servidor, Date inicio, Date termino);

}
