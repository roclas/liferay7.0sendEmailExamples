package com.roclas.modelListener;

import java.util.Dictionary;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.cm.ManagedServiceFactory;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.util.GetterUtil;
import com.roclas.mail.MailSender;

public abstract class EmailNotificationModelListener<T> implements ModelListener<T>, ManagedServiceFactory {

	MailSender sender=new MailSender();

	@Override
	public void deleted(String factoryPid) {
		long groupId = _factoryPidsToGroupId.remove(factoryPid);
		System.out.println("deleting conf: " +factoryPid+ " "+groupId);
		_factoryPidsToGroupId.remove(groupId);
		_emailOnJournalArticleEnabled.remove(groupId);
		_emailOnDLEntryEnabled.remove(groupId);
	}

	@Override
	public void updated(String factoryPid, Dictionary<String, ?> dictionary) {
		//Enumeration<String> confkeys = dictionary.keys();
		System.out.println("updating conf");
		long groupId = GetterUtil.getLong(dictionary.get("groupId"));
		Boolean emailOnJournalArticleExpiration= GetterUtil.getBoolean(dictionary.get("emailOnJournalArticleExpiration"));
		Boolean emailOnDLEntryExpiration= GetterUtil.getBoolean(dictionary.get("emailOnDLEntryExpiration"));

		_factoryPidsToGroupId.put(factoryPid, groupId);
		_emailOnJournalArticleEnabled.put(groupId, emailOnJournalArticleExpiration);
		_emailOnDLEntryEnabled.put(groupId, emailOnDLEntryExpiration);
	}

	@Override
	public String getName() {
		return getAssetClassName();
	}


	protected Map<Long, Boolean> _emailOnDLEntryEnabled = new ConcurrentHashMap<>();
	protected Map<Long, Boolean> _emailOnJournalArticleEnabled = new ConcurrentHashMap<>();
	private Map<String, Long> _factoryPidsToGroupId = new ConcurrentHashMap();
	
		@Override
	public void onAfterAddAssociation(Object classPK, String associationClassName, Object associationClassPK)
			throws ModelListenerException {
		System.out.println("onAfterAssociation "+associationClassName);
	}

	@Override
	public void onAfterCreate(T model) throws ModelListenerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAfterRemove(T model) throws ModelListenerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAfterRemoveAssociation(Object classPK, String associationClassName, Object associationClassPK)
			throws ModelListenerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAfterUpdate(T model) throws ModelListenerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBeforeAddAssociation(Object classPK, String associationClassName, Object associationClassPK)
			throws ModelListenerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBeforeCreate(T model) throws ModelListenerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBeforeRemove(T model) throws ModelListenerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBeforeRemoveAssociation(Object classPK, String associationClassName, Object associationClassPK)
			throws ModelListenerException {
		// TODO Auto-generated method stub
		
	}


	abstract protected String getAssetClassName();


}