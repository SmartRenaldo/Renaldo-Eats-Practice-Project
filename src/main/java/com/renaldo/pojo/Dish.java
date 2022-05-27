package com.renaldo.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * use @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss") before date attribute
 * can avoid the following problem:
 * Resolved [org.springframework.http.converter.HttpMessageNotReadableException:
 * JSON parse error: Cannot deserialize value of type `java.util.Date` from String
 * "2021-10-01 00:00:00": not a valid representation (error: Failed to parse Date
 * value '2021-10-01 00:00:00': Cannot parse date "2021-10-01 00:00:00": while it
 * seems to fit format 'yyyy-MM-dd'T'HH:mm:ss.SSSX', parsing fails (leniency? null)); nested exception is
 * com.fasterxml.jackson.databind.exc.InvalidFormatException: Cannot deserialize value of type `java.util.Date`
 * from String "2021-10-01 00:00:00": not a valid representation (error: Failed to parse Date value '2021-10-01 00:00:00': Cannot parse date "2021-10-01 00:00:00": while it seems to fit format 'yyyy-MM-dd'T'HH:mm:ss.SSSX',
 * parsing fails (leniency? null))<LF> at [Source: (PushbackInputStream); line: 3, column: 15]
 * (through reference chain: houseHistory.entity.House["publish"])]
 */
@Entity
@Table(name = "tb_dish",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
@Data
@EntityListeners(AuditingEntityListener.class)
public class Dish implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(64)")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

    private BigDecimal price;

    private String code;

    private String image;

    private String description;

    private Integer status;

    private Integer sort;

    @CreatedBy
    String createdBy;

    @LastModifiedBy
    String lastModifiedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date dateCreated = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date dateModified = new Date();

}
