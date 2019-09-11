/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package graficos;

/**
 *
 * @author Alana Tenório
 */
 
import controladores.ControleFalta;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import negocio.Falta;
import negocio.Servidor;
import org.primefaces.model.chart.PieChartModel;
 
@ManagedBean(name = "graficoPizzaOcorrenciasGeral")
public class GraficoPizzaOcorrenciasGeral implements Serializable {
 
    private PieChartModel graficoGeral;
    private PieChartModel graficoServidor;

 
    public PieChartModel getGraficoGeral() {
        return graficoGeral;
    }
     
    public PieChartModel getGraficoServidor() {
        return graficoServidor;
    }
 
    public String criarGraficoGeral(int inicio, int termino, int ano) {
        List<Falta> listaFaltas = ControleFalta.getInstance().contarFaltas(inicio, termino, ano);
        
        graficoGeral = new PieChartModel();
         
        graficoGeral.set("1011", ControleFalta.getInstance().contarFaltas1011(listaFaltas));
        graficoGeral.set("1012", ControleFalta.getInstance().contarFaltas1012(listaFaltas));
        graficoGeral.set("1013", ControleFalta.getInstance().contarFaltas1013(listaFaltas));
        graficoGeral.set("1014", ControleFalta.getInstance().contarFaltas1014(listaFaltas));
        graficoGeral.set("1015", ControleFalta.getInstance().contarFaltas1015(listaFaltas));
        graficoGeral.set("1016", ControleFalta.getInstance().contarFaltas1016(listaFaltas));
        graficoGeral.set("1017", ControleFalta.getInstance().contarFaltas1017(listaFaltas));
        graficoGeral.set("1018", ControleFalta.getInstance().contarFaltas1018(listaFaltas));
        graficoGeral.set("1019", ControleFalta.getInstance().contarFaltas1019(listaFaltas));
        graficoGeral.set("1020", ControleFalta.getInstance().contarFaltas1020(listaFaltas));

        
        return "GraficoOcorrenciasGeral.xhtml";
    }
     
    public String criarGraficoServidor(Servidor servidor, int inicio, int termino, int ano) {
        List<Falta> listaFaltas = ControleFalta.getInstance().contarFaltasServidor(servidor, inicio, termino, ano);
        
        graficoServidor = new PieChartModel();
         
        graficoServidor.set("1011", ControleFalta.getInstance().contarFaltas1011(listaFaltas));
        graficoServidor.set("1012", ControleFalta.getInstance().contarFaltas1012(listaFaltas));
        graficoServidor.set("1013", ControleFalta.getInstance().contarFaltas1013(listaFaltas));
        graficoServidor.set("1014", ControleFalta.getInstance().contarFaltas1014(listaFaltas));
        graficoServidor.set("1015", ControleFalta.getInstance().contarFaltas1015(listaFaltas));
        graficoServidor.set("1016", ControleFalta.getInstance().contarFaltas1016(listaFaltas));
        graficoServidor.set("1017", ControleFalta.getInstance().contarFaltas1017(listaFaltas));
        graficoServidor.set("1018", ControleFalta.getInstance().contarFaltas1018(listaFaltas));
        graficoServidor.set("1019", ControleFalta.getInstance().contarFaltas1019(listaFaltas));
        graficoServidor.set("1020", ControleFalta.getInstance().contarFaltas1020(listaFaltas));
         
        return "GraficoOcorrenciasServidor.xhtml";
    }
}
