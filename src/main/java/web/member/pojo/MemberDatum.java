package web.member.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "member_data")
public class MemberDatum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberNo", nullable = false, insertable = false)
    private Integer id;

    @Column(name = "memberAccount", nullable = false, length = 20)
    private String memberAccount;

    @Column(name = "memberName", nullable = false, length = 20)
    private String memberName;

    @Column(name = "memberGender", nullable = false, insertable = false)
    private Byte memberGender;

    @Column(name = "memberPassword", nullable = false, length = 20)
    private String memberPassword;

    @Column(name = "memberPhone", nullable = false, length = 10)
    private String memberPhone;

    @Column(name = "memberEmail", nullable = false, length = 40)
    private String memberEmail;

    @Column(name = "memberAddress", length = 100, insertable = false)
    private String memberAddress;

    @Column(name = "memberJoinTime", nullable = false, insertable = false)
    private Instant memberJoinTime;

    @Column(name = "memberBirthday", insertable = false)
    private Instant memberBirthday;

    @Column(name = "memberNation", length = 10, insertable = false)
    private String memberNation;

    @Column(name = "memberPic", insertable = false)
    private byte[] memberPic;

    @Column(name = "memberCard", length = 19, insertable = false)
    private String memberCard;

    @Column(name = "memberPoints", nullable = false, insertable = false)
    private Integer memberPoints;

    @Column(name = "memberStat", nullable = false, insertable = false)
    private Byte memberStat;


}