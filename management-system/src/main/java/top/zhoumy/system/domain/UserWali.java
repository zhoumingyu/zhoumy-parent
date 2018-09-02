package top.zhoumy.system.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import top.zhoumy.common.annotation.ExportConfig;

@Table(name = "t_user_wali")
public class UserWali implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7853981338139928175L;

	@Id
	@GeneratedValue(generator = "JDBC")
	@Column(name = "ID")
	private Long id;

	@Column(name = "PHONE")
	@ExportConfig(value = "手机号码")
	private String phone;

	@Column(name = "CREATE_TIME")
	@ExportConfig(value = "注册时间", convert = "c:top.zhoumy.common.util.poi.convert.TimeConvert")
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
