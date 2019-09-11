/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar;

import negocio.Reposicao;
import negocio.Servidor;

/**
 *
 * @author Alana Tenório
 */
public class ServidorReposicao {
    private Servidor servidor;
    private Reposicao reposicao;

    public ServidorReposicao() {
    }

    public ServidorReposicao(Servidor servidor, Reposicao reposicao) {
        this.servidor = servidor;
        this.reposicao = reposicao;
    }

    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    public Reposicao getReposicao() {
        return reposicao;
    }

    public void setReposicao(Reposicao reposicao) {
        this.reposicao = reposicao;
    }
    
}
