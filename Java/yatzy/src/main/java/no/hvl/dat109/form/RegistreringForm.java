package no.hvl.dat109.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegistreringForm {

	@NotEmpty(message = "Brukernavn kan ikke være tomt")
	@Size(min = 3, max = 20, message = "Brukernavn må være mellom 2-20 bokstaver")
	@Pattern(regexp = "^[a-zA-Z0-9_-]{3,20}$", message = "Brukernavn må være engelske bokstaver, tall og _ understrek")
	private String brukernavn;

	@NotEmpty(message = "Epost kan ikke være tomt")
	@Pattern(regexp = "^[\\w\\-\\.]+@([\\w-]+\\.)+[\\w-]{2,}$", message = "Epost må være gyldig")
	private String email;

	@NotEmpty(message = "Fornavn kan ikke være tomt")
	@Size(min = 2, max = 20, message = "Fornavn må være mellom 2-20 bokstaver")
	@Pattern(regexp = "^[A-ZÆØÅ][a-zæøåA-ZÆØÅ -]{1,19}$", message = "Fornavn må starte med stor forbokstav"
			+ "og kan inneholde mellomrom og bindestrek (-)")
	private String fornavn;

	@NotEmpty(message = "Etternavn kan ikke være tomt")
	@Size(min = 2, max = 20, message = "Etternavn må være mellom 2-20 bokstaver")
	@Pattern(regexp = "^[A-ZÆØÅ][a-zæøåA-ZÆØÅ -]{1,19}$", message = "Etternavn må starte med stor forbokstav"
			+ "og kan inneholde mellomrom og bindestrek (-)")
	private String etternavn;

	@NotEmpty(message = "Passord kan ikke være tomt")
	@Size(min = 8, message = "Passord må være minst 8 tegn")
	private String passord;

	public RegistreringForm(
			@NotEmpty(message = "Brukernavn kan ikke være tomt") @Size(min = 3, max = 20, message = "Brukernavn må være mellom 2-20 bokstaver") @Pattern(regexp = "^[a-zA-Z0-9_-]{3,20}$", message = "Brukernavn må være engelske bokstaver, tall og _ understrek") String brukernavn,
			@NotEmpty(message = "Epost kan ikke være tomt") @Pattern(regexp = "^[\\w\\-\\.]+@([\\w-]+\\.)+[\\w-]{2,}$", message = "Epost må være gyldig") String email,
			@NotEmpty(message = "Fornavn kan ikke være tomt") @Size(min = 2, max = 20, message = "Fornavn må være mellom 2-20 bokstaver") @Pattern(regexp = "^[A-ZÆØÅ][a-zæøåA-ZÆØÅ -]{1,19}$", message = "Fornavn må starte med stor forbokstavog kan inneholde mellomrom og bindestrek (-)") String fornavn,
			@NotEmpty(message = "Etternavn kan ikke være tomt") @Size(min = 2, max = 20, message = "Etternavn må være mellom 2-20 bokstaver") @Pattern(regexp = "^[A-ZÆØÅ][a-zæøåA-ZÆØÅ -]{1,19}$", message = "Etternavn må starte med stor forbokstavog kan inneholde mellomrom og bindestrek (-)") String etternavn,
			@NotEmpty(message = "Passord kan ikke være tomt") @Size(min = 8, message = "Passord må være minst 8 tegn") String passord) {
		super();
		this.brukernavn = brukernavn;
		this.email = email;
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.passord = passord;
	}

	public String getBrukernavn() {
		return brukernavn;
	}

	public void setBrukernavn(String brukernavn) {
		this.brukernavn = brukernavn;
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

	public String getPassord() {
		return passord;
	}

	public void setPassord(String passord) {
		this.passord = passord;
	}

	@Override
	public String toString() {
		return "RegistreringForm [brukernavn=" + brukernavn + ", email=" + email + ", fornavn=" + fornavn
				+ ", etternavn=" + etternavn + ", passord=" + passord + "]";
	}

}
