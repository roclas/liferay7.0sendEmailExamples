package com.roclas.modelListener;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition( category = "productivity", scope = ExtendedObjectClassDefinition.Scope.GROUP)
@Meta.OCD(id = "com.roclas.config.ModelListenersConfiguration", localization = "content/Language", name = "%com.roclas.config.modellistenerconfiguration")
public interface ModelListenersConfiguration {

    @Meta.AD(
        deflt = "false" ,
        required =false
    )
    public Boolean emailOnJournalArticleExpiration();

    @Meta.AD(
        deflt = "false" ,
        required =false
    )
    public Boolean emailOnDLEntryExpiration();




}