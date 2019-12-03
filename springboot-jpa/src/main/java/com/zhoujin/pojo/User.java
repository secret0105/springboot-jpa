package com.zhoujin.pojo;

import javax.persistence.*;

@Entity   //实体类，必须有
@Table(name = "user" //name必须与数据库表名一致
//        indexes = {@Index(name = "id",columnList = "id",unique = true),
//                @Index(name = "name",columnList = "name",unique = true)}
)
public class User {

    @Id   //指明id列 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //表明是否自动生成
    @Column(name = "id")
    private Long id;

    @Column(name = "age")
    private int age;

    @Column(name = "name")
    private String name;

    //get &  set 都可省略

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
