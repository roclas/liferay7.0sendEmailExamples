package com.roclas.modelListener;

import java.util.Map;

import org.osgi.framework.Constants;
import org.osgi.service.cm.ManagedServiceFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleServiceUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

@Component(
		immediate = true
		,property = Constants.SERVICE_PID +"=com.roclas.config.ModelListenersConfiguration"
		,service = {ModelListener.class, ManagedServiceFactory.class}
)
public class JAListener extends EmailNotificationModelListener<JournalArticle>{ 
	
	@Activate
    @Modified
    protected void activate(Map<String, Object> properties) {
		_log.info("activating JAListener"+properties);
    }


	@Override
	public void onBeforeUpdate(JournalArticle model) throws ModelListenerException {
		try {
			Boolean cnf = _emailOnDLEntryEnabled.get(model.getGroupId());
			_log.info("updating model with gId "+ model.getGroupId()+" and cnf ="+cnf);
			if(model.isExpired() && cnf){
					JournalArticle actualmodel=JournalArticleServiceUtil.getArticle(model.getId());
					if(actualmodel.isExpired())return;
					_log.info("expiring a JournalArticle and sending email..");
					String email = UserLocalServiceUtil.getUserById(model.getUserId()).getEmailAddress();
					sender.sendEmail(email);
			}else return;
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		//super.onBeforeUpdate(model);
	}
	
	private static final Log _log = LogFactoryUtil.getLog(JAListener.class);


	@Override
	protected String getAssetClassName() {
		return JournalArticle.class.getName();
	}

}
