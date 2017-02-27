package ni.edu.ucem.webapi.cache;

import javax.servlet.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;


@Configuration

public class EtagConfig {
	
	@Bean
	public Filter etagFilter(){
		return new ShallowEtagHeaderFilter();
	}

}
