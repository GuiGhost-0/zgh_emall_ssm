package com.controller;

import com.entity.Admins;
import com.entity.Goods;
import com.entity.Types;
import com.entity.Users;
import com.service.*;
import com.utils.PageUtil;
import com.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName AdminController
 * @Description TODO 后台相关接口
 * @Author GuiGhost
 * @Date 2020/12/12 23:42
 * @Version 1.0
 **/
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminsService adminsService;
    @Autowired
    private TypesService typesService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private UserService userService;
    @Autowired
    private TopsService topsService;

    /**
     * @Author GuiGhost
     * @Description //TODO 跳转去后台主页
     * @Date 00:17 2020/12/13
     * @Param []
     * @return java.lang.String
     **/
    @GetMapping("/index")
    public String index(HttpServletRequest request,HttpSession session){
        Admins admin = (Admins) session.getAttribute("admin");
        request.setAttribute("msg",admin.getUsername() + "管理员,欢迎登录后台管理页面！！！");
        return "/admin/index.jsp";
    }


    /**
     * @Author GuiGhost
     * @Description //TODO 管理员登录模块
     * @Date 00:03 2020/12/13
     * @Param [admins, request]
     * @return java.lang.String
     **/
    @PostMapping("/login")
    public String login(Admins checkout, HttpServletRequest request){
        Admins admins = adminsService.checkout(checkout);
        if (Objects.isNull(admins)){
            request.setAttribute("msg","用户名或密码错误");
            return "/admin/login.jsp";
        }else {
            request.getSession().setAttribute("admin",admins);
            return "redirect:index";
        }
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 管理员登出模块
     * @Date 00:25 2020/12/13
     * @Param [session]
     * @return java.lang.String
     **/
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("admin");
        return "redirect:/index/index";
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 后台类目管理模块
     * @Date 00:44 2020/12/13
     * @Param [request]
     * @return java.lang.String
     **/
    @GetMapping("/typeList")
    public String typeList(HttpServletRequest request){
        request.setAttribute("flag","1");
        List<Types> typesList = typesService.getList();
        request.setAttribute("typeList",typesList);
        return "/admin/type_list.jsp";
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 跳转到添加类目页面
     * @Date 15:17 2020/12/15
     * @Param []
     * @return java.lang.String
     **/
    @GetMapping("/typeAdd")
    public String typeAdd(){
        return "/admin/type_add.jsp";
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 添加类目信息模块
     * @Date 15:25 2020/12/15
     * @Param [types]
     * @return java.lang.String
     **/
    @PostMapping("/typeSave")
    public String typeSave(Types types){
        typesService.save(types);
        return "redirect:typeList";
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 跳转修改类目信息页面
     * @Date 15:40 2020/12/15
     * @Param [id, request]
     * @return java.lang.String
     **/
    @GetMapping("/typeEdit")
    public String typeEdit(int id,HttpServletRequest request){
        request.setAttribute("msg","修改类目信息");
        request.setAttribute("type",typesService.get(id));
        return "/admin/type_edit.jsp";
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 修改类目信息模块
     * @Date 15:49 2020/12/15
     * @Param [types]
     * @return java.lang.String
     **/
    @PostMapping("/typeUpdate")
    public String typeUpdate(Types types){
        typesService.update(types);
        return "redirect:typeList";
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 删除指定类目模块
     * @Date 16:02 2020/12/15
     * @Param [id]
     * @return java.lang.String
     **/
    @GetMapping("/typeDelete")
    public String typeDelete(int id){
        typesService.delete(id);
        return "redirect:typeList";
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 后台商品管理模块
     * @Date 09:56 2020/12/14
     * @Param [request, type, page, size]
     * @return java.lang.String
     **/
    @GetMapping("/goodList")
    public String goodList(HttpServletRequest request,
                           @RequestParam(required = false,defaultValue = "0")byte type,
                           @RequestParam(required = false,defaultValue = "1")int page,
                           @RequestParam(required = false,defaultValue = "10")int size){
        request.setAttribute("flag","2");
        request.setAttribute("type",type);
        if (type == 0){
            request.setAttribute("goodList",goodsService.getList(page,size));
            request.setAttribute("pageTool", PageUtil.getPageTool(request,goodsService.getCount(),page,size));
        }else {
            size = 6;
            request.setAttribute("goodList",goodsService.getListByTopType(type,page,size));
            request.setAttribute("pageTool",PageUtil.getPageTool(request,goodsService.getCountByTopType(type),page,6));
        }
        return "/admin/good_list.jsp";
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 跳转添加商品页面
     * @Date 16:04 2020/12/15
     * @Param []
     * @return java.lang.String
     **/
    @GetMapping("/goodAdd")
    public String goodAdd(HttpServletRequest request){
        request.setAttribute("flag","2");
        request.setAttribute("typeList",typesService.getList());
        return "/admin/good_add.jsp";
    }


    /**
     * @Author GuiGhost
     * @Description //TODO 添加商品模块
     * @Date 16:57 2020/12/15
     * @Param [good, file, page]
     * @return java.lang.String
     **/
    @PostMapping("/goodSave")
    public String goodSave(Goods good, MultipartFile file,
                           @RequestParam(required = false,defaultValue = "1") int page) throws Exception{
        good.setCover(UploadUtil.upload(file));
        if (good.getSales() == null) {
            good.setSales(0);
        }
        goodsService.add(good);
        return "redirect:goodList?flag=2&page="+page;
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 跳转到商品修改页面
     * @Date 22:00 2020/12/15
     * @Param [id]
     * @return java.lang.String
     **/
    @GetMapping("/goodEdit")
    public String goodEdit(int id,HttpServletRequest request){
        request.setAttribute("good",goodsService.getById(id));
        request.setAttribute("typeList",typesService.getList());
        return "/admin/good_edit.jsp";
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 修改商品模块
     * @Date 22:12 2020/12/15
     * @Param [good, file]
     * @return java.lang.String
     **/
    @PostMapping("/goodUpdate")
    public String goodUpdate(Goods good,MultipartFile file) throws Exception {
        good.setCover(UploadUtil.upload(file));
        if (good.getSales() == null){
            good.setSales(0);
        }
        goodsService.update(good);
        return "redirect:goodList";
    }
    
    /**
     * @Author GuiGhost
     * @Description //TODO 删除商品
     * @Date 16:17 2020/12/17
     * @Param [id]
     * @return java.lang.String
     **/
    @GetMapping("/goodDelete")
    public String goodDelete(int id){
        goodsService.delete(id);
        return "redirect:goodList";
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 加入今日推荐模块
     * @Date 16:00 2020/12/17
     * @Param [id]
     * @return java.lang.String
     **/
    @GetMapping("/addTop")
    public String addTop(int id){
        topsService.add(id);
        return "redirect:goodList";
    }
    
    /**
     * @Author GuiGhost
     * @Description //TODO 移除今日推荐模块
     * @Date 16:01 2020/12/17
     * @Param [id]
     * @return java.lang.String
     **/
    @GetMapping("/removeTop")
    public String removeTop(int id){
        topsService.remove(id);
        return "redirect:goodList";
    }



    /**
     * @Author GuiGhost
     * @Description //TODO 后台管理员模块
     * @Date 00:58 2020/12/13
     * @Param [request]
     * @return java.lang.String
     **/
    @GetMapping("/adminList")
    public String adminList(HttpServletRequest request){
        request.setAttribute("flag","5");
        request.setAttribute("adminList",adminsService.get());
        return "/admin/admin_list.jsp";
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 跳转添加管理员页面
     * @Date 01:10 2020/12/13
     * @Param [request]
     * @return java.lang.String
     **/
    @GetMapping("/adminAdd")
    public String adminAdd(HttpServletRequest request){
        request.setAttribute("msg","添加管理员");
        return "/admin/admin_add.jsp";
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 添加管理员
     * @Date 01:21 2020/12/13
     * @Param [admins]
     * @return java.lang.String
     **/
    @PostMapping("/adminSave")
    public String adminSave(Admins admins){
        adminsService.insert(admins);
        return "redirect:adminList";
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 删除指定管理员
     * @Date 01:41 2020/12/13
     * @Param [id]
     * @return java.lang.String
     **/
    @GetMapping("/adminDelete")
    public String adminDelete(int id){
        adminsService.delete(id);
        return "redirect:adminList";
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 跳转重置管理员密码页面
     * @Date 22:58 2020/12/17
     * @Param [id, request]
     * @return java.lang.String
     **/
    @GetMapping("/adminRe")
    public String adminRe(int id,HttpServletRequest request){
        request.setAttribute("msg","管理员密码重置");
        request.setAttribute("admin",adminsService.get(id));
        return "/admin/admin_reset.jsp";
    }
    
    /**
     * @Author GuiGhost
     * @Description //TODO 重置密码
     * @Date 23:00 2020/12/17
     * @Param [admins]
     * @return java.lang.String
     **/
    @PostMapping("/adminReset")
    public String adminReset(Admins admins){
        adminsService.resetPwd(admins);
        return "redirect:adminList";
    }



    /**
     * @Author GuiGhost
     * @Description //TODO 后台订单管理模块
     * @Date 10:45 2020/12/14
     * @Param [request, status, page, size]
     * @return java.lang.String
     **/
    @GetMapping("/orderList")
    public String orderList(HttpServletRequest request,
                            @RequestParam(required = false,defaultValue = "0")int status,
                            @RequestParam(required = false,defaultValue = "1")int page,
                            @RequestParam(required = false,defaultValue = "10")int size){
        request.setAttribute("flag","3");
        request.setAttribute("status",status);
        if (status == 0){
            request.setAttribute("orderList",ordersService.getList(page,size));
            request.setAttribute("pageTool",PageUtil.getPageTool(request,ordersService.getCount(),page,size));
        }else{
            request.setAttribute("orderList",ordersService.getListByStatus(status,page,size));
            request.setAttribute("pageTool",PageUtil.getPageTool(request,ordersService.getCountByStatus(status),page,size));
        }

        return "/admin/order_list.jsp";
    }
    
    /**
     * @Author GuiGhost
     * @Description //TODO 发货模块
     * @Date 16:27 2020/12/17
     * @Param [id, status]
     * @return java.lang.String
     **/
    @GetMapping("/orderSend")
    public String orderSend(int id,int status){
        ordersService.updateStatus(id,status);
        return "redirect:orderList?status=" + status;
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 完成订单
     * @Date 17:12 2020/12/17
     * @Param [id, status]
     * @return java.lang.String
     **/
    @GetMapping("/orderFinish")
    public String orderFinish(int id,int status){
        ordersService.updateStatus(id,status);
        return "redirect:orderList?status=" + status;
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 删除订单
     * @Date 17:42 2020/12/17
     * @Param [id, status]
     * @return java.lang.String
     **/
    @GetMapping("/orderDelete")
    public String orderDelete(int id,int status){
        ordersService.delete(id,status);
        return "redirect:orderList?status=" + status;
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 后台用户管理模块
     * @Date 15:08 2020/12/15
     * @Param [request, page, size]
     * @return java.lang.String
     **/
    @GetMapping("/userList")
    public String userList(HttpServletRequest request,
                           @RequestParam(required = false,defaultValue = "1")int page,
                           @RequestParam(required = false,defaultValue = "10")int size){
        request.setAttribute("flag","4");
        request.setAttribute("userList",userService.getList(page,size));
        request.setAttribute("pageTool",PageUtil.getPageTool(request,userService.getCount(),page,size));
        return "/admin/user_list.jsp";
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 跳转添加用户页面
     * @Date 17:50 2020/12/17
     * @Param [request]
     * @return java.lang.String
     **/
    @GetMapping("/userAdd")
    public String userAdd(HttpServletRequest request){
        request.setAttribute("msg","添加用户");
        return  "/admin/user_add.jsp";
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 保存用户信息
     * @Date 17:53 2020/12/17
     * @Param [users]
     * @return java.lang.String
     **/
    @PostMapping("/userSave")
    public String userSave(Users users){
        userService.insertUsers(users);
        return "redirect:userList";
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 跳转密码重置页面
     * @Date 17:59 2020/12/17
     * @Param [id, request]
     * @return java.lang.String
     **/
    @GetMapping("/userRe")
    public String userRe(int id,HttpServletRequest request){
        request.setAttribute("user",userService.getById(id));
        return "/admin/user_reset.jsp";
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 重置密码
     * @Date 18:05 2020/12/17
     * @Param [users]
     * @return java.lang.String
     **/
    @PostMapping("/userReset")
    public String userReset(Users users){
        userService.updatePassword(users.getId(),users.getPassword());
        return "redirect:userList";
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 跳转修改用户信息页面
     * @Date 18:26 2020/12/17
     * @Param [id, request]
     * @return java.lang.String
     **/
    @GetMapping("/userEdit")
    public String userEdit(int id,HttpServletRequest request){
        request.setAttribute("user",userService.getById(id));
        return "/admin/user_edit.jsp";
    }
    
    /**
     * @Author GuiGhost
     * @Description //TODO 提交用户修改
     * @Date 18:27 2020/12/17
     * @Param []
     * @return java.lang.String
     **/
    @PostMapping("/userUpdate")
    public String userUpdate(Users users){
        userService.updateAddress(users.getId(),users);
        return "redirect:userList";
    }
    /**
     * @Author GuiGhost
     * @Description //TODO 删除指定用户
     * @Date 18:35 2020/12/17
     * @Param [id]
     * @return java.lang.String
     **/
    @GetMapping("/userDelete")
    public String userDelete(int id){
        userService.delete(id);
        return "redirect:userList";
    }
}
