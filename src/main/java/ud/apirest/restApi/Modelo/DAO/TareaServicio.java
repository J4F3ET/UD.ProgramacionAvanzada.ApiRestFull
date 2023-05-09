package ud.apirest.restApi.Modelo.DAO;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ud.apirest.restApi.Modelo.DTO.TareaDTO;
import ud.apirest.restApi.Repository.TareaIn;

@Service
public class TareaServicio {
    @Autowired
    private TareaIn tareaRepository;
    /**
     * @return las tareas en el repositorio, o en la base de datos
     */
    public ArrayList<TareaDTO> getTareas(){
        return (ArrayList<TareaDTO>) tareaRepository.findAll();
    }
    /**
     * @param tarea la tarea a guardar
     * @return la tarea guardada en el repositorio, o en la base de datos
     * Guarda tarea en la base de datos, y retorna la tarea guardada
     */
    public TareaDTO saveTarea(TareaDTO tarea){
        return tareaRepository.save(tarea);
    }
    /**
     * @param id el id de la tarea a buscar
     * @return la tarea con el id especificado
     */
    public Optional<TareaDTO> getTareaById(long id){
        return tareaRepository.findById(id);
    }
     /**
     * @param request la tarea nueva
     * @param id el id de la tarea a actualizar
     * @return la tarea actualizada
     * La funcion actualiza la tarea con el id especificado, con los datos de la tarea nueva, cambiando todos los datos de la tarea (metodo PUT)
     * y retorna la tarea actualizada
     */
    public Optional<TareaDTO> updateTareaById(TareaDTO request, long id){
        Optional<TareaDTO> tareaToUpdate = tareaRepository.findById(id);
        if(!tareaToUpdate.isPresent())
            return null;
        tareaToUpdate.get().setNombre(request.getNombre());
        tareaToUpdate.get().setDescripcion(request.getDescripcion());
        tareaToUpdate.get().setEstado(request.getEstado());
        tareaToUpdate.get().setFechaFinalizacion(request.getFechaFinalizacion());
        tareaRepository.save(tareaToUpdate.get());
        return tareaToUpdate;
    }
    /**
     * @param request la tarea nueva
     * @param id el id de la tarea a actualizar
     * @return la tarea actualizada
     * La funcion actualiza la tarea con el id especificado, para cambiar el estado de la tarea (metodo PATCH)
     */
    public Optional<TareaDTO> updateTareaEstadoById(TareaDTO request, long id){
        Optional<TareaDTO> tareaToUpdate = tareaRepository.findById(id);
        if(!tareaToUpdate.isPresent())
            return null;
        tareaToUpdate.get().setEstado(request.getEstado());
        tareaRepository.save(tareaToUpdate.get());
        return tareaToUpdate;
    }
    /**
     * @param id el id de la tarea a eliminar
     * @return true si la tarea fue eliminada, false si no
     */
    public boolean deleteTareaById(long id){
        try{
            tareaRepository.deleteById(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
