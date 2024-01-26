package org.sid.dao;

import org.sid.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ICompteRepository extends JpaRepository<Compte, String> {

	@Query("SELECT c FROM Compte c WHERE c.codeCpte = :x")
	 Compte findOneByCodeCompte(@Param("x")String codeCpte);
}
