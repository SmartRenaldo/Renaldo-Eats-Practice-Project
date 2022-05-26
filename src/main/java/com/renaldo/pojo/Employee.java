package com.renaldo.pojo;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tb_employee",
        uniqueConstraints = @UniqueConstraint(columnNames = {"username"}))
@Data
@EntityListeners(AuditingEntityListener.class)
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * if do not set character number of varchar, default character number will be 255, and unique will not work.
     * This is because 767 bytes in MySQL version 5.6 (and prior versions),
     * is the stated prefix limitation for InnoDB tables.
     */
    @Column(columnDefinition = "varchar(64)")
    private String username;

    private String name;

    @Column(columnDefinition = "varchar(64) default 'e10adc3949ba59abbe56e057f20f883e'")
    private String password;

    private String phone;

    private String gender;

    @Column(columnDefinition = "int(11) default '1'")
    private Integer status;

    @CreatedBy
    String createdBy;

    @LastModifiedBy
    String lastModifiedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    protected Date dateCreated = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    protected Date dateModified = new Date();

}
