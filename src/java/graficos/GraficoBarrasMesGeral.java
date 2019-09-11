/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package graficos;

import controladores.ControleFalta;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import negocio.Falta;
import negocio.Servidor;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 * @author Alana Tenório
 */

@ManagedBean(name="graficoBarraMesGeral")
public class GraficoBarrasMesGeral implements Serializable {
    private ChartSeries graficoGeral;
    private ChartSeries graficoServidor;
    private CartesianChartModel colunasGeral = new CartesianChartModel();
    private CartesianChartModel colunasServidor = new CartesianChartModel();

 
    public ChartSeries getGraficoGeral() {
        return graficoGeral;
    }
     
    public ChartSeries getGraficoServidor() {
        return graficoServidor;
    }

    public CartesianChartModel getColunasGeral() {
        return colunasGeral;
    }

    public void setColunasGeral(CartesianChartModel colunasGeral) {
        this.colunasGeral = colunasGeral;
    }

    public CartesianChartModel getColunasServidor() {
        return colunasServidor;
    }

    public void setColunasServidor(CartesianChartModel colunasServidor) {
        this.colunasServidor = colunasServidor;
    }
    
    public String criarGraficoGeral(int inicio, int termino, int ano) {
        List<Falta> listaFaltas = ControleFalta.getInstance().contarFaltas(inicio, termino, ano);
        this.graficoGeral = new ChartSeries();
        this.graficoGeral.set("Jan/" + ano, ControleFalta.getInstance().contarFaltasJaneiro(listaFaltas));
        this.graficoGeral.set("Fev/" + ano, ControleFalta.getInstance().contarFaltasFevereiro(listaFaltas));
        this.graficoGeral.set("Mar/" + ano, ControleFalta.getInstance().contarFaltasMarco(listaFaltas));
        this.graficoGeral.set("Abr/" + ano, ControleFalta.getInstance().contarFaltasAbril(listaFaltas));
        this.graficoGeral.set("Mai/" + ano, ControleFalta.getInstance().contarFaltasMaio(listaFaltas));
        
        this.graficoGeral.set("Jun/" + ano, ControleFalta.getInstance().contarFaltasJunho(listaFaltas));
        this.graficoGeral.set("Jul/" + ano, ControleFalta.getInstance().contarFaltasJulho(listaFaltas));
        
        this.graficoGeral.set("Ago/" + ano, ControleFalta.getInstance().contarFaltasAgosto(listaFaltas));
        this.graficoGeral.set("Set/" + ano, ControleFalta.getInstance().contarFaltasSetembro(listaFaltas));
        this.graficoGeral.set("Out/" + ano, ControleFalta.getInstance().contarFaltasOutubro(listaFaltas));
        this.graficoGeral.set("Nov/" + ano, ControleFalta.getInstance().contarFaltasNovembro(listaFaltas));
        this.graficoGeral.set("Dez/" + ano, ControleFalta.getInstance().contarFaltasDezembro(listaFaltas));
        graficoGeral.setLabel("Quantidade de Faltas");
        
        System.out.println(ControleFalta.getInstance().contarFaltasJulho(listaFaltas));
        System.out.println(ControleFalta.getInstance().contarFaltasJunho(listaFaltas));
        this.colunasGeral.addSeries(graficoGeral);
        return "GraficoMesGeral.xhtml";
    }
     
    public String criarGraficoServidor(Servidor servidor, int inicio, int termino, int ano) {
        List<Falta> listaFaltas = ControleFalta.getInstance().contarFaltasServidor(servidor, inicio, termino, ano);
        
        graficoServidor = new ChartSeries();
        
        graficoServidor.set("Jan/" + ano, ControleFalta.getInstance().contarFaltasJaneiro(listaFaltas));
        graficoServidor.set("Fev/" + ano, ControleFalta.getInstance().contarFaltasFevereiro(listaFaltas));
        graficoServidor.set("Mar/" + ano, ControleFalta.getInstance().contarFaltasMarco(listaFaltas));
        graficoServidor.set("Abr/" + ano, ControleFalta.getInstance().contarFaltasAbril(listaFaltas));
        graficoServidor.set("Mai/" + ano, ControleFalta.getInstance().contarFaltasMaio(listaFaltas));
        graficoServidor.set("Jun/" + ano, ControleFalta.getInstance().contarFaltasJunho(listaFaltas));
        graficoServidor.set("Jul/" + ano, ControleFalta.getInstance().contarFaltasJulho(listaFaltas));
        graficoServidor.set("Ago/" + ano, ControleFalta.getInstance().contarFaltasAgosto(listaFaltas));
        graficoServidor.set("Set/" + ano, ControleFalta.getInstance().contarFaltasSetembro(listaFaltas));
        graficoServidor.set("Out/" + ano, ControleFalta.getInstance().contarFaltasOutubro(listaFaltas));
        graficoServidor.set("Nov/" + ano, ControleFalta.getInstance().contarFaltasNovembro(listaFaltas));
        graficoServidor.set("Dez/" + ano, ControleFalta.getInstance().contarFaltasDezembro(listaFaltas));
        graficoServidor.setLabel("Quantidade de Faltas");
                
        colunasServidor.addSeries(graficoServidor);

        return "GraficoMesServidor.xhtml";
    }
}
