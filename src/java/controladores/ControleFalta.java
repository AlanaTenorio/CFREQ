package controladores;

import auxiliar.ServidorFalta;
import interfaces.InterfaceRepositorioFalta;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import negocio.Coordenacao;
import negocio.Disciplina;
import negocio.Email;
import negocio.Falta;
import negocio.Reposicao;
import negocio.Servidor;
import negocio.Turma;
import repositorios.RepositorioFalta;
import sendmail.ControleEmail;
import sendmail.DadosEmail;

/**
 *
 * @author Alana Tenório
 */
@ManagedBean(name = "controleFalta")
@SessionScoped

public class ControleFalta implements Serializable {

    private InterfaceRepositorioFalta rf;
    private List<Disciplina> disciplinas;
    private List<Turma> turmas;
    private Falta selected;
    private List<Reposicao>reposicoes;
    private static ControleFalta mySelf;

    public static ControleFalta getInstance() {
        if (mySelf == null) {
            mySelf = new ControleFalta();
        }
        return mySelf;
    }

    public ControleFalta() {
        this.rf = new RepositorioFalta();
    }

    public String adicionarFalta(Falta falta) throws MessagingException {

        if (falta.getDataFalta() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Insira a data da falta!", ""));
            return "";
        }

        if (falta.getServidor() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione o servidor!", ""));
            return "";
        }

        if (falta.getDisciplina() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione a disciplina!", ""));
            return "";
        }

        if (falta.getTurma() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione a turma!", ""));
            return "";
        }

        if (falta.getAulasRepor() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Insira o nº de aulas!", ""));
            return "";
        }

        if (falta.getObservacao() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione a observação!", ""));
            return "";
        }

        falta.setAtivo(true);
        this.rf.inserir(falta);
        //enviarEmail(falta);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Falta registrada com sucesso!", ""));
        return "VisualizarFaltas.xhtml";
    }

    public String alterarFalta(Falta falta) {

        if (falta.getDataFalta() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Insira a data da falta!", ""));
            return "";
        }

        if (falta.getServidor() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione o servidor!", ""));
            return "";
        }

        if (falta.getDisciplina() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione a disciplina!", ""));
            return "";
        }

        if (falta.getTurma() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione a turma!", ""));
            return "";
        }

        if (falta.getAulasRepor() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Insira o nº de aulas!", ""));
            return "";
        }

        if (falta.getObservacao() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione a observação!", ""));
            return "";
        }

        this.rf.alterar(falta);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados de falta alterados com sucesso!", ""));
        return "VisualizarFaltas.xhtml";
    }

    public List<Reposicao> listarReposicoesFalta(Falta falta) {
        List<Reposicao> rC = ControleReposicao.getInstance().listarReposicoesAtivas();
        List<Reposicao> rF = new ArrayList<Reposicao>();

        for (int i = 0; i < rC.size(); i++) {
            for (int j = 0; j < rC.get(i).getFaltasRepostas().size(); j++) {
                if (rC.get(i).getFaltasRepostas().get(j).getCodigo() == falta.getCodigo()) {
                    rF.add(rC.get(i));
                }
            }
        }
        return this.reposicoes = rF;
    }

    public Falta recuperarFalta(Integer codigo) {
        return this.rf.recuperar(codigo);
    }

    public void excluirFalta(Falta falta) {
        this.rf.deletar(falta);
    }

    public void desativar(Falta falta) {
        falta.setAtivo(false);
        falta.getReposicoes().removeAll(falta.getReposicoes());
        this.rf.alterar(falta);
    }

    public List<Falta> listarFaltas() {
        return this.rf.listarFaltas();
    }

    public List<Falta> listarFaltasAtivas() {
        return this.rf.listarFaltasAtivas();
    }

    public List<Falta> listarFaltasPorServidor(Servidor servidor) {
        return this.rf.listarFaltasPorServidor(servidor);
    }

