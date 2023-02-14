package com.wcbeh.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
	name = "equation",
	uniqueConstraints={ @UniqueConstraint(columnNames = {"x", "y", "z"}) }
)
public class Equation implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false) private Long id;
	@Column(name = "x", nullable = false) private Integer valueX;
	@Column(name = "y", nullable = false) private Integer valueY;
	@Column(name = "z", nullable = false) private Integer valueZ;
	
	public Equation() { }
	
	public Equation(Integer valueX, Integer valueY, Integer valueZ) {
		this.valueX = valueX;
		this.valueY = valueY;
		this.valueZ = valueZ;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getValueX() {
		return valueX;
	}

	public void setValueX(Integer valueX) {
		this.valueX = valueX;
	}

	public Integer getValueY() {
		return valueY;
	}

	public void setValueY(Integer valueY) {
		this.valueY = valueY;
	}

	public Integer getValueZ() {
		return valueZ;
	}

	public void setValueZ(Integer valueZ) {
		this.valueZ = valueZ;
	}
	
}
