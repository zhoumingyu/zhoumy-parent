package top.zhoumy.common.handler;

import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class UrlLogInterceptor implements HandlerInterceptor {
	Logger logger = LoggerFactory.getLogger(UrlLogInterceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(arg0.getRequestURI());
		sb.append("====>");
		if (arg0.getParameterMap() != null && !arg0.getParameterMap().isEmpty())
			for (Entry<String, String[]> itemKey : arg0.getParameterMap().entrySet()) {
				sb.append("key:" + itemKey.getKey());
				sb.append("value:");
				for (String item : itemKey.getValue()) {
					sb.append(item);
				}
			}
		logger.info(sb.toString());
		return true;
	}

}
