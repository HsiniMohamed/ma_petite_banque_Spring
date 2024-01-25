package org.sid.dao;

import org.sid.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICompteRepository extends JpaRepository<Compte, String> {

}
