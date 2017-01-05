package com.mr.media.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mr.media.model.User;
import com.mr.media.response.BaseResp;
import com.mr.media.service.UserService;
import org.json.JSONObject;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Collectors;

/**
 * 全局拦截器，检查令牌
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

    UserService userService;

    public TokenInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getParameter("token");
        if(StringUtils.isEmpty(token)){
            // 到body里面寻找
            try {
                String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
                JSONObject json = new JSONObject(body);
                token = json.getString("token");
            }catch (Exception e){
                token = "";
            }

        }
        User user = userService.findUserByToken(token);
        if(user == null){
            BaseResp content = new BaseResp(BaseResp.WRONT_TOKEN);
            writeResponse(content, response);
            return false;
        }
        if(user.getDisable() == 1){
            BaseResp content = new BaseResp(BaseResp.USER_DEACTIVE);
            writeResponse(content, response);
            return false;
        }

        return super.preHandle(request, response, handler);
    }

    void writeResponse(BaseResp content, HttpServletResponse response) throws Exception {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String str = new ObjectMapper().writeValueAsString(content);
        response.getWriter().write(str);
    }

}
