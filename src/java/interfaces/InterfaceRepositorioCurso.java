
package interfaces;

import java.util.List;
import negocio.Curso;

public interface InterfaceRepositorioCurso {
    
    public void inserir(Curso c);

    public void alterar(Curso c);

    public void deletar(Curso c);

    public Curso recuperar(Integer codigo);
            
    public List<Curso> listarCursos();
    
    public List<Curso> listarCursosAtivos();
}
