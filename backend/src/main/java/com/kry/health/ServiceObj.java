package com.kry.health;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "services")
public class ServiceObj {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String url;
    private String status;
    private Timestamp creation_time;

    public Integer getId() {
        return id;
    }
  
    public void setId(Integer id) {
      this.id = id;
    }
  
    public String getName() {
      return name;
    }
  
    public void setName(String name) {
      this.name = name;
    }
  
    public String getUrl() {
      return url;
    }
  
    public void setUrl(String url) {
      this.url = url;
    }

    public String getStatus() {
      return status;
    }
  
    public void setStatus(String status) {
      this.status = status;
    }

    public Timestamp getCreationTime() {
      return creation_time;
    }
  
    public void setCreationTime() {
      this.creation_time = Timestamp.valueOf(LocalDateTime.now());
    }
    
}