package ni.edu.ucem.webapi.dao;

import java.util.Date;
import ni.edu.ucem.webapi.modelo.Disponible;

public interface DisponibleDAO 
{
        
    
    public Disponible obtenerDisponible(Date desde, Date hasta);
    
}
