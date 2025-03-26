package no.hvl.dat109.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import no.hvl.dat109.entity.Poengtabell;
import no.hvl.dat109.entity.PoengtabellId;

@Repository
public interface PoengtabellRepo extends JpaRepository<Poengtabell, PoengtabellId> {
	
	@Query("SELECT p FROM Poengtabell p WHERE p.id.brukernavn = :brukernavn")
	List<Poengtabell> findByBrukernavn(@Param("brukernavn") String brukernavn);
	
	@Query("SELECT p FROM Poengtabell p WHERE p.id.brukernavn = :brukernavn AND p.id.spillnr = :spillnr")
	Poengtabell findByBrukernavnAndSpillnr(@Param("brukernavn") String brukernavn, @Param("spillnr") int spillnr);


}