    public List<ServidorFalta> listarFaltasPorCoordenacao(Coordenacao coordenacao) {
        return this.rf.listarFaltasPorCoordenacao(coordenacao);
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    public List<Reposicao> getReposicoes() {
        return reposicoes;
    }

    public void setReposicoes(List<Reposicao> reposicoes) {
        this.reposicoes = reposicoes;
    }
    

    //Método para Relatório COORDENACAO
    public List<Falta> listarFaltasPorServidorInicioTermino(Date inicio, Date termino, Servidor servidor) {
        List<Falta> listaFaltas = dao.DaoManagerHiber.recover("from Falta where ativo = true and servidor_codigo = " + servidor.getCodigo());
        List<Falta> lista = new ArrayList<Falta>();
        for (Falta falta : listaFaltas) {
            if (falta.getDataFalta().getTime() >= inicio.getTime() && falta.getDataFalta().getTime() <= termino.getTime()) {
                lista.add(falta);
            }
        }
        return lista;
    }

    //Método para Relatório ADM e COORDENACAO MES
    public List<Falta> listarFaltasPorServidorMes1011(int mes, Servidor servidor, int ano) {
        return dao.DaoManagerHiber.recover("from Falta WHERE ativo = true AND servidor_codigo = " + servidor.getCodigo() + " AND"
                + " month(dataFalta) = " + mes + " AND observacao_codigo = " + 1011 + " AND year(dataFalta) = " + ano);
    }

    //Método para Relatório ADM e COORDENACAO MES
    public List<Falta> listarFaltasPorServidorMes1012(int mes, Servidor servidor, int ano) {
        return dao.DaoManagerHiber.recover("from Falta WHERE ativo = true AND servidor_codigo = " + servidor.getCodigo() + " AND"
                + " month(dataFalta) = " + mes + " AND observacao_codigo = " + 1012 + " AND year(dataFalta) = " + ano);
    }

    //Método para Relatório ADM e COORDENACAO MES
    public List<Falta> listarFaltasPorServidorMes1013(int mes, Servidor servidor, int ano) {
        return dao.DaoManagerHiber.recover("from Falta WHERE ativo = true AND servidor_codigo = " + servidor.getCodigo() + " AND"
                + " month(dataFalta) = " + mes + " AND observacao_codigo = " + 1013 + " AND year(dataFalta) = " + ano);
    }

    //Método para Relatório ADM e COORDENACAO MES
    public List<Falta> listarFaltasPorServidorMes1014(int mes, Servidor servidor, int ano) {
        return dao.DaoManagerHiber.recover("from Falta WHERE ativo = true AND servidor_codigo = " + servidor.getCodigo() + " AND"
                + " month(dataFalta) = " + mes + " AND observacao_codigo = " + 1014 + " AND year(dataFalta) = " + ano);
    }

    //Método para Relatório ADM e COORDENACAO MES
    public List<Falta> listarFaltasPorServidorMes1015(int mes, Servidor servidor, int ano) {
        return dao.DaoManagerHiber.recover("from Falta WHERE ativo = true AND servidor_codigo = " + servidor.getCodigo() + " AND"
                + " month(dataFalta) = " + mes + " AND observacao_codigo = " + 1015 + " AND year(dataFalta) = " + ano);
    }

    //Método para Relatório ADM e COORDENACAO MES
    public List<Falta> listarFaltasPorServidorMes1016(int mes, Servidor servidor, int ano) {
        return dao.DaoManagerHiber.recover("from Falta WHERE ativo = true AND servidor_codigo = " + servidor.getCodigo() + " AND"
                + " month(dataFalta) = " + mes + " AND observacao_codigo = " + 1016 + " AND year(dataFalta) = " + ano);
    }

    //Método para Relatório ADM e COORDENACAO MES
    public List<Falta> listarFaltasPorServidorMes1017(int mes, Servidor servidor, int ano) {
        return dao.DaoManagerHiber.recover("from Falta WHERE ativo = true AND servidor_codigo = " + servidor.getCodigo() + " AND"
                + " month(dataFalta) = " + mes + " AND observacao_codigo = " + 1017 + " AND year(dataFalta) = " + ano);
    }

    //Método para Relatório ADM e COORDENACAO MES
    public List<Falta> listarFaltasPorServidorMes1018(int mes, Servidor servidor, int ano) {
        return dao.DaoManagerHiber.recover("from Falta WHERE ativo = true AND servidor_codigo = " + servidor.getCodigo() + " AND"
                + " month(dataFalta) = " + mes + " AND observacao_codigo = " + 1018 + " AND year(dataFalta) = " + ano);
    }

    //Método para Relatório ADM e COORDENACAO MES
    public List<Falta> listarFaltasPorServidorMes1019(int mes, Servidor servidor, int ano) {
        return dao.DaoManagerHiber.recover("from Falta WHERE ativo = true AND servidor_codigo = " + servidor.getCodigo() + " AND"
                + " month(dataFalta) = " + mes + " AND observacao_codigo = " + 1019 + " AND year(dataFalta) = " + ano);
    }

    //Método para Relatório ADM e COORDENACAO MES
    public List<Falta> listarFaltasPorServidorMes1020(int mes, Servidor servidor, int ano) {
        return dao.DaoManagerHiber.recover("from Falta WHERE ativo = true AND servidor_codigo = " + servidor.getCodigo() + " AND"
                + " month(dataFalta) = " + mes + " AND observacao_codigo = " + 1020 + " AND year(dataFalta) = " + ano);
    }

    //Método para Relatório ADM e COORDENACAO MES
    public List<Falta> listarFaltasPorServidorMes(int mes, Servidor servidor, int ano) {
        return this.rf.listarFaltasPorServidorMes(mes, servidor, ano);
    }

    public List<Falta> listarFaltasPorMes(int mes) {
        return this.rf.listarFaltasPorMes(mes);
    }

    public List<Falta> listarFaltasPorDisciplina(Disciplina disciplina) {
        return this.rf.listarFaltasPorDisciplina(disciplina);
    }

    public Integer somarAulas(List<Falta> listaFaltas) {
        return this.rf.somarAulas(listaFaltas);
    }

    public Falta getSelected() {
        return selected;
    }

    public void setSelected(Falta selected) {
        this.selected = selected;
    }

    public List<Disciplina> filtrarDisciplinasPorServidor(Servidor servidor) {
        if (servidor == null) {
            return this.disciplinas = null;
        }
        return this.disciplinas = ControleDisciplina.getInstance().listarDisciplinasPorServidor(servidor);

    }

    public List<Turma> filtrarTurmasPorDisciplina(Disciplina disciplina) {

        List<Turma> turmasCadastradas = ControleTurma.getInstance().listarTurmasAtivas();
        List<Turma> resultado = new ArrayList<Turma>();

        for (int i = 0; i < turmasCadastradas.size(); i++) {
            for (int j = 0; j < turmasCadastradas.get(i).getDisciplinas().size(); j++) {

                if (turmasCadastradas.get(i).getDisciplinas().get(j).getCodigo() == disciplina.getCodigo()) {
                    resultado.add(turmasCadastradas.get(i));
                }
            }
        }
        return this.turmas = resultado;
    }

    public Integer somarReposicoesdaFalta(Falta falta) {
        Integer soma = 0;

        for (int i = 0; i < listarReposicoesFalta(falta).size(); i++) {
            soma += listarReposicoesFalta(falta).get(i).getQuantidadeAulas();
        }

        return soma;
    }

    //Método Referente ao Gráfico Pizza
    public List<Falta> contarFaltas(int inicio, int termino, int ano) {
        return this.rf.contarFaltas(inicio, termino, ano);
    }

    //Método Referente ao Gráfico Pizza
    public List<Falta> contarFaltasServidor(Servidor servidor, int inicio, int termino, int ano) {
        return this.rf.contarFaltasServidor(servidor, inicio, termino, ano);
    }

    //Método Referente ao Gráfico Pizza
    public Integer contarFaltas1011(List<Falta> lista) {
        return this.rf.contarFaltas1011(lista);
    }

    //Método Referente ao Gráfico Pizza
    public Integer contarFaltas1012(List<Falta> lista) {
        return this.rf.contarFaltas1012(lista);
    }

    //Método Referente ao Gráfico Pizza
    public Integer contarFaltas1013(List<Falta> lista) {
        return this.rf.contarFaltas1013(lista);
    }

    //Método Referente ao Gráfico Pizza
    public Integer contarFaltas1014(List<Falta> lista) {
        return this.rf.contarFaltas1014(lista);
    }

    //Método Referente ao Gráfico Pizza
    public Integer contarFaltas1015(List<Falta> lista) {
        return this.rf.contarFaltas1015(lista);
    }

    //Método Referente ao Gráfico Pizza
    public Integer contarFaltas1016(List<Falta> lista) {
        return this.rf.contarFaltas1016(lista);
    }

    //Método Referente ao Gráfico Pizza
    public Integer contarFaltas1017(List<Falta> lista) {
        return this.rf.contarFaltas1017(lista);
    }

    //Método Referente ao Gráfico Pizza
    public Integer contarFaltas1018(List<Falta> lista) {
        return this.rf.contarFaltas1018(lista);
    }

    //Método Referente ao Gráfico Pizza
    public Integer contarFaltas1019(List<Falta> lista) {
        return this.rf.contarFaltas1019(lista);
    }

    //Método Referente ao Gráfico Pizza
    public Integer contarFaltas1020(List<Falta> lista) {
        return this.rf.contarFaltas1020(lista);
    }

    public Integer contarFaltasJaneiro(List<Falta> lista) {
        return this.rf.contarFaltasJaneiro(lista);
    }

    public Integer contarFaltasFevereiro(List<Falta> lista) {
        return this.rf.contarFaltasFevereiro(lista);
    }

    public Integer contarFaltasMarco(List<Falta> lista) {
        return this.rf.contarFaltasMarco(lista);
    }

    public Integer contarFaltasAbril(List<Falta> lista) {
        return this.rf.contarFaltasAbril(lista);
    }

    public Integer contarFaltasMaio(List<Falta> lista) {
        return this.rf.contarFaltasMaio(lista);
    }

    public Integer contarFaltasJunho(List<Falta> lista) {
        return this.rf.contarFaltasJunho(lista);
    }

    public Integer contarFaltasJulho(List<Falta> lista) {
        return this.rf.contarFaltasJulho(lista);
    }

    public Integer contarFaltasAgosto(List<Falta> lista) {
        return this.rf.contarFaltasAgosto(lista);
    }

    public Integer contarFaltasSetembro(List<Falta> lista) {
        return this.rf.contarFaltasSetembro(lista);
    }

    public Integer contarFaltasOutubro(List<Falta> lista) {
        return this.rf.contarFaltasOutubro(lista);
    }

    public Integer contarFaltasNovembro(List<Falta> lista) {
        return this.rf.contarFaltasNovembro(lista);
    }

    public Integer contarFaltasDezembro(List<Falta> lista) {
        return this.rf.contarFaltasDezembro(lista);
    }

    public void enviarEmail(Falta falta) throws MessagingException {

        String dataFormatada = new SimpleDateFormat("dd/MM/yyyy").format(falta.getDataFalta());

        String assunto = "Inclusão de nova falta";
        String mensagem = "Falta adicionada para servidor: " + falta.getServidor().getNome()
                + "\nData: " + dataFormatada
                + "\nDisciplina: " + falta.getDisciplina().getTurno()
                + "\nTurma: " + falta.getTurma().getNome()
                + "\nQuantidade de aulas: " + falta.getAulasRepor()
                + "\nObservação: " + falta.getObservacao().getObservacao()
                + "\nAnotações: " + falta.getAnotacao();

        List<String> emails = new ArrayList<String>();
        emails.add(falta.getServidor().getEmail());
        emails.add(falta.getServidor().getCoordenacao().getEmail());

        DadosEmail dadosEmail = new DadosEmail();
        dadosEmail.setDestinatarios(emails);
        dadosEmail.setAssunto(assunto);
        dadosEmail.setMensagem(mensagem);
        dadosEmail.setLogin(recuperarEmail().getEmail());
        dadosEmail.setSenha(recuperarEmail().getSenha());

        ControleEmail.getInstance().PDados(dadosEmail);
    }

    private Email recuperarEmail() {
        return (Email) dao.DaoManagerHiber.recover("from Email").get(0);
    }
}
