package springboot.angular.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;
import springboot.angular.Constants;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)  response;

        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        String[] authorizationHeaderArr = (authorizationHeader == null)? new String[]{} : authorizationHeader.split("Bearer ");

        if(authorizationHeader == null){
            httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "Authorization header must be provided");
        }

        if(authorizationHeaderArr.length <= 1){
            System.out.println("Single Length");
            System.out.println(authorizationHeader);
            httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "Authorization token must be 'Bearer [token]'");
        }


        try{
            String token = authorizationHeaderArr[1];
            Claims claims = Jwts.parser().setSigningKey(Constants.API_SECRET_KEY).parseClaimsJws(token).getBody();

            httpServletRequest.setAttribute("id", claims.get("id"));
            httpServletRequest.setAttribute("username", claims.get("username"));

        }catch(Exception exc) {
            httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "Invalid or Expired Token");
        }

        chain.doFilter(httpServletRequest, httpServletResponse);

    }
}
