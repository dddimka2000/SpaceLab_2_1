package org.example.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="session")
@Data
public class SessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    Date time;
    @Column(name = "ip_address")
    String ipAddress;
    @Column(name = "device")
    String device;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
