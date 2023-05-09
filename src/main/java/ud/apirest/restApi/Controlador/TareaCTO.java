package ud.apirest.restApi.Controlador;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ud.apirest.restApi.Modelo.DAO.TareaServicio;
import ud.apirest.restApi.Modelo.DTO.TareaDTO;
/**
 * Clase que contiene los metodos para controlar las peticiones a la base de datos
 * @Autowired: anotacion que permite inyectar la dependencia en la clase
 * @RestController: anotacion que indica que la clase es un controlador
 * @RequestMapping: anotacion que indica la ruta del controlador
 * @GetMapping: anotacion que indica que el metodo es un GET
 * @PostMapping: anotacion que indica que el metodo es un POST
 * @PutMapping: anotacion que indica que el metodo es un PUT
 * @PatchMapping: anotacion que indica que el metodo es un PATCH
 * @DeleteMapping: anotacion que indica que el metodo es un DELETE
 * en este caso, la ruta es /task (localhost:8080/task)
 */
@RestController
@RequestMapping( value = "/task")
public class TareaCTO {
    @Autowired
    private TareaServicio tareaServicio;
    /**
     * @return un ArrayList con todas las tareas
     * Este metodo se ejecuta cuando se hace una peticion GET a la ruta /task
     * para obtener todas las tareas
     */
    @GetMapping
    public ArrayList<TareaDTO> index(){
        return tareaServicio.getTareas();
    }
    /**
     * @param id el id de la tarea a buscar
     * @return el objeto tarea encontrado
     * Este metodo se ejecuta cuando se hace una peticion GET a la ruta /task/{id}
     * para obtener una tarea por su id
     */
    @GetMapping( path = "/{id}")
    public Optional<TareaDTO> getTaskById(@PathVariable("id") long id){
        return tareaServicio.getTareaById(id);
    }
    /**
     * @param id el id de la tarea a actualizar
     * @param request el objeto tarea a actualizar
     * @return el objeto tarea actualizado
     * Este metodo se ejecuta cuando se hace una peticion PATCH a la ruta /task/{id}
     * para actualizar el estado de una tarea por su id
     */
    @PatchMapping( path = "/{id}")
    public Optional<TareaDTO> updateTaskById(@PathVariable("id") long id, @RequestBody TareaDTO request){
        return tareaServicio.updateTareaEstadoById(request, id);
    }
    /**
     * @param tarea el objeto tarea a guardar
     * @return el objeto tarea guardado
     * Este metodo se ejecuta cuando se hace una peticion POST a la ruta /task
     * para guardar una tarea
     */
    @PostMapping
    public TareaDTO test(@RequestBody TareaDTO tarea){
        tarea.setEstado("Pendiente");
        Date fechaActual = new Date();
        java.sql.Date fechaCreacion = new java.sql.Date(fechaActual.getTime());
        tarea.setFechaCreacion(fechaCreacion);

        return tareaServicio.saveTarea(tarea);
    }
    /**
     * @param request el objeto tarea a guardar
     * @return el objeto tarea guardado
     * Este metodo se ejecuta cuando se hace una peticion PUT a la ruta /task
     * para actualizar una tarea por su id
     */
    @PutMapping
    public Optional<TareaDTO> updateTaskById(@RequestBody TareaDTO request){
        return tareaServicio.updateTareaById(request, request.getId());
    }
    /**
     * @param id el id de la tarea a eliminar
     * @return true si la tarea fue eliminada, false si no
     * Este metodo se ejecuta cuando se hace una peticion DELETE a la ruta /task/{id}
     * para eliminar una tarea por su id
     */
    @DeleteMapping( path = "/{id}")
    public boolean deleteTaskById(@PathVariable long id){
        return tareaServicio.deleteTareaById(id);
    }

}
