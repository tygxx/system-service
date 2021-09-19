package com.yy.ds.system.user.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name = "sys_role")
@Data
public class Role implements Serializable {

    private static final long serialVersionUID = -759644943064216466L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 名称
     */
    @Column
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    @Column
    @CreationTimestamp
    private Date insertTime;

    /**
     * 修改时间
     */
    @Column
    @UpdateTimestamp
    private Date updateTime;

    // 这里要忽略Permission里面的roleList序列化，不然相当于死循环
    @JsonIgnoreProperties(value = { "roleList" })
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "sys_role_permission", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = {
            @JoinColumn(name = "permission_id") })
    private List<Permission> permissionList;

    // 这里要忽略User里面的roleList序列化，不然相当于死循环
    @JsonIgnoreProperties(value = { "roleList" })
    @ManyToMany(mappedBy = "roleList", fetch = FetchType.LAZY)
    private List<User> userList;
}