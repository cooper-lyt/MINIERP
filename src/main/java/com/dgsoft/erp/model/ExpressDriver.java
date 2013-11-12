package com.dgsoft.erp.model;
// Generated Oct 24, 2013 3:27:02 PM by Hibernate Tools 4.0.0

import com.dgsoft.common.NamedEntity;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * ExpressDriver generated by hbm2java
 */
@Entity
@Table(name = "EXPRESS_DRIVER", catalog = "MINI_ERP")
public class ExpressDriver implements java.io.Serializable, NamedEntity {

	private String id;
	private String name;
	private String tel;
	private String carCode;
    private String memo;
    private boolean enable;
	private Set<ExpressCar> expressCars = new HashSet<ExpressCar>(0);

	public ExpressDriver() {
	}

	public ExpressDriver(String id, String name, String tel) {
		this.id = id;
		this.name = name;
		this.tel = tel;
	}
	public ExpressDriver(String id, String name, String tel, String carCode,
			Set<ExpressCar> expressCars) {
		this.id = id;
		this.name = name;
		this.tel = tel;
		this.carCode = carCode;
		this.expressCars = expressCars;
	}

	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 32)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
	@NotNull
	@Size(max = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "NAME", nullable = false, length = 50)
	@NotNull
	@Size(max = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "TEL", nullable = false, length = 50)
	@NotNull
	@Size(max = 50)
	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "CAR_CODE", length = 20)
	@Size(max = 20)
	public String getCarCode() {
		return this.carCode;
	}

	public void setCarCode(String carCode) {
		this.carCode = carCode;
	}

    @Column(name = "ENABLE", nullable = false)
    public boolean isEnable() {
        return this.enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "expressDriver")
	public Set<ExpressCar> getExpressCars() {
		return this.expressCars;
	}

	public void setExpressCars(Set<ExpressCar> expressCars) {
		this.expressCars = expressCars;
	}


    @Column(name = "MEMO", length = 200)
    @Size(max = 200)
    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

}
