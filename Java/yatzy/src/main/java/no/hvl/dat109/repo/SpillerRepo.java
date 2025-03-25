package no.hvl.dat109.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import no.hvl.dat109.yatzy.Spiller;

@Repository
public interface SpillerRepo extends JpaRepository<Spiller, String> {
	
}
