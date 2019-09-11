/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package auxiliar;

import negocio.Falta;
import negocio.Servidor;

/**
 *
 * @author Alana Tenório
 */
public class ServidorFalta {
    private Servidor servidor;
    private Falta falta;

    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    public Falta getFalta() {
        return falta;
    }

    public void setFalta(Falta falta) {
        this.falta = falta;
    }

    
}
