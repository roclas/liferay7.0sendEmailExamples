package com.roclas.modelListener;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleServiceUtil;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.roclas.mail.MailSender;



@Component(immediate = true, service = ModelListener.class, configurationPid = "com.roclas.config.ModelListenersConfiguration",configurationPolicy = ConfigurationPolicy.OPTIONAL)
public class JAListener extends BaseModelListener<JournalArticle>{ 
	MailSender sender=new MailSender();
	
	@Activate
    @Modified
    protected void activate(Map<String, Object> properties) {
		_log.info("loading configuration for the model listeners");
        _configuration = ConfigurableUtil.createConfigurable(ModelListenersConfiguration.class, properties);
    }
    private volatile ModelListenersConfiguration _configuration;


	@Override
	public void onBeforeUpdate(JournalArticle model) throws ModelListenerException {
		if(model.isExpired() && _configuration.emailOnJournalArticleExpiration()){
			try {
				JournalArticle actualmodel=JournalArticleServiceUtil.getArticle(model.getId());
				if(actualmodel.isExpired())return;
				_log.info("expiring a JournalArticle");
				String email = UserLocalServiceUtil.getUserById(model.getUserId()).getEmailAddress();
				sender.sendEmail(email);
			} catch (Exception e) {
				e.printStackTrace();
				return;
			} 
		}else return;
		super.onBeforeUpdate(model);
	}
	
	private static final Log _log = LogFactoryUtil.getLog(JAListener.class);

}
