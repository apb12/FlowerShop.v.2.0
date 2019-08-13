package com.accenture.microservice.entity;

import com.accenture.microservice.Enums.EvidenceStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Evidence {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private User user;

    private Double total;

    @OneToMany(mappedBy = "evidence", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Bucket> bucket;

    @Enumerated(EnumType.STRING)
    private EvidenceStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public EvidenceStatus getStatus() {
        return status;
    }

    public void setStatus(EvidenceStatus status) {
        this.status = status;
    }

    public List<Bucket> getBucket() {
        return bucket;
    }

    public void setBucket(List<Bucket> bucket) {
        this.bucket = bucket;
    }
}
