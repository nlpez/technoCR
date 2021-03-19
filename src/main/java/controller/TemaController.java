
package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;


@Named(value = "temaController")
@SessionScoped
public class TemaController implements Serializable {
    
    private String tema;    

  
    public TemaController() {
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }
    
}
