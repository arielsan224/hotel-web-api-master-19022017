package ni.edu.ucem.webapi.web.inventario;

import java.math.BigDecimal;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import ni.edu.ucem.webapi.modelo.CategoriaCuarto;
import ni.edu.ucem.webapi.modelo.Filtro;
import ni.edu.ucem.webapi.modelo.Pagina;
import ni.edu.ucem.webapi.serviceImpl.InventarioServiceImpl;

@RestController	
@RequestMapping ("/v1/inventario/categorias")

public class CategoriaResource {
	
	private final InventarioServiceImpl inventarioService;
	
	@Autowired
    public CategoriaResource(final InventarioServiceImpl inventarioService)
    {
        this.inventarioService = inventarioService;
    }
    
    @RequestMapping(method = RequestMethod.GET, produces="application/json")
    public ListApiResponse<CategoriaCuarto> obtenerTodosCategoriaCuartos(
            @RequestParam(value = "offset", required = false, defaultValue ="0") final Integer offset,
            @RequestParam(value = "limit", required = false, defaultValue="0") final Integer limit)
    {
        final Filtro paginacion = new Filtro.Builder()
                .paginacion(offset, limit)
                .build();

        Pagina<CategoriaCuarto> pagina;
        pagina = this.inventarioService.obtenerTodosCategoriaCuartos(paginacion);
        return new ListApiResponse<CategoriaCuarto>(Status.OK, pagina);
        
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces="application/json")
    public ApiResponse obtener(
    		@PathVariable("id") final int id, 
    		@RequestParam(value = "fields", required = false) final String campos)
    {
    	final CategoriaCuarto categoriacuarto = this.inventarioService.obtenerCategoriaCuarto(id, campos);
        return new ApiResponse(Status.OK, categoriacuarto);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST,
            produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse guardarCategoriaCuarto(@Valid @RequestBody final CategoriaCuarto categoriacuarto, BindingResult result) 
    {
        if(result.hasErrors())
        {
            throw new IllegalArgumentException(result.getFieldError().getDefaultMessage());
        }
        
        this.inventarioService.agregarCategoriaCuarto(categoriacuarto);
        return new ApiResponse(Status.OK, categoriacuarto);
    }
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse guardarCategoriaCuartoConFormData(final String nombre, final String descripcion, final BigDecimal precio) 
    {
        CategoriaCuarto categoriacuarto = new CategoriaCuarto(nombre, descripcion, precio);
        this.inventarioService.agregarCategoriaCuarto(categoriacuarto);
        return new ApiResponse(Status.OK, categoriacuarto);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT,
            produces="application/json")
    public ApiResponse guardarCategoriaCuarto(@PathVariable("id") final int id, 
            @RequestBody final CategoriaCuarto categoriaActualizada) 
    {
        final CategoriaCuarto categoriacuarto = new CategoriaCuarto(id,
        		categoriaActualizada.getNombre(), 
        		categoriaActualizada.getDescripcion(),
        		categoriaActualizada.getPrecio());
        this.inventarioService.guardarCategoriaCuarto(categoriacuarto);
        return new ApiResponse(Status.OK, categoriacuarto);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE,
            produces="application/json")
    public ApiResponse eliminarCategoriaCuarto(@PathVariable("id") final int id) 
    {
        final CategoriaCuarto categoriacuarto = this.inventarioService.obtenerCategoriaCuarto(id);
        this.inventarioService.eliminarCategoriaCuarto(categoriacuarto.getId());
        return new ApiResponse(Status.OK,null);
    }


}
