package pl.kszucki.crmznsurance.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Policy")
public class InsPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPolicy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "idUsers", nullable = false)
    private User user;

    @Column(name = "co_Own_First_Name")
    private String coOwnFirstName;

    @Column(name = "co_Own_Last_Name")
    private String coOwnLastName;

    @Column(name = "office")
    private String office;

    @Column(name = "insurance_Comp")
    private String insuranceComp;

    @Column(name = "policy_Id")
    private String policyNumber;

    @Column(name = "discount")
    private String discount;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date installmentDate;

    @Column(name = "vehicle_Type")
    private String vehicleType;

    @Column(name = "reg_Number")
    private String regNumber;

    @JsonIgnore
    @OneToMany(mappedBy = "insPolicy", cascade = CascadeType.ALL)
    private Set<Attachment> attachments = new HashSet<>();

}
