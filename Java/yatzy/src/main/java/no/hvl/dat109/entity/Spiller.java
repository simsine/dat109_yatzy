package no.hvl.dat109.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(schema = "yatzy")
public class Spiller {
	@Id
	private String brukernavn;
	private String email;
	private String fornavn;
	private String etternavn;
	private String hashetpassord;
	private String salt;
	
	public Spiller() {
		
	}

	public Spiller(String brukernavn, String eMail, String fornavn, String etternavn, String hashPassord, String salt) {
		super();
		this.brukernavn = brukernavn;
		this.email = eMail;
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.hashetpassord = hashPassord;
		this.salt = salt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getHashetpassord() {
		return hashetpassord;
	}

	public void setHashetpassord(String hashetpassord) {
		this.hashetpassord = hashetpassord;
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
