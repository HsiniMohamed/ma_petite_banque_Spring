package org.sid.entities;

import java.util.Collection;
import java.util.Date;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("CE")
@AllArgsConstructor @NoArgsConstructor @Data
public class CompteEpargne extends Compte {

	public CompteEpargne(String codeCompte, Date dateCreation, double solde, Client client,
			Collection<Operation> operations, double taux) {
		super(codeCompte, dateCreation, solde, client, operations);
		this.taux = taux;
	}

	private double taux;
}
