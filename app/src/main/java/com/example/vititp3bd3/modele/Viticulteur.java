package com.example.vititp3bd3.modele;

public class Viticulteur {
	private long idV;
	private String nomV;
	
	public Viticulteur(long idV, String nomV) {
		this.idV = idV;
		this.nomV = nomV;
	}
	
	public Viticulteur(String nomV) {
		this.idV = -1;
		this.nomV = nomV;
	}
	
	public long getIdV() {
		return idV;
	}
	
	public void setIdV(long idV) {
		this.idV = idV;
	}
	
	public String getNomV() {
		return nomV;
	}
	
	public void setNomV(String nomV) {
		this.nomV = nomV;
	}

	public String toString(){
		return "idV="+idV+",nomV="+nomV;
	}
}
