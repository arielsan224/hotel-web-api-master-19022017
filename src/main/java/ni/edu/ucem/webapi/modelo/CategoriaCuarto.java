package ni.edu.ucem.webapi.modelo;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(Include.NON_NULL)
public class CategoriaCuarto 
{
    private Integer id;
	
	@NotNull
    @NotEmpty(message = "El nombre es requerido.")
    @Pattern(regexp = "^[\\w ]+$")
    private String nombre;
	
	@NotNull
    @NotEmpty(message = "La descripción es requerida.")
    @Pattern(regexp = "^[\\w ]+$")
    private String descripcion;
	
	@NotNull
    @NotEmpty(message = "El precio es requerido.")
    private BigDecimal precio;
    
	public CategoriaCuarto()
    {
    } 
	
    public CategoriaCuarto(final String nombre, 
            final String descripcion,final BigDecimal precio) 
    {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }
    
    public CategoriaCuarto(final Integer id ,final String nombre, 
            final String descripcion,final BigDecimal precio) 
    {
    	this.id=id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
 
    
}
