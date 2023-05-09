package ud.apirest.restApi.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

import ud.apirest.restApi.Modelo.DTO.TareaDTO;

/**
*Interfaz que extiende de JpaRepository, para poder usar los metodos de JpaRepository
para acceder a la base de datos
JpaRepository usa generics, por lo que se le debe pasar el tipo de dato de la clase
y el tipo de dato del id de la clase
*/
public interface TareaIn extends JpaRepository<TareaDTO, Long> {}