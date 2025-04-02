package no.hvl.dat109.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import no.hvl.dat109.entity.Admin;

@Repository
public interface AdminRepo extends JpaRepository<Admin, String> {
}
