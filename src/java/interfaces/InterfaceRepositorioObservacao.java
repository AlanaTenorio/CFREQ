
package interfaces;

import java.util.List;
import negocio.Observacao;

public interface InterfaceRepositorioObservacao {
    
    public void inserir(Observacao o);
    
    public void alterar(Observacao o);
    
    public void deletar(Observacao o);
    
    public Observacao recuperar(String codigo);
    
    public List<Observacao> listarObservacoes();
    
    public List<Observacao> listarObservacoesAtivas();
}
