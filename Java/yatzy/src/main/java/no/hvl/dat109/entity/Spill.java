package no.hvl.dat109.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(schema = "yatzy")
public class Spill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer spillnr;

	@OneToMany
	@JoinColumn(name = "spillnr")
	private List<Poengtabell> poengtabeller;

	@NotNull
	private LocalDateTime tidopprettet;

	private LocalDateTime tidavsluttet;

	@ManyToOne
	@JoinColumn(name = "oppretterbrukernavn")
	private Spiller oppretter;
	

	public Spill(List<Poengtabell> poengtabeller) {
		super();
		this.poengtabeller = poengtabeller;
	}

	public Spill() {

	}

	public Integer getSpillnr() {
		return spillnr;
	}

	public void setSpillnr(Integer spillnr) {
		this.spillnr = spillnr;
	}

	public List<Poengtabell> getPoengtabeller() {
		return poengtabeller;
	}
	
	public int getAntallSpillere() {
		return poengtabeller.size();
	}

	public void setPoengtabeller(List<Poengtabell> poengtabeller) {
		this.poengtabeller = poengtabeller;
	}

	public LocalDateTime getTidopprettet() {
		return tidopprettet;
	}

	public void setTidopprettet(LocalDateTime tidopprettet) {
		this.tidopprettet = tidopprettet;
	}

	public LocalDateTime getTidavsluttet() {
		return tidavsluttet;
	}

	public void setTidavsluttet(LocalDateTime tidavsluttet) {
		this.tidavsluttet = tidavsluttet;
	}

	public Spiller getOppretter() {
		return oppretter;
	}

	public void setOppretter(Spiller oppretter) {
		this.oppretter = oppretter;
	}

	public boolean erSpillFerdig() {
		return this.tidavsluttet != null;
	}
	
	public List<String> getSpillereBrukernavn(){
		return this.poengtabeller.stream().map(t -> t.getPoengtabellId().getBrukernavn()).toList();
	}

}
