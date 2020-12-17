package com.service;

import com.dao.GoodsDao;
import com.entity.Goods;
import com.entity.Tops;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @ClassName GoodsService
 * @Description TODO 商品服务类
 * @Author GuiGhost
 * @Date 2020/12/08 09:35
 * @Version 1.0
 **/
@Service
public class GoodsService {

    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private TypesService typesService;
    @Autowired
    private TopsService topsService;


    /**
     * @Author GuiGhost
     * @Description //TODO 根据商品编号查询
     * @Date 16:52 2020/12/09
     * @Param [id]
     * @return com.entity.Goods
     **/
    public Goods getById(int id){
        return goodsDao.selectById(id);
    }


    /**
     * @Author GuiGhost
     * @Description //TODO 根据类目Id查询商品并分页业务
     * @Date 10:48 2020/12/08
     * @Param [typeId, page, size]
     * @return java.util.List<com.entity.Goods>
     **/
    public List<Goods> getListByTypeId(int typeId,int page,int size){
        return typeId > 0 ? packGood(goodsDao.selectListByTypeId(typeId,
                size * (page-1),size)) : this.getList(page,size);
    }


    /**
     * @Author GuiGhost
     * @Description //TODO 查询所有商品并分页业务
     * @Date 10:49 2020/12/08
     * @Param [page, size]
     * @return java.util.List<com.entity.Goods>
     **/
    public List<Goods> getList(int page,int size){
        return packGood(goodsDao.selectList(size * (page-1),size));
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 封装商品
     * @Date 09:53 2020/12/08
     * @Param [list]
     * @return java.util.List<com.entity.Goods>
     **/
    public List<Goods> packGood(List<Goods> list){
        for (Goods goods : list) {
            goods = packGood(goods);
        }
        return list;
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 封装商品
     * @Date 09:40 2020/12/08
     * @Param [goods]
     * @return com.entity.Goods
     **/
    public Goods packGood(Goods goods){
        if (goods != null){
            goods.setType(typesService.get(goods.getTypeId()));//类目信息
            goods.setTop(Objects.nonNull(topsService.getByGoodIdAndType(goods.getId(), Tops.TYPE_TODAY)));
        }
        return goods;
    }


    /**
     * @Author GuiGhost
     * @Description //TODO 查询指定类目ID下的商品总数
     * @Date 11:01 2020/12/08
     * @Param [typeId]
     * @return long
     **/
    public long getCountByTypeId(int typeId){
        return typeId > 0 ? goodsDao.selectCountByTypeId(typeId) : this.getCount();
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 查询商品总数业务
     * @Date 11:00 2020/12/08
     * @Param []
     * @return int
     **/
    public int getCount(){
        return goodsDao.selectCount();
    }


    /**
     * @Author GuiGhost
     * @Description //TODO 连表查询今日推荐商业务
     * @Date 11:55 2020/12/08
     * @Param [type, page, size]
     * @return java.util.List<com.entity.Goods>
     **/
    public List<Goods> getListByTopType(byte type,int page,int size){
        return packGood(goodsDao.selectListByTopType(type,size * (page-1),size));
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 连表查询今日推荐商品总记录数业务
     * @Date 11:57 2020/12/08
     * @Param [type]
     * @return long
     **/
    public long getCountByTopType(byte type){
        return goodsDao.selectCountByTopType(type);
    }


    /**
     * @Author GuiGhost
     * @Description //TODO 热销商品服务
     * @Date 14:28 2020/12/08
     * @Param [page, size]
     * @return java.util.List<com.entity.Goods>
     **/
    public List<Goods> getListByHot(int page,int size){
        return packGood(goodsDao.selectListByHot(size * (page-1),size));
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 热销商品总记录服务
     * @Date 14:27 2020/12/08
     * @Param []
     * @return long
     **/
    public long getCountByHot(){
        return goodsDao.selectCountByHot();
    }



    /**
     * @Author GuiGhost
     * @Description //TODO 新品查询业务
     * @Date 17:01 2020/12/08
     * @Param [page, size]
     * @return java.util.List<com.entity.Goods>
     **/
    public List<Goods> getListByNew(int page,int size){
        return packGood(goodsDao.selectListByNew(size * (page-1),size));
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 新品总记录查询业务
     * @Date 17:02 2020/12/08
     * @Param []
     * @return long
     **/
    public long getCountByNew(){
        return goodsDao.selectCountByNew();
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 修改商品库存业务
     * @Date 15:54 2020/12/11
     * @Param [id, amount]
     * @return boolean
     **/
    public boolean updateStock(int id,int amount){
        return goodsDao.updateStock(id,amount);
    }
    public boolean addStock(int id,int amount){
        return goodsDao.addStock(id,amount);
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 修改商品销量
     * @Date 16:51 2020/12/11
     * @Param [id, amount]
     * @return boolean
     **/
    public boolean updateSales(int id,int amount){
        return goodsDao.updateSales(id, amount);
    }
    public boolean lessSales(int id,int amount){
        return goodsDao.lessSales(id,amount);
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 添加商品业务
     * @Date 16:57 2020/12/15
     * @Param [good]
     * @return boolean
     **/
    public boolean add(Goods good){
        return goodsDao.insert(good);
    }

    /**
     * @Author GuiGhost
     * @Description //TODO 修改商品业务
     * @Date 22:11 2020/12/15
     * @Param [good]
     * @return boolean
     **/
    public boolean update(Goods good){
        return goodsDao.update(good);
    }

    
    /**
     * @Author GuiGhost
     * @Description //TODO 删除商品
     * @Date 16:14 2020/12/17
     * @Param [goodId]
     * @return boolean
     **/
    @Transactional
    public boolean delete(int goodId){
        topsService.remove(goodId);
        return goodsDao.delete(goodId);
    }


    /**
     * @Author GuiGhost
     * @Description //TODO 模糊查询
     * @Date 23:34 2020/12/17
     * @Param [search]
     * @return java.util.List<com.entity.Goods>
     **/
    public List<Goods> search(String search){
        return packGood(goodsDao.search("%" + search + "%"));
    }
}
