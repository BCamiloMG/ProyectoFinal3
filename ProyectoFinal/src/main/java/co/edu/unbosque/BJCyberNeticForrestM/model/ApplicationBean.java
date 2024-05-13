package co.edu.unbosque.BJCyberNeticForrestM.model;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

@ManagedBean(name = "applicationBean")
@ApplicationScoped
public class ApplicationBean {

    private ServletContext servletContext;

    public ApplicationBean() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        this.servletContext = (ServletContext) facesContext.getExternalContext().getContext();
    }

    public ServletContext getServletContext() {
        return servletContext;
    }
    
}
