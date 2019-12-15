package com.zhoujin.pojo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 建立多对多映射
 *
 *
 */
@Entity
@Table(name = "t_menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menuid")
    private Long menuid;

    @Column(name = "menuname")
    private String menuname;

    @Column(name = "menuurl")
    private String menuurl;

    @Column(name = "menufatherid")
    private Long fatherid;

    @ManyToMany(mappedBy = "menus")
    private Set<Role> roles = new HashSet<>();

    public Long getMenuid() {
        return menuid;
    }

    public void setMenuid(Long menuid) {
        this.menuid = menuid;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public String getMenuurl() {
        return menuurl;
    }

    public void setMenuurl(String menuurl) {
        this.menuurl = menuurl;
    }

    public Long getFatherid() {
        return fatherid;
    }

    public void setFatherid(Long fatherid) {
        this.fatherid = fatherid;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    @Override
    public String toString() {
        return "Menu{" +
                "menuid=" + menuid +
                ", menuname='" + menuname + '\'' +
                ", menuurl='" + menuurl + '\'' +
                ", fatherid=" + fatherid +
                ", roles=" + roles +
                '}';
    }
}
