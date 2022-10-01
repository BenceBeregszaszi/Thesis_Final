package ekke.spring.configuration.Filter;

import ekke.spring.dto.UserDto;
import ekke.spring.service.authentication.AuthenticatedUser;
import ekke.spring.service.authentication.JwtService;
import ekke.spring.validators.JwtValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private AuthorizationHeaderValidator authorizationHeaderValidator;
    private JwtValidator jwtValidator;
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            authorizationHeaderValidator.validate(authorizationHeader);

            final String jwt = authorizationHeader.split(" ")[1].trim();
            jwtValidator.validate(jwt);
            AuthenticatedUser  authenticatedUser= jwtService.getUser(jwt, AuthenticatedUser.class);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(authenticatedUser, jwt, authenticatedUser.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } catch (Exception e){
            logger.warn(String.format("Jwt filter error: %s", e.getMessage()));
        }
        filterChain.doFilter(request, response);
    }
}
