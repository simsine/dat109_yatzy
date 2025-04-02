package no.hvl.dat109.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(schema = "yatzy")
public class Admin {
	@Id
	String brukernavn;

	@OneToOne
	@JoinColumn(name = "brukernavn")
	private Spiller spiller;
}
