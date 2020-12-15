package com.controller;

import com.entity.*;
import com.service.GoodsService;
import com.service.OrdersService;
import com.service.TypesService;
import com.utils.PageUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName IndexController
 * @Description TODO 前台相关接口
 * @Author GuiGhost
 * @Date 2020/12/08 09:56
 * @Version 1.0
 **/
@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private TypesService typesService;
    @Autowired
    private OrdersService ordersService;

    /**
     * @Author GuiGhost
     * @Description //TODO 商城首页模块
     * @Date 15:03 2020/12/09
     * @Param [request]
     * @return java.lang.String
     **/
    @GetMapping("/index")
    public String index(HttpServletRequest request){
        request.setAttribute("flag",1);
        //今日推荐
        List<Goods> todayList = goodsService.getListByTopType(Tops.TYPE_TODAY, 1, 6);
        request.setAttribute("todayList",todayList);
        //热销排行
        List<Goods> hotList = goodsService.getListByHot(1, 10);
        request.setAttribute("hotList",hotList);
        //类目列表
        List<Types> typeList = typesService.getList();


        /**
         * 同时这里同时调用了两张表的数据，显示每类商品，一个商品表、一个类目表
         * 注意：这里可以使用连表查询，但连表查询非常消耗数据库的资源
         * 所以可以使用map集合存储不同类型的数据
         * */
        List<Map<String,Object>> dataList = new ArrayList<Map<String, Object>>();
        for (Types types : typeList) {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("type",types);
            map.put("goodList",goodsService.getListByTypeId(types.getId(),1,15));
            dataList.add(map);
        }
        request.setAttribute("dataList",dataList);
        return "/index/index.jsp";
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 商品分类模块
     * @Date 12:00 2020/12/08
     * @Param [request, id, page, size]
     * @return java.lang.String
     **/
    @GetMapping("/type")
    public String type(HttpServletRequest request,
                       @RequestParam(required = false,defaultValue = "0")int id,
                       @RequestParam(required = false,defaultValue = "1")int page,
                       @RequestParam(required = false,defaultValue = "10")int size){
        request.setAttribute("type",typesService.get(id));//指定类目
        request.setAttribute("goodList",goodsService.getListByTypeId(id,page,size));
        request.setAttribute("pageHtml",
                PageUtil.getPageHtml(request,goodsService.getCountByTypeId(id),page,size));
        return "/index/goods.jsp";
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 今日推荐模块
     * @Date 12:00 2020/12/08
     * @Param [request, page, size]
     * @return java.lang.String
     **/
    @GetMapping("/today")
    public String today(HttpServletRequest request,
                        @RequestParam(required = false,defaultValue = "1")int page,
                        @RequestParam(required = false,defaultValue = "10")int size){
        request.setAttribute("flag",2);
        request.setAttribute("goodList",goodsService.getListByTopType(Tops.TYPE_TODAY,page,size));
        request.setAttribute("pageHtml",
                PageUtil.getPageHtml(request,goodsService.getCountByTopType(Tops.TYPE_TODAY),page,size));
        return "/index/goods.jsp";
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 商品热销模块
     * @Date 13:56 2020/12/08
     * @Param [request, page, size]
     * @return java.lang.String
     **/
    @GetMapping("/hot")
    public String hot(HttpServletRequest request,
                      @RequestParam(required = false,defaultValue = "1")int page,
                      @RequestParam(required = false,defaultValue = "10")int size){
        request.setAttribute("flag",3);
        request.setAttribute("goodList",goodsService.getListByHot(page,size));
        request.setAttribute("pageHtml",
                PageUtil.getPageHtml(request,goodsService.getCountByHot(),page,size));
        return "/index/goods.jsp";
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 新品查询模块
     * @Date 17:05 2020/12/08
     * @Param [request, page, size]
     * @return java.lang.String
     **/
    @GetMapping("/new")
    public String newGoods(HttpServletRequest request,
                           @RequestParam(required = false,defaultValue = "1")int page,
                           @RequestParam(required = false,defaultValue = "10")int size){
        request.setAttribute("flag",4);
        request.setAttribute("goodList",goodsService.getListByNew(page,size));
        request.setAttribute("pageHtml",
                PageUtil.getPageHtml(request,goodsService.getCountByNew(),page,size));
        return "/index/goods.jsp";
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 显示商品详情模块
     * @Date 00:16 2020/12/11
     * @Param [id, request]
     * @return java.lang.String
     **/
    @GetMapping("/detail")
    public String detail(int id, HttpServletRequest request){
        request.setAttribute("good",goodsService.getById(id));
        request.setAttribute("todayList",goodsService.getListByTopType(Tops.TYPE_TODAY,1,2));
        return "/index/detail.jsp";
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 用户订单模块
     * @Date 00:24 2020/12/11
     * @Param [request, session]
     * @return java.lang.String
     **/
    @GetMapping("/order")
    public String order(HttpServletRequest request,HttpSession session,
                        @RequestParam(required = false,defaultValue = "1")int page,
                        @RequestParam(required = false,defaultValue = "3")int size){
        Users user = (Users) session.getAttribute("user");
        List<Orders> orderList = ordersService.getListByUserId(user.getId(),page,size);
        request.setAttribute("orderList",orderList);
        request.setAttribute("pageHtml",PageUtil.getPageHtml(request,ordersService.getCountByUserId(user.getId()),page,size));
        return "/index/order.jsp";
    }
}
