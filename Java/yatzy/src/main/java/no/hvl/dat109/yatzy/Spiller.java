package no.hvl.dat109.yatzy;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;



@Entity
public class Spiller {
	@Id
	private String brukernavn;
	private String eMail;
	private String fornavn;
	private String etternavn;
	private String hashPassord;
	private String salt;
	
	public Spiller() {
		
	}

	public Spiller(String brukernavn, String eMail, String fornavn, String etternavn, String hashPassord, String salt) {
		super();
		this.brukernavn = brukernavn;
		this.eMail = eMail;
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.hashPassord = hashPassord;
		this.salt = salt;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getFornavn() {
		return fornavn;
	}

	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}

	public String getEtternavn() {
		return etternavn;
	}

	public void setEtternavn(String etternavn) {
		this.etternavn = etternavn;
	}

	public String getHashPassord() {
		return hashPassord;
	}

	public void setHashPassord(String hashPassord) {
		this.hashPassord = hashPassord;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getBrukernavn() {
		return brukernavn;
	}
}
