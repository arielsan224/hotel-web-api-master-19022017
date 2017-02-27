package ni.edu.ucem.webapi.daoImpl;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ni.edu.ucem.webapi.dao.DisponibleDAO;
import ni.edu.ucem.webapi.modelo.Cuarto;
import ni.edu.ucem.webapi.modelo.Disponible;


@Repository
public class DisponibleDAOImpl implements DisponibleDAO 
{
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
   
    @Autowired
    public DisponibleDAOImpl(final NamedParameterJdbcTemplate namedParameterJdbcTemplate)
    {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Disponible obtenerDisponible(Date desde, Date hasta){
    	Disponible disponible = new Disponible(desde, hasta);
    	 String sql = "select ct.* from cuarto as ct left join (%s) as res on ct.id=res.cuarto where res.cuarto is null";
    	 		sql = String.format(sql, "select cuarto from reservacion where desde between :pdesde and :phasta or hasta between :pdesde and :phasta");
    	 
    	 Map<String,Object> namedParameters  = new HashMap<String,Object>();
    	 namedParameters.put("pdesde", desde);
    	 namedParameters.put("phasta", hasta);
    	 
    	 List<Cuarto> cuarto = this.namedParameterJdbcTemplate.query(sql, namedParameters, new BeanPropertyRowMapper<Cuarto>(Cuarto.class));
    	 disponible.setCuarto(cuarto);
    	 return disponible;
    }

	
   
/*
    @Override
    public List<Disponible> obtenerTodos(final Date desde, final Date hasta) 
    {
        String sql = "select * from cuarto as ct inner join categoria_cuarto ctcat on ctcat .id = ct.categoria" +
					"	where ct.id not in"+
					"	(select  res.cuarto from reservacion as res"+
					"	where res.desde<=:pdesde and res.hasta>=:pdesde"+
					"	union all"+
					"	select  res.cuarto from reservacion as res"+
					"	where res.desde<=:phasta and res.hasta>=:phasta); ";
        return this.jdbcTemplate.query(sql, new Object[],
                new BeanPropertyRowMapper<Disponible>(Disponible.class));
        Map<String,Object> namedParameters  = new HashMap<String,Object>();
    	namedParameters.put("pdesde", desde);
        namedParameters.put("phasta", hasta);
    	 
    	 /*List<Cuarto> cuarto = this.namedParameterJdbcTemplate.query(sql, namedParameters, new BeanPropertyRowMapper<Cuarto>(Cuarto.class));
    	 disponible.setCuarto(cuarto);
    	 return disponible;
    	 
    	 return this.namedParameterJdbcTemplate.query(sql, nnamedParameters, new BeanPropertyRowMapper<Cuarto>(Cuarto.class));
    }
*/
    
}
