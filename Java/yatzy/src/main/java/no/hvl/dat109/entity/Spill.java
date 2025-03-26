package no.hvl.dat109.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(schema = "yatzy")
public class Spill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer spillnr;
	
	@OneToMany
	@JoinColumn(name = "spillnr")
	private List<Poengtabell> poengTabeller;

	public Spill(List<Poengtabell> poengtabeller) {
		super();
		this.poengTabeller = poengtabeller;
	}
	
	public Spill() {
		
	}

	public Integer getSpillnr() {
		return spillnr;
	}

	public void setSpillnr(Integer spillNr) {
		this.spillnr = spillNr;
	}

	public List<Poengtabell> getPoengtabeller() {
		return poengTabeller;
	}

	public void setPoengtabeller(List<Poengtabell> poengtabeller) {
		this.poengTabeller = poengtabeller;
	}
	
}
