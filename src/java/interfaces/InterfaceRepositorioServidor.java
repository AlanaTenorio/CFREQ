
package interfaces;

import java.util.List;
import negocio.Coordenacao;
import negocio.Servidor;

public interface InterfaceRepositorioServidor {

    public void inserir(Servidor s);

    public void alterar(Servidor s);

    public void deletar(Servidor s);

    public Servidor recuperar(String siape);
    
    public Servidor recuperarPorCodigo(Integer codigo);
    
    public List<Servidor> listarServidores();

    public List<Servidor> listarServidoresAtivos();
    
    public List<Servidor> listarServidoresPorCoordenacao(Coordenacao c);
}
