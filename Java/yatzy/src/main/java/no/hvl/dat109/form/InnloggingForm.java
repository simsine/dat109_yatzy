package no.hvl.dat109.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class InnloggingForm {

	@NotEmpty(message = "Brukernavn kan ikke være tomt")
	@Size(min = 3, max = 20, message = "Brukernavn må være mellom 2-20 bokstaver")
	@Pattern(regexp = "^[a-zA-Z0-9_-]{3,20}$", message = "Brukernavn må være engelske bokstaver, tall og _ understrek")
	private String brukernavn;

	@NotEmpty(message = "Passord kan ikke være tomt")
	@Size(min = 8, message = "Passord må være minst 8 tegn")
	private String passord;

	public InnloggingForm(
			@NotEmpty(message = "Brukernavn kan ikke være tomt") @Size(min = 3, max = 20, message = "Brukernavn må være mellom 2-20 bokstaver") @Pattern(regexp = "^[a-zA-Z0-9_-]{3,20}$", message = "Brukernavn må være engelske bokstaver, tall og _ understrek") String brukernavn,
			@NotEmpty(message = "Passord kan ikke være tomt") @Size(min = 8, message = "Passord må være minst 8 tegn") @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zæøå])(?=.*[A-ZÆØÅ])(?=.*[\\W_])(?!.*\\s).{8,}$", message = "Passord må inneholde minst ett tall, minst én liten bokstav, minst én stor bokstav og minst ett spesialtegn. Mellomrom ikke tillatt.") String passord) {
		super();
		this.brukernavn = brukernavn;
		this.passord = passord;
	}

	public String getBrukernavn() {
		return brukernavn;
	}

	public void setBrukernavn(String brukernavn) {
		this.brukernavn = brukernavn;
	}

	public String getPassord() {
		return passord;
	}

	public void setPassord(String passord) {
		this.passord = passord;
	}

	@Override
	public String toString() {
		return "InnloggingForm [brukernavn=" + brukernavn + ", passord=" + passord + "]";
	}

}
