package me.skhu.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.skhu.domain.dto.UserDto;

/**
 * Created by Manki Ki on 2017-01-18.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "login_id")
    private String loginId;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "grade")
    private int grade;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "company_number")
    private String companyNumber;

    @Column(name = "status")
    private String status;

    @Column(name = "birth")
    private String birth;

    @Column(name = "email")
    private String email;

    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name="user_type")
    private Position position;

    public static User ofCreate(String loginId,String name,String password){
        return User.builder()
                .loginId(loginId)
                .name(name)
                .password(password)
                .build();
    }
    public static User ofUpdate(int id,String loginId,String name,String password){
        return User.builder()
                .id(id)
                .loginId(loginId)
                .name(name)
                .password(password)
                .build();
    }

    public static User of(UserDto userDto, Category category, Position position){
    	return User.builder()
    			.loginId(userDto.getPhoneNumber())
    			.password(userDto.getBirth())
    			.name(userDto.getName())
    			.grade(userDto.getGrade())
    			.phoneNumber(userDto.getPhoneNumber())
    			.companyNumber(userDto.getCompanyNumber())
    			.status(userDto.getStatus())
    			.birth(userDto.getBirth())
    			.email(userDto.getEmail())
    			.category(category)
    			.position(position)
    			.build();
    }

    public static User ofCreate2(UserDto userDto,Position position){
        return User.builder()
                .loginId(userDto.getPhoneNumber())
                .password(userDto.getBirth())
                .name(userDto.getName())
                .grade(userDto.getGrade())
                .birth(userDto.getBirth())
                .status(userDto.getStatus())
                .phoneNumber(userDto.getPhoneNumber())
                .companyNumber(userDto.getCompanyNumber())
                .email(userDto.getEmail())
                .position(position)
                .build();
    }

   public static User ofUpdate2(int id, String loginId, String name, int grade, String birth, String status, String phoneNumber,
		   String companyNumber, String email, Position position){
	   return User.builder()
			   .loginId(loginId)
    		   .id(id)
               .name(name)
               .grade(grade)
               .birth(birth)
               .status(status)
               .phoneNumber(phoneNumber)
               .companyNumber(companyNumber)
               .email(email)
               .position(position)
               .build();
   }

}
