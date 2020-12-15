package com.entity;

/**
 * @ClassName Types
 * @Description TODO 商品类别表
 * @Author GuiGhost
 * @Date 2020/12/07 14:21
 * @Version 1.0
 **/
public class Types {
    //`id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    //  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
    //  `num` int(11) NULL DEFAULT 0 COMMENT '排序号 (从小到大)',
    private Integer id;//商品类别编号
    private String name;//类别名称
    private Integer num;//排序号(从小到大)

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null :name.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }


    @Override
    public String toString() {
        return "Types{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", num=" + num +
                '}';
    }
}
