package com.roclas.modelListener;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.settings.definition.ConfigurationBeanDeclaration;

@Component
public class ModelListenersConfigurationBeanDeclaration
    implements ConfigurationBeanDeclaration {

    @Override
    public Class getConfigurationBeanClass() {
        return ModelListenersConfiguration.class;
    }

}