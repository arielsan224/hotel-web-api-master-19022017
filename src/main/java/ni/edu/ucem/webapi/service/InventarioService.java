package ni.edu.ucem.webapi.service;

import java.util.Date;
import ni.edu.ucem.webapi.modelo.CategoriaCuarto;
import ni.edu.ucem.webapi.modelo.Cuarto;
import ni.edu.ucem.webapi.modelo.Disponible;
import ni.edu.ucem.webapi.modelo.Reservacion;
import ni.edu.ucem.webapi.modelo.Pagina;
import ni.edu.ucem.webapi.modelo.Filtro;

public interface InventarioService 
{
    public void agregarCategoriaCuarto(final CategoriaCuarto pCategoriaCuarto);

    public void guardarCategoriaCuarto(final CategoriaCuarto pCategoriaCuarto);

    public void eliminarCategoriaCuarto(int id);
    
    public CategoriaCuarto obtenerCategoriaCuarto(final int id, final String... strings);

    public Pagina<CategoriaCuarto> obtenerTodosCategoriaCuartos(final Filtro paginacion);

    public void agregarCuarto(final Cuarto pCuarto);

    public void guardarCuarto(final Cuarto pCuarto);

    public void eliminarCuarto(final int pCuarto);

    public Cuarto obtenerCuarto(final int pId, final String... strings);

    public Pagina<Cuarto> obtenerTodosCuarto(final Filtro paginacion);

    public Pagina<Cuarto> obtenerTodosCuartoEnCategoria(final int pCategoriaCuarto, final Filtro paginacion);
    
    public void agregarReservacion(final Reservacion pReservacion);
    
    public Pagina<Reservacion> obtenerTodasReservacion(Filtro paginacion);
    
    public Reservacion obtenerReservacion(final int pId, final String... strings);
    
    public void guardarReservacion(final Reservacion pReservacion);
    
    public void eliminarReservacion(final int pId);
    
    public Disponible obtenerDisponible(Date desde, Date hasta);
    
    
    
}
