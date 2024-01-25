package org.sid.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("CE")
@AllArgsConstructor @NoArgsConstructor @Data
public class CompteEpargne extends Compte {

	private double taux;
}
