
package interfaces;


import java.util.List;
import negocio.Coordenacao;

public interface InterfaceRepositorioCoordenacao{
    
    public void inserir(Coordenacao c);
    
    public void alterar(Coordenacao c);
    
    public void deletar(Coordenacao c);
    
    public Coordenacao recuperar(String codigo);
    
    public Coordenacao recuperarPorNome(String nome);
    
    public List<Coordenacao> listarCoordenacoes();
    
    public List<Coordenacao> listarCoordenacoesAtivas();
}
