package no.hvl.dat109.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Spill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int spillNr;
	
	@OneToMany(mappedBy = "spill")
	private List<Poengtabell> poengtabeller;
}
