package com.smarttech.interceptor;

import com.smarttech.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CommonInterceptor implements HandlerInterceptor {

    private final CartService cartService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (Objects.nonNull(modelAndView)) {
            request.setAttribute("currentUri", request.getServletPath());
            request.setAttribute("uriWithQueryString", this.getUriWithParameters(request));
            this.setCartSize(request);
        }
    }

    private void setCartSize(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            request.setAttribute("cartSize", 0);
            return;
        }
        request.setAttribute("cartSize", cartService.getMyCart().size());
    }

    private String getUriWithParameters(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        return request.getRequestURI() + "?" + parameterMap.keySet().stream()
                .filter(key -> !"page".equals(key))
                .map(key -> key + "=" + String.join(",", parameterMap.get(key)))
                .collect(Collectors.joining("&"));
    }
}
