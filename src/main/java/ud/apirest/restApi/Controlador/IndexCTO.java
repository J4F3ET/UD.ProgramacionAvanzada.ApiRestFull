package ud.apirest.restApi.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
/**
 * Clase que contiene los metodos para controlar las peticiones a la vista index
 * @Controller: anotacion que indica que la clase es un controlador
 * @GetMapping: anotacion que indica que el metodo es un GET
 * en este caso, la ruta es / (localhost:8080/) o /index (localhost:8080/index)
 */
@Controller
public class IndexCTO { 
    /**
     * Metodo que retorna la vista index
     * @return ModelAndView: retorna la vista index
     */
    @GetMapping({"/", "/index"})
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }
}
