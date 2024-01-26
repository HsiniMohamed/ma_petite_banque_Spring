package org.sid.entities;

import java.util.Date;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("R")
@NoArgsConstructor 
public class Retrait extends Operation 

{public Retrait(Long numero, Date dateOperation, double montant, Compte compte) {
		super(numero, dateOperation, montant, compte);
		// TODO Auto-generated constructor stub
	}

	
}
