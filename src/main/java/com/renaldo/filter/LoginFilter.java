package com.renaldo.filter;

import com.alibaba.fastjson.JSON;
import com.renaldo.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * check if user logged in
 */
@Slf4j
@WebFilter(filterName = "loginFilter", urlPatterns = "/*")
public class LoginFilter implements Filter {
    //path comparison
    private static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        //Get the URI of this request
        String requestURI = httpServletRequest.getRequestURI();

        //Define request paths that do not need to be processed
        String[] urls = new String[]{
                "/employees/login",
                "/employees/logout",
                "/back/**",
                "/front/**",
                "/common/**",
        };

        //Determine whether this request needs to be processed
        boolean check = check(urls, requestURI);

        //If no processing is required, let it go directly
        if (check) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        //Judging the login status, if logged in, let it go directly
        if (httpServletRequest.getSession().getAttribute("employee") != null) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        log.info("intercept request: {}", httpServletRequest.getRequestURI());


        //If not logged in, return the result of not logged in,
        //and respond data to the client page through the output stream
        httpServletResponse.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
    }

    /**
     * path match check
     *
     * @param requestURI
     * @return
     */
    private boolean check(String[] urls, String requestURI) {
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);

            if (match) {
                return true;
            }
        }

        return false;
    }
}
