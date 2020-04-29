package com.foan.crm.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "created_by", nullable = false)
    private String created_by;

    @Column(name = "create_at", nullable = false)
    @CreationTimestamp
    private Timestamp createdat;

    @Column(name = "update_by")
    private String updateby;

    @Column(name = "update_at")
    @UpdateTimestamp
    private Timestamp updatedat;
}
