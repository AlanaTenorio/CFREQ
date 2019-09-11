
package interfaces;

import java.util.List;
import negocio.Disciplina;
import negocio.Servidor;

public interface InterfaceRepositorioDisciplina {
    
    public void inserir(Disciplina d);
    
    public void alterar(Disciplina d);
    
    public void deletar(Disciplina d);
    
    public Disciplina recuperar(String codigo);
    
    public Disciplina recuperarPorNome(String nome);
    
    public List<Disciplina> recuperarPorNomeCoordenacao(String nomeCoordenacao);
    
    public List<Disciplina> listarDisciplinas();
    
    public List<Disciplina> listarDisciplinasAtivas();
    
    public List<Disciplina> listarDisciplinasServidor(Servidor servidor);
    
    public void calcularCH();

}
