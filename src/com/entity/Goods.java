package com.entity;

/**
 * @ClassName Goods
 * @Description TODO 商品实体类
 * @Author GuiGhost
 * @Date 2020/12/08 09:19
 * @Version 1.0
 **/
public class Goods {
    //  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
    //  `cover` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '封面',
    //  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
    //  `intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '介绍',
    //  `spec` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规格',
    //  `price` int(11) NOT NULL DEFAULT 0 COMMENT '价格',
    //  `stock` int(11) NOT NULL DEFAULT 0 COMMENT '库存',
    //  `sales` int(11) NOT NULL DEFAULT 0 COMMENT '销量',
    //  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '详情',
    //  `type_id` int(11) NOT NULL DEFAULT 0 COMMENT '分类ID',
    private Integer id;//商品编号
    private String cover;//封面
    private String name;//名称
    private String intro;//介绍
    private String spec;//规格
    private Integer price;//价格
    private Integer stock;//库存
    private Integer sales;//销量
    private String content;//详情
    private Integer typeId;//分类编号
    private Types type;//商品类
    private boolean top;//今日推荐

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover == null ? null : cover.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec == null ? null : spec.trim();
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Types getType() {
        return type;
    }

    public void setType(Types type) {
        this.type = type;
    }

    public boolean isTop() {
        return top;
    }

    public void setTop(boolean top) {
        this.top = top;
    }

    @Override
    public String  toString() {
        return "Goods{" +
                "id=" + id +
                ", cover='" + cover + '\'' +
                ", name='" + name + '\'' +
                ", intro='" + intro + '\'' +
                ", spec='" + spec + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", sales=" + sales +
                ", content='" + content + '\'' +
                ", typeId=" + typeId +
                ", type=" + type +
                '}';
    }
}
