package com.roclas.modelListener;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition( 
		category = "productivity" 
		,factoryInstanceLabelAttribute = "groupId"
)
@Meta.OCD(
		id = "com.roclas.config.ModelListenersConfiguration",
		localization = "content/Language", 
		name = "modellistenerconfigurationByGroupId",
		factory = true
)
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

    @Meta.AD(
        deflt = "0" ,
        required =true,
        name="groupId"
    )
    public String groupId();

}