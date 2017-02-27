select * from cuarto as ct inner join categoria_cuarto ctcat on ctcat .id = ct.categoria
where ct.id not in
(select  res.cuarto from reservacion as res
where res.desde<='2017-02-26' and res.hasta>='2017-02-26'
union all
select  res.cuarto from reservacion as res
where res.desde<='2017-02-28' and res.hasta>='2017-02-28');

select  * from reservacion as res
inner  join cuarto as ct on  ct.id =res.cuarto 
inner join categoria_cuarto ctcat on ctcat .id = ct.categoria
inner join huesped as hu on hu.id=res.huesped
