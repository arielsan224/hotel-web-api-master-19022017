package ni.edu.ucem.webapi.web.inventario;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import ni.edu.ucem.webapi.core.ApiResponse;
import ni.edu.ucem.webapi.core.ApiResponse.Status;
import ni.edu.ucem.webapi.modelo.Disponible;
import ni.edu.ucem.webapi.serviceImpl.InventarioServiceImpl;

@RestController
@RequestMapping("/v1/inventario/disponibilidad/cupos")
public class DisponibleResource 
{
    private final InventarioServiceImpl inventarioService;
    
    @Autowired
    public DisponibleResource(final InventarioServiceImpl inventarioService)
    {
        this.inventarioService = inventarioService;
    }

    @RequestMapping(method = RequestMethod.GET, produces="application/json")
        	public ApiResponse disponible(
        			@RequestParam(value = "desde", required = true) @DateTimeFormat(pattern = "dd/MM/yyyy") final Date desde,
        			@RequestParam(value = "hasta", required = true) @DateTimeFormat(pattern = "dd/MM/yyyy") final Date hasta) {
            	Disponible disponible = this.inventarioService.obtenerDisponible(desde, hasta);
        		return new ApiResponse(Status.OK, disponible);
        	}
    
   
}
