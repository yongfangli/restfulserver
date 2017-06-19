package com.meike.restfulserver.authority.jwt;

/**
 * JWT的头部
 * 
 * @author lyf
 *
 */
public class Header {
	private String alg;
	private String typ;

	public Header(String alg, String typ) {
		super();
		this.alg = alg;
		this.typ = typ;
	}

	public String getAlg() {
		return alg;
	}

	public void setAlg(String alg) {
		this.alg = alg;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

}
