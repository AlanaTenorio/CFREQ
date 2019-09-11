/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sendmail;

import interfaces.InterfaceRepositorioServidor;
import java.io.Serializable;
import java.util.List;
import java.util.Properties;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import repositorios.RepositorioServidor;

@ManagedBean(name = "controleEmail")
@SessionScoped
public class ControleEmail implements Serializable {

    private InterfaceRepositorioServidor rs = null;
    private static ControleEmail mySelf;

    public ControleEmail() {
        this.rs = new RepositorioServidor();
    }

    public static ControleEmail getInstance() {
        if (mySelf == null) {
            mySelf = new ControleEmail();
        }
        return mySelf;
    }

    public void PDados(DadosEmail dadosEmail) throws MessagingException {

        boolean saida;

        saida = enviar(dadosEmail.getDestinatarios(), dadosEmail.getLogin(), dadosEmail.getSenha(), dadosEmail.getAssunto(), dadosEmail.getMensagem());

        if (saida == true) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Notificação enviada por email.", ""));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Notificação não enviada!", ""));
        }

    }

    public static boolean enviar(List<String> listaEmails, String usuario, String senha, String assunto, String mensagem) throws MessagingException {

        List<String> adress = listaEmails;

        final String username = usuario;
        final String password = senha;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        //props.put("mail.debug", "true");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(usuario));

        for (int i = 0; i < adress.size(); i++) {
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(adress.get(i)));
        }

        message.setSubject(assunto);
        message.setText(mensagem);

        Transport.send(message);

        System.out.println("E-mail enviado!");
        return true;
    }
}
