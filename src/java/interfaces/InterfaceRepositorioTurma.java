
package interfaces;

import java.util.List;
import negocio.Turma;

public interface InterfaceRepositorioTurma {
    
    public void inserir(Turma t);

    public void alterar(Turma t);

    public void deletar(Turma t);

    public Turma recuperar(Integer codigo);
            
    public List<Turma> listarTurmas();
    
    public List<Turma> listarTurmasAtivas();
    
}
