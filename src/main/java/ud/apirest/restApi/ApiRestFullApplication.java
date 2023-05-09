package ud.apirest.restApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * Clase principal de la aplicacion
 * @SpringBootApplication: anotacion que indica que la clase es la clase principal de la aplicacion
 */
@SpringBootApplication
public class ApiRestFullApplication {
	/**
	 * Metodo principal de la aplicacion
	 * @param args: argumentos del metodo principal
	 */
	public static void main(String[] args) {
		SpringApplication.run(ApiRestFullApplication.class, args);
	}

}
