package org.sid.entities;

import java.util.Date;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("V")
@NoArgsConstructor
public class Versement extends Operation{public Versement(Long numero, Date dateOperation, double montant, Compte compte) {
		super(numero, dateOperation, montant, compte);
		// TODO Auto-generated constructor stub
	}

}
