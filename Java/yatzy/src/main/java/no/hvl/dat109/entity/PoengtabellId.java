package no.hvl.dat109.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;

@Embeddable
@Table(schema = "yatzy")
public class PoengtabellId implements Serializable {
	private String brukernavn;
	private int spillnr;

	public PoengtabellId() {
	}

	public PoengtabellId(String brukernavn, int spillnr) {
		this.brukernavn = brukernavn;
		this.spillnr = spillnr;
	}

	public String getBrukernavn() {
		return brukernavn;
	}

	public void setBrukernavn(String brukernavn) {
		this.brukernavn = brukernavn;
	}

	public int getSpillnr() {
		return spillnr;
	}

	public void setSpillnr(int spillnr) {
		this.spillnr = spillnr;
	}

	@Override
	public int hashCode() {
		return Objects.hash(brukernavn, spillnr);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		PoengtabellId that = (PoengtabellId) o;
		return spillnr == that.spillnr && Objects.equals(brukernavn, that.brukernavn);
	}

}
