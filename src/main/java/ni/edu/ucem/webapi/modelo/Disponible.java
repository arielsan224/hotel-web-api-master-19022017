package ni.edu.ucem.webapi.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(Include.NON_NULL)
public class Disponible 
{
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private Date desde;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private Date hasta;
	private List<Cuarto> cuarto;
    
    public Disponible(Date desde, Date hasta)
    {
    	this(desde, hasta, new ArrayList<Cuarto>());
    }
    
    public Disponible(Date desde, Date hasta,List<Cuarto> cuarto) {
        this.desde=desde;
        this.hasta= hasta;
        this.cuarto = cuarto;
        
        
    }

	public Date getDesde() {
		return desde;
	}

	public void setDesde(Date desde) {
		this.desde = desde;
	}

	public Date getHasta() {
		return hasta;
	}

	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}

	public List<Cuarto> getCuarto() {
		return cuarto;
	}

	public void setCuarto(List<Cuarto> cuarto) {
		this.cuarto = cuarto;
	}

	
    
        
}
