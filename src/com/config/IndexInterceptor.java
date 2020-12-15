package com.config;


import com.entity.Users;
import com.service.CartService;
import com.service.TypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @ClassName IndexInterceptor
 * @Deacription TODO
 * @Author GuiGhost
 * @Date 2020/12/07 14:18
 * @Version 1.0
 **/
public class IndexInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TypesService typesService;

    @Autowired
    private CartService cartService;


    
    /**
     * @Author GuiGhost
     * @Description //TODO 该方法拦截的是DispatcherServlet之后，调用Controller之前使用
     * @Date 15:01 2020/12/07
     * @Param [request, response, handler]
     * @return boolean
     **/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("typeList",typesService.getList());//为所有页面设置类目列表
        String uri = request.getRequestURI();//拦截指定路径
        if (uri.contains("index/cart") || uri.contains("index/order") || uri.contains("index/my")){
            Object user = request.getSession().getAttribute("user");
            if (Objects.isNull(user)){
                response.sendRedirect("login");
                return false;
            }
        } // 默认放过
        return true;
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 该方法时在调用了Controller之后，分发器DispatcherServlet准备把数据给ViewResolver之前调用
     * @Date 16:15 2020/12/07
     * @Param [request, response, handler, modelAndView]
     * @return void
     **/
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        //指定拦截路径
        String uri = request.getRequestURI();
        if (uri.contains("index/cart")){ //购物车相关请求后，更新session
            Users user = (Users)request.getSession().getAttribute("user");
            request.getSession().setAttribute("cartCount",cartService.getCount(user.getId()));
//            request.getSession().setAttribute("cartCount",0);
        }
        super.postHandle(request, response, handler, modelAndView);
    }


}
