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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yy.ds.system.user.enums.UserEnums.Status;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name = "sys_user")
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -759644943064216466L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户名
     */
    @Column
    private String username;

    /**
     * 密码
     */
    @Column
    private String password;

    /**
     * 头像
     */
    @Column
    private String icon;

    /**
     * 邮箱
     */
    @Column
    private String email;

    /**
     * 昵称
     */
    @Column
    private String nickName;

    /**
     * 备注信息
     */
    @Column(length = 2048)
    private String note;

    /**
     * 创建时间
     */
    @Column(updatable = false)
    @CreationTimestamp
    private Date insertTime;

    /**
     * 修改时间
     */
    @Column
    @UpdateTimestamp
    private Date updateTime;

    /**
     * 最后登录时间
     */
    @Column
    private Date loginTime;

    /**
     * 帐号启用状态
     */
    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    // 这里要忽略角色里面的用户序列化，不然相当于死循环
    @JsonIgnoreProperties(value = { "userList" })
    // 这里如果不用eager，那么当save时，它会先判断id存不存在（会查询一下）再保存，然而查询时拿不到role，更新完后它会再发一条删除语句，删除中间表的userId数据
    @ManyToMany(fetch = FetchType.EAGER) 
    @JoinTable(name = "sys_user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
            @JoinColumn(name = "role_id") })
    private List<Role> roleList;
}