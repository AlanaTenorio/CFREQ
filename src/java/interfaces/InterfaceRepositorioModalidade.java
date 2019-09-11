/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import negocio.Modalidade;

/**
 *
 * @author Lucas
 */

public interface InterfaceRepositorioModalidade {

    public void inserir(Modalidade m);

    public void alterar(Modalidade m);

    public void deletar(Modalidade m);

    public Modalidade recuperar(Integer codigo);

    public List<Modalidade> listarModalidades();
    
    public List<Modalidade> listarModalidadesAtivas();

}
