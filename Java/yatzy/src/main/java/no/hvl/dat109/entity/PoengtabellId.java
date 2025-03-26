package no.hvl.dat109.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;

@Embeddable
@Table(schema = "yatzy")
public class PoengtabellId implements Serializable {
	private String brukernavn;
	private int spillNr;

	public PoengtabellId() {
	}

	public PoengtabellId(String brukernavn, int spillNr) {
		this.brukernavn = brukernavn;
		this.spillNr = spillNr;
	}

	public String getBrukernavn() {
		return brukernavn;
	}

	public void setBrukernavn(String brukernavn) {
		this.brukernavn = brukernavn;
	}

	public int getSpillNr() {
		return spillNr;
	}

	public void setSpillNr(int spillNr) {
		this.spillNr = spillNr;
	}

	@Override
	public int hashCode() {
		return Objects.hash(brukernavn, spillNr);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		PoengtabellId that = (PoengtabellId) o;
		return spillNr == that.spillNr && Objects.equals(brukernavn, that.brukernavn);
	}

}
