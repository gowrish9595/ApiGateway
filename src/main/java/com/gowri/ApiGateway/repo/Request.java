package com.gowri.ApiGateway.repo;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "request_trace")
@Data
public class Request {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String url;

    @CreationTimestamp
    private Date requestTime;
}
