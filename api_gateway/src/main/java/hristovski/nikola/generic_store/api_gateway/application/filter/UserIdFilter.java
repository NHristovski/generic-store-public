package hristovski.nikola.generic_store.api_gateway.application.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import hristovski.nikola.common.shared.domain.constants.Headers;
import hristovski.nikola.generic_store.api_gateway.application.configuration.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserIdFilter extends ZuulFilter {

    @Autowired
    private JwtConfig jwtConfig;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String header = request.getHeader(jwtConfig.getHeader());

        if (header == null || !header.startsWith(jwtConfig.getPrefix())) {
            return null;
        }

        String token = header.replace(jwtConfig.getPrefix(), "");

        // 4. Validate the token
        Claims claims = Jwts.parser()
                .setSigningKey(jwtConfig.getSecret().getBytes())
                .parseClaimsJws(token)
                .getBody();

        String userId = (String) claims.get(Headers.USER_ID);

        ctx.addZuulRequestHeader(Headers.USER_ID, userId);

        log.info("Added the userId {} as header", userId);

        return null;
    }
}

