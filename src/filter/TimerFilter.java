package filter;

import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class TimerFilter implements Filter {

    public void destroy() {
	}
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		long inicio = System.currentTimeMillis();
		chain.doFilter(request, response);
		long fim = System.currentTimeMillis();
		long tempo = fim - inicio;
		
		FileWriter fw = new FileWriter("c:/TEMP/time.txt", true);
		fw.write("URL:" +req.getRequestURI()+" : "+tempo+" ms");
		fw.write(System.getProperty("line.separator"));
		fw.close();
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
