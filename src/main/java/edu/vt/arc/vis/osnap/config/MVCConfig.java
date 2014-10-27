package edu.vt.arc.vis.osnap.config;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;


/**
 * Spring Web MVC configuration.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * @since 0.1
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "edu.vt.arc.vis.osnap.rest.controllers" })
public class MVCConfig
        extends WebMvcConfigurerAdapter {

    private static Logger LOG = LoggerFactory.getLogger(MVCConfig.class);


    @Override
    public void configureMessageConverters(
            List<HttpMessageConverter<?>> converters) {

        LOG.debug("Configuring message converters!");
        super.configureMessageConverters(converters);

        converters.add(0, mappingJacksonHttpMessageConverter(objectMapper()));
    }


    /**
     * Returns a Jackson ObjectMapper with a custom Modules.
     * 
     * @return a Jackson ObjectMapper with a custom Modules.
     */
    @Bean
    public ObjectMapper objectMapper() {

        ObjectMapper mapper = new ObjectMapper();

        mapper.registerModule(new JSR310Module());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        return mapper;
    }


    /**
     * Returns a MappingJackson2HttpMessageConverter with a custom
     * {@link ObjectMapper}.
     * 
     * @param objectMapper
     *            the custom {@link ObjectMapper}.
     * @return a MappingJackson2HttpMessageConverter with a custom
     *         {@link ObjectMapper}.
     */
    @Bean
    public MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter(
            ObjectMapper objectMapper) {

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

        converter.setObjectMapper(objectMapper);

        return converter;

    }
}
