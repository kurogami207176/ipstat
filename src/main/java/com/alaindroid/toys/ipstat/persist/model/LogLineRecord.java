package com.alaindroid.toys.ipstat.persist.model;

import jakarta.persistence.*;

import java.time.ZonedDateTime;


@Entity
@Table(name = "logs")
public class LogLineRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "ip")
    private String ip;
    @Column(name = "log_date")
    private ZonedDateTime date;
    @Column(name = "http_operation")
    private String httpOperation;
    @Column(name = "url")
    private String endPoint;
    @Column(name = "status_code")
    private Integer statusCode;
    @Column(name = "bytes_downloaded")
    private Long bytes;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public String getHttpOperation() {
        return httpOperation;
    }

    public void setHttpOperation(String httpOperation) {
        this.httpOperation = httpOperation;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Long getBytes() {
        return bytes;
    }

    public void setBytes(Long bytes) {
        this.bytes = bytes;
    }
}
