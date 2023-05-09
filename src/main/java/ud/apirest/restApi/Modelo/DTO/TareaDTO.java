package ud.apirest.restApi.Modelo.DTO;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
/**
 * Clase que representa la tabla tarea en la base de datos
 * Se usa la anotacion @Entity para indicar que es una entidad de la base de datos
 * Se usa la anotacion @Table para indicar el nombre de la tabla en la base de datos
 * Se usa la anotacion @Id para indicar que es la llave primaria de la tabla
 * Se usa la anotacion @GeneratedValue para indicar que el valor de la llave primaria es autoincremental
 * Se usa la anotacion @Column para indicar que es una columna de la tabla
 */
@Entity
@Table(name = "tarea")
public class TareaDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String nombre;
    @Column
    private String descripcion;
    @Column
    private String estado;
    @Column
    private Date fechaCreacion;
    @Column
    private Date fechaFinalizacion;
    

    public TareaDTO() {
    }
    public TareaDTO(long id, String nombre, String descripcion, String estado, Date fechaCreacion, Date fechaFinalizacion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaFinalizacion = fechaFinalizacion;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public Date getFechaCreacion() {
        return fechaCreacion;
    }
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }
    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }
    @Override
    public String toString() {
        return "TareaDTO [descripcion=" + descripcion + ", estado=" + estado + ", fechaCreacion=" + fechaCreacion
                + ", fechaFinalizacion=" + fechaFinalizacion + ", id=" + id + ", nombre=" + nombre + "]";
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof TareaDTO){
            TareaDTO tarea = (TareaDTO) obj;
            return this.id == tarea.getId();
        }
        return false;
    }
    
}
