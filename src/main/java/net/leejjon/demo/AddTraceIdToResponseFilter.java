package net.leejjon.demo;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Component
public class AddTraceIdToResponseFilter extends GenericFilterBean {
    private Tracer tracer;

    @Autowired
    public AddTraceIdToResponseFilter(Tracer tracer) {
        this.tracer = tracer;
    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {
        if (response instanceof HttpServletResponse) {
            String traceId = tracer.currentSpan().context().traceId();
            HttpServletResponse res = (HttpServletResponse) response;
            res.addHeader("LEONS-TRACE-ID", traceId);
        }
        chain.doFilter(request, response);
    }
}
