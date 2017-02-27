package ni.edu.ucem.webapi.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ni.edu.ucem.webapi.dao.CategoriaCuartoDAO;
import ni.edu.ucem.webapi.dao.CuartoDAO;
import ni.edu.ucem.webapi.dao.DisponibleDAO;
import ni.edu.ucem.webapi.dao.ReservacionDAO;
import ni.edu.ucem.webapi.modelo.CategoriaCuarto;
import ni.edu.ucem.webapi.modelo.Cuarto;
import ni.edu.ucem.webapi.modelo.Disponible;
import ni.edu.ucem.webapi.modelo.Pagina;
import ni.edu.ucem.webapi.modelo.Reservacion;
import ni.edu.ucem.webapi.modelo.Filtro;
import ni.edu.ucem.webapi.service.InventarioService;

@Service
public class InventarioServiceImpl implements InventarioService 
{
    private final CategoriaCuartoDAO categoriaCuartoDAO;
    private final CuartoDAO cuartoDAO;
	private final ReservacionDAO reservacionDAO;
	private final DisponibleDAO disponibleDAO;
    
    public InventarioServiceImpl(final CategoriaCuartoDAO categoriaCuartoDAO,
            final CuartoDAO cuartoDAO, final ReservacionDAO reservacionDAO, DisponibleDAO disponibleDAO)
    {
        this.categoriaCuartoDAO = categoriaCuartoDAO;
        this.cuartoDAO = cuartoDAO;
        this.reservacionDAO =reservacionDAO;
        this.disponibleDAO = disponibleDAO;
    }
    @Transactional
    @Override
    public void agregarCategoriaCuarto(final CategoriaCuarto pCategoriaCuarto) 
    {
        this.categoriaCuartoDAO.agregar(pCategoriaCuarto);
    }

    @Transactional
    @Override
    public void guardarCategoriaCuarto(final CategoriaCuarto pCategoriaCuarto) 
    {
        if(pCategoriaCuarto.getId() < 1)
        {
            throw new IllegalArgumentException("La categoría del cuarto no existe");
        }
        this.categoriaCuartoDAO.guardar(pCategoriaCuarto);
    }

    @Transactional
    @Override
    public void eliminarCategoriaCuarto(final int pId) 
    {
        if(pId < 1)
        {
            throw new IllegalArgumentException("ID invalido. Debe ser mayor a cero");
        }
        this.categoriaCuartoDAO.eliminar(pId);
    }

    @Override
    public CategoriaCuarto obtenerCategoriaCuarto(final int pId, final String... strings) 
    {
        return this.categoriaCuartoDAO.obtenerPorId(pId);
    }

    @Override
    public Pagina<CategoriaCuarto> obtenerTodosCategoriaCuartos(Filtro paginacion) 
    {
        List<CategoriaCuarto> categoriacuarto;
        final int count = this.categoriaCuartoDAO.contar();
        if(count > 0)
        {
        	categoriacuarto = this.categoriaCuartoDAO.obtenerTodos(paginacion.getOffset(),
                    paginacion.getLimit());
        }
        else
        {
        	categoriacuarto = new ArrayList<CategoriaCuarto>();
        }
        return new Pagina<CategoriaCuarto>(categoriacuarto, count,  paginacion.getOffset(), paginacion.getLimit());
    }
    
    @Transactional
    @Override
    public void agregarCuarto(final Cuarto pCuarto) 
    {
        this.cuartoDAO.agregar(pCuarto);

    }
    
    @Transactional
    @Override
    public void guardarCuarto(final Cuarto pCuarto) 
    {
        if(pCuarto.getId() < 1)
        {
            throw new IllegalArgumentException("El cuarto no existe");
        }
        this.cuartoDAO.guardar(pCuarto);
    }
    
    @Transactional
    @Override
    public void eliminarCuarto(final int pId) 
    {
        if(pId < 1)
        {
            throw new IllegalArgumentException("ID invalido. Debe ser mayor a cero");
        }
        this.cuartoDAO.eliminar(pId);
    }

    @Override
    public Cuarto obtenerCuarto(final int pId, final String... strings) 
    {
        if (pId < 0) 
        {
            throw new IllegalArgumentException("ID inválido. debe ser mayor a cero.");
        }
        return this.cuartoDAO.obtenerPorId(pId, strings); 
    }

    @Override
    public Pagina<Cuarto> obtenerTodosCuarto(Filtro paginacion) 
    {
        List<Cuarto> cuartos;
        final int count = this.cuartoDAO.contar();
        if(count > 0)
        {
            cuartos = this.cuartoDAO.obtenerTodos(paginacion.getOffset(),
                    paginacion.getLimit());
        }
        else
        {
            cuartos = new ArrayList<Cuarto>();
        }
        return new Pagina<Cuarto>(cuartos, count,  paginacion.getOffset(), paginacion.getLimit());
    }

    @Override
    public Pagina<Cuarto> obtenerTodosCuartoEnCategoria(final int pCategoriaCuartoId, final Filtro paginacion)
    {
        final int count = this.cuartoDAO.contarPorCategoria(pCategoriaCuartoId);
        List<Cuarto> cuartos = null;
        if(count > 0)
        {
            cuartos = this.cuartoDAO.obtenerTodosPorCategoriaId(pCategoriaCuartoId, paginacion.getOffset(),
                    paginacion.getLimit());
        }
        else
        {
            cuartos = new ArrayList<Cuarto>();
        }
        return new Pagina<Cuarto>(cuartos, count,  paginacion.getOffset(), paginacion.getLimit());
    }
	public void agregarReservacion(final Reservacion pReservacion) {
		this.reservacionDAO.agregar(pReservacion);
		
	}
	public Pagina<Reservacion> obtenerTodasReservacion(Filtro paginacion) {
		List<Reservacion> reservacion;
        final int count = this.reservacionDAO.contar();
        if(count > 0)
        {
        	reservacion = this.reservacionDAO.obtenerTodos(paginacion.getOffset(),
                    paginacion.getLimit());
        }
        else
        {
            reservacion = new ArrayList<Reservacion>();
        }
        return new Pagina<Reservacion>(reservacion, count,  paginacion.getOffset(), paginacion.getLimit());
	}
	public Reservacion obtenerReservacion(final int pId, final String... strings) {
		if (pId < 0) 
        {
            throw new IllegalArgumentException("ID inválido. debe ser mayor a cero.");
        }
        return this.reservacionDAO.obtenerPorId(pId, strings); 
	}
	
	public void guardarReservacion(final Reservacion pReservacion) 
    {
        if(pReservacion.getId() < 1)
        {
            throw new IllegalArgumentException("El cuarto no existe");
        }
        this.reservacionDAO.guardar(pReservacion);
    }
	
	public void eliminarReservacion(final int pId) 
    {
        if(pId < 1)
        {
            throw new IllegalArgumentException("ID invalido. Debe ser mayor a cero");
        }
        this.reservacionDAO.eliminar(pId);
    }
	@Override
	public Disponible obtenerDisponible(Date desde, Date hasta) {
		// TODO Auto-generated method stub
		return this.disponibleDAO.obtenerDisponible(desde, hasta);
		
	}
	
}
