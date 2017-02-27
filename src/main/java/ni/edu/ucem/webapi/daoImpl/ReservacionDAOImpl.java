package ni.edu.ucem.webapi.daoImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ni.edu.ucem.webapi.dao.ReservacionDAO;
import ni.edu.ucem.webapi.modelo.Reservacion;

@Repository
public class ReservacionDAOImpl implements ReservacionDAO 
{
    private final JdbcTemplate jdbcTemplate;
   
    @Autowired
    public ReservacionDAOImpl(final JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Reservacion obtenerPorId(final int pId, final String... campos) 
    {
    	String fields = (campos != null && campos.length > 0 && campos[0] != null) ? campos[0] : "*";
        String sql = String.format("select %s from reservacion where id = ?", fields);
        return jdbcTemplate.queryForObject(sql, new Object[]{pId}, 
                new BeanPropertyRowMapper<Reservacion>(Reservacion.class));
    }  
    
    @Override
    public int contar()
    {
        final String sql = "select count(*) from reservacion";
        return this.jdbcTemplate.queryForObject(sql, Integer.class);
    }
    
    @Override
    public int contarPorReservacion(final int pReservacionId)
    {
        final String sql = "select count(*) from reservacion where ";
        return this.jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public List<Reservacion> obtenerTodos(final int pOffset, final int pLimit) 
    {
        String sql = "select * from reservacion offset ? limit ?";
        return this.jdbcTemplate.query(sql, new Object[]{pOffset, pLimit},
                new BeanPropertyRowMapper<Reservacion>(Reservacion.class));
    }

    @Override
    public List<Reservacion> obtenerTodosPorReservacionId(int pCategoriaId, int pOffset, int pLimit) 
    {
        final String sql = "select * from reservacion where cuarto = ? offset ? limit ?";
        return this.jdbcTemplate.query(sql, new Object[]{pCategoriaId, pOffset, pLimit},
                new BeanPropertyRowMapper<Reservacion>(Reservacion.class));
    }

    @Override
    public void agregar(final Reservacion pReservacion) 
    {
        final String sql = new StringBuilder()
                .append("INSERT INTO reservacion")
                .append(" ")
                .append("(desde, hasta, cuarto, huesped)")
                .append(" ")
                .append("VALUES (?, ?, ?, ?)")
                .toString();
        final Object[] parametros = new Object[3];
        parametros[0] = pReservacion.getDesde();
        parametros[1] = pReservacion.getHasta();
        parametros[2] = pReservacion.getCuarto();
        parametros[3] = pReservacion.getHuesped();
        this.jdbcTemplate.update(sql,parametros);
        
    }

    @Override
    public void guardar(final Reservacion pReservacion) 
    {        
       final String sql = new StringBuilder()
                .append("UPDATE reservacion")
                .append(" ")
                .append("set desde = ?")
                .append(",hasta = ?")
                .append(",cuarto = ?")
                .append(",huesped = ?")
                .append(" ")
                .append("where id = ?")
                .toString();
        final Object[] parametros = new Object[5];
        parametros[0] = pReservacion.getDesde();
        parametros[1] = pReservacion.getHasta();
        parametros[2] = pReservacion.getCuarto();
        parametros[2] = pReservacion.getHuesped();        
        parametros[4] = pReservacion.getId();
        this.jdbcTemplate.update(sql,parametros);
    }

    @Override
    public void eliminar(final int pId) 
    {
        final String sql = "delete from reservacion where id = ?";
        this.jdbcTemplate.update(sql, new Object[]{pId});
    }
}
