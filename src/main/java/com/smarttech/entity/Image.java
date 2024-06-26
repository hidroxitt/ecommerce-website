package com.smarttech.entity;

import com.smarttech.constant.ImageType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "_image")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "url")
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ImageType type;

    @Column(name = "object_id")
    private String objectId;

    @Column(name = "created_date", updatable = false)
    @CreationTimestamp
    private Date createdDate;
}
