package ni.edu.ucem.webapi.web.inventario;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import ni.edu.ucem.webapi.core.ApiResponse;
import ni.edu.ucem.webapi.core.ApiResponse.Status;
import ni.edu.ucem.webapi.core.ListApiResponse;
import ni.edu.ucem.webapi.modelo.Reservacion;
import ni.edu.ucem.webapi.modelo.Pagina;
import ni.edu.ucem.webapi.modelo.Filtro;
import ni.edu.ucem.webapi.serviceImpl.InventarioServiceImpl;

@RestController
@RequestMapping("/v1/inventario/reservaciones")
public class ReservacionResource 
{
    private final InventarioServiceImpl inventarioService;
    
    @Autowired
    public ReservacionResource(final InventarioServiceImpl inventarioService)
    {
        this.inventarioService = inventarioService;
    }

    @RequestMapping(method = RequestMethod.GET, produces="application/json")
    public ListApiResponse<Reservacion> obtenerservaciones(
            @RequestParam(value = "offset", required = false, defaultValue ="0") final Integer offset,
            @RequestParam(value = "limit", required = false, defaultValue="0") final Integer limit)
    {
        final Filtro paginacion = new Filtro.Builder()
                .paginacion(offset, limit)
                .build();

        Pagina<Reservacion> pagina;
        pagina = this.inventarioService.obtenerTodasReservacion(paginacion);
        return new ListApiResponse<Reservacion>(Status.OK, pagina);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces="application/json")
    public ApiResponse obtener(
    		@PathVariable("id") final int id, 
    		@RequestParam(value = "fields", required = false) final String campos)
    {
    	final Reservacion reservacion = this.inventarioService.obtenerReservacion(id, campos);
        return new ApiResponse(Status.OK, reservacion);
    }
    
    
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST,
            produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse guardarReservacion(@Valid @RequestBody final Reservacion reservacion, BindingResult result) 
    {
        if(result.hasErrors())
        {
            throw new IllegalArgumentException(result.getFieldError().getDefaultMessage());
        }
        
        this.inventarioService.agregarReservacion(reservacion);
        return new ApiResponse(Status.OK, reservacion);
    }
    
    
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse guardarReservacionConFormData(final Date desde, final Date hasta, final Integer cuarto, final Integer huesped) 
    {
    	Reservacion reservacion = new Reservacion(desde, hasta, cuarto, huesped);
        this.inventarioService.agregarReservacion(reservacion);
        return new ApiResponse(Status.OK, reservacion);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT,
            produces="application/json")
    public ApiResponse guardarReservacion(@PathVariable("id") final int id, 
            @RequestBody final Reservacion reservacionActualizada) 
    {
        final Reservacion reservacion = new Reservacion(id,
        		reservacionActualizada.getDesde(), 
        		reservacionActualizada.getHasta(),
        		reservacionActualizada.getCuarto(),
        		reservacionActualizada.getHuesped());
        this.inventarioService.guardarReservacion(reservacion);
        return new ApiResponse(Status.OK, reservacion);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE,
            produces="application/json")
    public ApiResponse eliminarCuarto(@PathVariable("id") final int id) 
    {
        final Reservacion reservacion = this.inventarioService.obtenerReservacion(id);
        this.inventarioService.eliminarCuarto(reservacion.getId());
        return new ApiResponse(Status.OK,null);
    }
}
