package com.lan.pojo;

import javax.persistence.Id;

public class Stu {
    /**
     * id
     */
    @Id
    private Integer id;

    /**
     * age
     */
    private String name;

    /**
     * name
     */
    private Integer age;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取age
     *
     * @return name - age
     */
    public String getName() {
        return name;
    }

    /**
     * 设置age
     *
     * @param name age
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取name
     *
     * @return age - name
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置name
     *
     * @param age name
     */
    public void setAge(Integer age) {
        this.age = age;
    }
}