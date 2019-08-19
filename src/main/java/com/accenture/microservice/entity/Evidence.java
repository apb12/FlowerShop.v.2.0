package com.accenture.microservice.entity;

import com.accenture.microservice.Enums.EvidenceStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Evidence implements Serializable {

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

}
