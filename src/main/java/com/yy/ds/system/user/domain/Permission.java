package com.yy.ds.system.user.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yy.ds.system.user.enums.UserEnums.PermissionType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Table(name = "sys_permission")
@Data
public class Permission implements Serializable {

    private static final long serialVersionUID = -2997129690494851125L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 名称
     */
    @Column
    private String name;

    /**
     * 类型
     */
    @Column
    @Enumerated(EnumType.STRING)
    private PermissionType type;

    /**
     * 创建时间
     */
    @Column
    @CreationTimestamp
    private Date insertTime;

    // 这里要忽略Role里面的permissionList序列化，不然相当于死循环
    @JsonIgnoreProperties(value = { "permissionList" })
    @ManyToMany(mappedBy = "permissionList", fetch = FetchType.LAZY)
    private List<Role> roleList;

}