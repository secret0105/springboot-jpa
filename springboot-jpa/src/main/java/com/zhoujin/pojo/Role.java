package com.zhoujin.pojo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 建立一对多映射关联
 * 1个用户只能拥有一个角色，一个角色可以拥有多个用户
 */
@Entity
@Table(name = "role")
public class Role {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleid")
    private Long roleid;

    @Column(name = "rolename")
    private String rolename;

    @OneToMany(mappedBy = "role")
    private Set<User> users = new HashSet<>();

    //级联操作，关闭延时加载
    //@JoinTable:映射中间表
    //joinColumns:当前表中的主键所关联的中间表中的外键字段
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "menu_role", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "menu_id"))
    private Set<Menu> menus = new HashSet<>();

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleid=" + roleid +
                ", rolename='" + rolename + '\'' +
                ", users=" + users +
                ", menus=" + menus +
                '}';
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }
}
