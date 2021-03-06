package org.majorxie.dreamvc.mvc.dispatcher;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.majorxie.dreamvc.tag.Contextconfig.StrategyContext;
/**
 * dispatcher
 * @author xiezhaodong
 * 2014-10-28
 *
 */
public class DispatcherServlet extends GenericServlet {
	private final Log log=LogFactory.getLog(getClass());	
	
	private Dispatcher dispatcher;
	
	
	public void destroy() {
		log.info("distory!!");
		super.destroy(); 
	}
	/**
	 * 同filter一样，策略识别初始化
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.dispatcher=new Dispatcher();
		StrategyContext configImpl=new StrategyContext(config); 
		dispatcher.init(configImpl);
		
		
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
		HttpServletRequest req=(HttpServletRequest) arg0;
		HttpServletResponse resp=(HttpServletResponse) arg1;
		String method=req.getMethod();
		 if ("GET".equals(method) || "POST".equals(method)) {
			 if(!dispatcher.service(req, resp)){		
				 return;
			 }
		 }
		
		
	}

	
	
}
