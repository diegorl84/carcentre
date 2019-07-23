package com.carcentre.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the MECANICOS database table.
 * 
 */
@Embeddable
public class MecanicoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="tipo_documento")
	private String tipoDocumento;

	private int documento;

	public MecanicoPK() {
	}
	public String getTipoDocumento() {
		return this.tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public int getDocumento() {
		return this.documento;
	}
	public void setDocumento(int documento) {
		this.documento = documento;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MecanicoPK)) {
			return false;
		}
		MecanicoPK castOther = (MecanicoPK)other;
		return 
			this.tipoDocumento.equals(castOther.tipoDocumento)
			&& (this.documento == castOther.documento);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.tipoDocumento.hashCode();
		hash = hash * prime + this.documento;
		
		return hash;
	}
}