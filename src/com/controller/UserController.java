package com.controller;

import com.config.ExceptionConfig;
import com.entity.Carts;
import com.entity.Orders;
import com.entity.Users;
import com.service.CartService;
import com.service.OrdersService;
import com.service.UserService;
import com.utils.SafeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * 用户控制器类
 * */
@Controller
@RequestMapping("/index")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrdersService ordersService;


    /**
     * GET请求跳转登录页面
     * */
    @GetMapping("/login")
    public String log(){
        return "/index/login.jsp";
    }

    /**
     * POST请求登录验证
     * */
    @PostMapping("/login")
    public void login(Users user, HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException, ServletException {
        Users loginUser = userService.getUsernameAndPassword(user.getUsername(),user.getPassword());
        if (Objects.isNull(loginUser)){
            session.setAttribute("msg","用户名或密码错误");
            request.getRequestDispatcher("/index/login.jsp").forward(request,response);
        }else {
            session.setAttribute("user",loginUser);
            //还原购物车
            session.setAttribute("cartCount",0);
            String referer = request.getHeader("referer");//页面来源
            System.out.println(referer);//TODO
            response.sendRedirect("index");
        }

    }

    /**
     * GET请求跳转注册页面
     * */
    @GetMapping("/register")
    public String reg(){
        return "/index/register.jsp";
    }
    /**
     * POST请求提交数据注册用户
     * */
    @PostMapping("/register")
    public String register(Users users,HttpServletRequest request){
        if (users.getUsername().isEmpty()){
            request.setAttribute("msg","用户名不能为空!");
        }else if (Objects.nonNull(userService.getByUsername(users.getUsername()))){
            request.setAttribute("msg","用户名已注册!");
        }else {
            userService.insertUsers(users);
            request.setAttribute("msg","注册成功,请前往登录!");
            return "/index/login.jsp";
        }

        return "/index/register.jsp";
    }

    /**
     * GET请求跳转修改密码页面
     * */
    @GetMapping("/password")
    public String toPasswordPage(){
        return "/index/password.jsp";
    }

    /**
     * POST请求修改密码操作
     * */
    @PostMapping("/passwordUpdate")
    public String passwordUpdate(String password,String passwordNew,HttpServletRequest request,HttpSession session){
        Users user = (Users) session.getAttribute("user");
        user = userService.getById(user.getId());
        if (!user.getPassword().equals(SafeUtil.encode(password))){
            request.setAttribute("msg","原密码不匹配！！");
        }else {
            userService.updatePassword(user.getId(),passwordNew);
            request.setAttribute("msg","密码修改成功！！！");
        }

        return "/index/password.jsp";
    }

    /**
     * GET请求跳转到地址管理
     * */
    @GetMapping("/address")
    public String toAddress(){
        return "/index/address.jsp";
    }

    /**
     * POST请求提交修改地址信息
     * */
    @RequestMapping("/addressUpdate")
    public String updateAddress(Users users,HttpServletRequest request,HttpSession session){
        Users user = (Users) session.getAttribute("user");
        userService.updateAddress(user.getId(),users);
        session.setAttribute("user",userService.getById(user.getId()));
        request.setAttribute("msg","信息修改成功！！！");
        return "/index/address.jsp";
    }

    /**
     * 注销登录
     * */
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        session.removeAttribute("cartCount");
        return "/index/login.jsp";
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 购物车模块
     * @Date 21:45 2020/12/09
     * @Param [request, session]
     * @return java.lang.String
     **/
    @GetMapping("/cart")
    public String cart(HttpServletRequest request,HttpSession session){
        long cartTotal = 0L;
        Users user = (Users) session.getAttribute("user");
        List<Carts> cartsList = cartService.getList(user.getId());
        for (Carts carts : cartsList) {
            cartTotal += carts.getTotal();
        }
        request.setAttribute("cartList",cartsList);//购物车商品
        request.setAttribute("cartTotal",cartTotal);
        return "/index/cart.jsp";
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 购物车总金额
     * @Date 01:30 2020/12/10
     * @Param [request, session]
     * @return java.lang.String
     **/
    @GetMapping("/cartTotal")
    public @ResponseBody long cartTotal(HttpServletRequest request,HttpSession session){
        long cartTotal = 0L;
        Users user = (Users) session.getAttribute("user");
        List<Carts> cartsList = cartService.getList(user.getId());
        for (Carts carts : cartsList) {
            cartTotal += carts.getTotal();
        }
        session.setAttribute("cartCount",cartService.getCount(user.getId()));//设置购物车数量
        return cartTotal;
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 加入购物车模块
     * @Date 23:51 2020/12/09
     * @Param [goodId, session]
     * @return boolean
     **/
    @PostMapping("/cartBuy")
    public @ResponseBody boolean cartBuy(int goodId,HttpSession session){
        Users user = (Users) session.getAttribute("user");
        return cartService.save(goodId,user.getId());
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 增加购物车商品数量
     * @Date 01:15 2020/12/10
     * @Param [id]
     * @return boolean
     **/
    @PostMapping("/cartAdd")
    public @ResponseBody boolean cartAdd(int id,HttpSession session){
        updateCartCount(session);//跟新购物车数量
        return cartService.addAmount(id);
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 减少指定购物车商品数量
     * @Date 01:17 2020/12/10
     * @Param [id]
     * @return boolean
     **/
    @PostMapping("/cartLess")
    public @ResponseBody boolean cartLess(int id,HttpSession session){
        updateCartCount(session);//跟新购物车数量
        return cartService.lessAmount(id);
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 删除指定购物车商品
     * @Date 01:22 2020/12/10
     * @Param [id]
     * @return boolean
     **/
    @PostMapping("/cartDelete")
    public @ResponseBody boolean cartDelete(int id,HttpSession session){
        updateCartCount(session);//跟新购物车数量
        return cartService.delete(id);
    }
    
    /**
     * @Author GuiGhost
     * @Description //TODO 更新购物车数量
     * @Date 02:27 2020/12/10
     * @Param [session]
     * @return void
     **/
    public void updateCartCount(HttpSession session){
        Users user = (Users) session.getAttribute("user");
        session.setAttribute("cartCount",cartService.getCount(user.getId()));
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 
     * @Date 11:45 2020/12/11
     * @Param [request, session]
     * @return void
     **/
    @GetMapping("/orderSave")
    public String orderSave(HttpServletRequest request,HttpSession session) throws ExceptionConfig.MyException {
        Users user = (Users) session.getAttribute("user");
        int orderId = ordersService.save(user.getId());
        session.removeAttribute("cartCount");//清理购物车数量session
        return "redirect:orderPay?id=" + orderId;//跳转支付
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 支付页面
     * @Date 16:54 2020/12/11
     * @Param [id, request]
     * @return java.lang.String
     **/
    @GetMapping("/orderPay")
    public String orderPay(int id, ServletRequest request){
        request.setAttribute("order",ordersService.getById(id));
        return "/index/pay.jsp";
    }


    /**
     * @Author GuiGhost
     * @Description //TODO 修改订单状态（支付成功）模块
     * @Date 22:54 2020/12/12
     * @Param [orders]
     * @return java.lang.String
     **/
    @PostMapping("/orderPay")
    public String orderPay(Orders orders){
        orders.setStatus(Orders.STATUS_PAYED);
        ordersService.updateByPayed(orders);
        return "/index/payok.jsp";
    }
}
