package no.hvl.dat109.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import no.hvl.dat109.entity.Poengtabell;

@Repository
public interface PoengtabellRepo extends JpaRepository<Poengtabell, Integer> {
	List<Poengtabell> findByBrukernavn(String brukernavn);

	List<Poengtabell> findBySpillnr(int spillNr);
}
