package ud.apirest.restApi.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

import ud.apirest.restApi.Modelo.DTO.TareaDTO;


public interface TareaIn extends JpaRepository<TareaDTO, Long> {
    
}