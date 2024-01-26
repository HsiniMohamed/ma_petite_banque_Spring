package org.sid.entities;

import java.util.Date;
import java.util.Collection;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@DiscriminatorValue("CC")
@Data @AllArgsConstructor @NoArgsConstructor 
public class CompteCourant extends Compte {

	private double decouvert;

	public CompteCourant(String codeCompte, Date dateCreation, double solde, Client client,
			Collection<Operation> operations, double decouvert) {
		super(codeCompte, dateCreation, solde, client, operations);
		this.decouvert = decouvert;
	}
}
