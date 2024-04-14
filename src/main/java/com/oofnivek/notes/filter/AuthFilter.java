package com.oofnivek.notes.filter;

import com.oofnivek.notes.NotesApplication;
import com.oofnivek.notes.exception.UnauthorizedException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AuthFilter implements Filter {

  String validKey = "abc123";
  private static final Logger logger = LoggerFactory.getLogger(NotesApplication.class);

  @Override
  public void destroy() {}

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain)
      throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;

    //    logger.info("Remote Host:" + request.getRemoteHost());
    //    logger.info("Remote Address:" + request.getRemoteAddr());
    String xApiKey = req.getHeader("X-API-KEY");
    logger.info(xApiKey);
    filterchain.doFilter(request, response);
    if (!xApiKey.equals(validKey)) {
      throw new UnauthorizedException(xApiKey);
    }
  }

  @Override
  public void init(FilterConfig filterconfig) throws ServletException {}
}
