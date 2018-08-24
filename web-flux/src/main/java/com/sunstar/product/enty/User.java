package com.sunstar.product.enty;

import java.io.Serializable;

/**
 * @Author 黄昌焕
 * @Date 2018-08-24  14:58
 */
public class User implements Serializable{
    //private final static Logger logger = LoggerFactory.getLogger(topTest.class);

    private String name;
    private Long id;

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
