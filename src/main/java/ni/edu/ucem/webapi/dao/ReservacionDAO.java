package ni.edu.ucem.webapi.dao;

import java.util.List;

import ni.edu.ucem.webapi.modelo.Reservacion;

public interface ReservacionDAO 
{
    public Reservacion obtenerPorId(final int pId, final String... proyeccion);
    
    public int contar();
    
    public int contarPorReservacion(final int pReservacionId);
    
    public List<Reservacion> obtenerTodos(final int offset, final int limit);

    public List<Reservacion> obtenerTodosPorReservacionId(final int pReservacionId, final int offset, final int limit);

    public void agregar(final Reservacion pReservacion);

    public void guardar(final Reservacion pReservacion);

    public void eliminar(final int pId);
}
