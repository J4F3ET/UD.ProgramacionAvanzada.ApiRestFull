package ud.apirest.restApi.Controlador;

import java.text.SimpleDateFormat;
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

@RestController
@RequestMapping( value = "/task")
public class TareaCTO {
    @Autowired
    private TareaServicio tareaServicio;

    @GetMapping
    public ArrayList<TareaDTO> index(){
        return tareaServicio.getTareas();
    }
    @GetMapping( path = "/{id}")
    public Optional<TareaDTO> getTaskById(@PathVariable("id") long id){
        return tareaServicio.getTareaById(id);
    }

    @PatchMapping( path = "/{id}")
    public Optional<TareaDTO> updateTaskById(@PathVariable("id") long id, @RequestBody TareaDTO request){
        return tareaServicio.updateTareaEstadoById(request, id);
    }

    @PostMapping
    public TareaDTO test(@RequestBody TareaDTO tarea){
        SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
        tarea.setEstado("Pendiente");
        tarea.setFechaCreacion(DateFor.format(new Date()));
        return tareaServicio.saveTarea(tarea);
    }

    @PutMapping
    public Optional<TareaDTO> updateTaskById(@RequestBody TareaDTO request){
        return tareaServicio.updateTareaById(request, request.getId());
    }

    @DeleteMapping( path = "/{id}")
    public boolean deleteTaskById(@PathVariable long id){
        return tareaServicio.deleteTareaById(id);
    }

}
