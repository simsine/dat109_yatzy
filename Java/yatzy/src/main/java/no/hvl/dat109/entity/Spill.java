package no.hvl.dat109.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(schema = "yatzy")
public class Spill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer spillNr;
	
	@OneToMany(mappedBy = "spill")
	private List<Poengtabell> poengtabeller;

	public Spill(List<Poengtabell> poengtabeller) {
		super();
		this.poengtabeller = poengtabeller;
	}
	
	public Spill() {
		
	}

	public Integer getSpillNr() {
		return spillNr;
	}

	public void setSpillNr(int spillNr) {
		this.spillNr = spillNr;
	}

	public List<Poengtabell> getPoengtabeller() {
		return poengtabeller;
	}

	public void setPoengtabeller(List<Poengtabell> poengtabeller) {
		this.poengtabeller = poengtabeller;
	}
	
	
}
