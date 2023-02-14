package com.wcbeh.entity;
import java.io.Serializable;

public class Equation implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer valueX;
	private Integer valueY;
	private Integer valueZ;
	
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
