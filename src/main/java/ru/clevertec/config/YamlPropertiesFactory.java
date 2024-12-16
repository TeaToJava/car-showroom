package ru.clevertec.config;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.IOException;
import java.util.Properties;

public class YamlPropertiesFactory implements PropertySourceFactory {
    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        if (resource.getResource() == null) {
            throw new IOException("Resource Not Found");
        }
        YamlPropertiesFactoryBean propertiesFactoryBean = new YamlPropertiesFactoryBean();
        propertiesFactoryBean.setResources(resource.getResource());
        propertiesFactoryBean.afterPropertiesSet();
        Properties props = propertiesFactoryBean.getObject();
        return new PropertiesPropertySource(resource.getResource().getFilename(), props);
    }
}
