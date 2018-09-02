package top.zhoumy.system.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_user_phone_code")
public class UserPhoneCode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3709701122751966189L;

	@Id
	@GeneratedValue(generator = "JDBC")
	@Column(name = "ID")
	private Long id;

	@Column(name = "PHONE")
	private String phone;

	@Column(name = "PHONE_CODE")
	private String phoneCode;

	@Column(name = "CRATE_TIME")
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
