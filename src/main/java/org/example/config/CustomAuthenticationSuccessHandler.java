package org.example.config;

import lombok.extern.log4j.Log4j2;
import org.example.model.SessionEntity;
import org.example.repository.SessionRepository;
import org.example.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

@Log4j2
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    final
    SessionRepository sessionRepository;
    final
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    public CustomAuthenticationSuccessHandler(SessionRepository sessionRepository, UserDetailsServiceImpl userDetailsService) {
        this.sessionRepository = sessionRepository;
        this.userDetailsService = userDetailsService;
    }
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("СРАБОТАЛО!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        String username = authentication.getName();
        String ipAddress = request.getRemoteAddr();
        String device = request.getHeader("User-Agent");
        Date loginTime = new Date();
        SessionEntity loginHistory = new SessionEntity();
        loginHistory.setTime(loginTime);
        loginHistory.setDevice(device);
        loginHistory.setIpAddress(ipAddress);
        loginHistory.setUser(userDetailsService.findByLog(username).get());
        sessionRepository.save(loginHistory);
        response.sendRedirect("/SpaceLab_2_1-1.0-SNAPSHOT/admin/");
    }
}
