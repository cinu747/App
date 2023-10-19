/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.ckolathabraham.web;

import edu.iit.sat.itmd4515.ckolathabraham.security.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.faces.context.FacesContext;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Cini
 */
@Named
@RequestScoped
public class LoginController {

    private static final Logger LOG = Logger.getLogger(LoginController.class.getName());
    private User user;

    @Inject
    SecurityContext securityContext;
    @Inject
    FacesContext facesContext;

    /**
     *
     */
    public LoginController() {
    }

    @PostConstruct
    private void postConstruct() {
        LOG.info("Inside the LoginController.postConstruct");
        //Initialize the model
        user = new User();
    }

    //helper method
    /**
     *
     * @return
     */
    public String getAuthenticatedUser() {
        return facesContext.getExternalContext().getRemoteUser();
    }

    /**
     *
     * @return
     */
    public boolean isAdmin() {
        return securityContext.isCallerInRole("ADMIN_ROLE");
    }

    /**
     *
     * @return
     */
    public boolean isHospital() {
        return securityContext.isCallerInRole("HOSPITAL_ROLE");
    }

    /**
     *
     * @return
     */
    public boolean isParent() {
        return securityContext.isCallerInRole("PARENT_ROLE");
    }

    //action methods 
    /**
     *
     * @return
     */
    public String doLogin() {
        LOG.info("Inside LoginController.doLogin with " + this.user.getUserName());

        //JSR-375 authN
        Credential cred = new UsernamePasswordCredential(
                this.user.getUserName(),
                new Password(this.user.getPassword()));

        AuthenticationStatus status = securityContext.authenticate(
                (HttpServletRequest) facesContext.getExternalContext().getRequest(),
                (HttpServletResponse) facesContext.getExternalContext().getResponse(),
                AuthenticationParameters.withParams().credential(cred));

        switch (status) {
            case SUCCESS:
                LOG.info(status.toString());
                break;
            case SEND_FAILURE:
                LOG.info(status.toString());
                return "/error.xhtml";
            case NOT_DONE:
                LOG.info(status.toString());
                return "/error.xhtml";
            case SEND_CONTINUE:
                LOG.info(status.toString());
                break;

        }
        return "/welcome.xhtml?faces-redirect=true";
    }

    /**
     *
     * @return
     */
    public String doLogout() {
        try {
            HttpServletRequest req
                    = (HttpServletRequest) facesContext.getExternalContext().getRequest();
            req.logout();
        } catch (ServletException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return "/login.xhtml?faces-redirect=true";
    }

    /**
     *
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }
}
