/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio;

import javax.faces.bean.ManagedBean;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Alana Tenório
 */
@ManagedBean(name = "email")
@Entity
public class Email {
    
    @Id
    @GeneratedValue
    private Integer id;
    private String email;
    private String senha;

    public Email() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getId() {
        return id;
    }
}
