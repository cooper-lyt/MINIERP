package com.dgsoft.common.system.model;
// Generated Apr 28, 2013 11:02:59 AM by Hibernate Tools 4.0.0

import com.dgsoft.common.OrderBean;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Word generated by hbm2java
 */
@Entity
@Table(name = "WORD", catalog = "DG_SYSTEM")
public class Word implements java.io.Serializable,OrderBean {

	private String id;
	private WordCategory wordCategory;
	private String key;
	private String value;
	private String description;
	private int priority;
    private boolean enable;

	public Word() {
	}

	public Word(String id, WordCategory wordCategory, String key, String value,
			int priority) {
		this.id = id;
		this.wordCategory = wordCategory;
		this.key = key;
		this.value = value;
		this.priority = priority;
	}
	public Word(String id, WordCategory wordCategory, String key, String value,
			String description, int priority) {
		this.id = id;
		this.wordCategory = wordCategory;
		this.key = key;
		this.value = value;
		this.description = description;
		this.priority = priority;
	}

	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	@NotNull
	@Size(max = 32)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator",strategy = "uuid")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY", nullable = false)
	@NotNull
	public WordCategory getWordCategory() {
		return this.wordCategory;
	}

	public void setWordCategory(WordCategory wordCategory) {
		this.wordCategory = wordCategory;
	}

	@Column(name = "_KEY", nullable = false, length = 50)
	@NotNull
	@Size(max = 50)
	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Column(name = "_VALUE", nullable = false, length = 100)
	@NotNull
	@Size(max = 100)
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "DESCRIPTION", length = 200)
	@Size(max = 200)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "PRIORITY", nullable = false)
	public int getPriority() {
		return this.priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

    @Column(name = "ENABLE",nullable = false)
    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

}
