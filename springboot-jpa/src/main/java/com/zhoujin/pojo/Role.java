package com.zhoujin.pojo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * 建立一对多映射关联
 * 1个用户只能拥有一个角色，一个角色可以拥有多个用户
 */
@Entity
@Table(name = "role")
public class Role {

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleid")

    private Integer roleid;

    @Column(name = "rolename")
    private String rolename;

    @OneToMany(mappedBy = "role")
    private Set<User> users = new HashSet<>();

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
