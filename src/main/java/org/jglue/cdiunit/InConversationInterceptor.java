package org.jglue.cdiunit;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpServletRequest;

public class InConversationInterceptor {
	@Inject
	private ContextController _contextController;

	@Inject
	private Provider<HttpServletRequest> _requestProvider;

	@AroundInvoke
	public Object around(InvocationContext ctx) throws Exception {
		try {
			_contextController.openConversation(_requestProvider.get());
			return ctx.proceed();
		} finally {
			_contextController.closeConversation();
		}
	}
}