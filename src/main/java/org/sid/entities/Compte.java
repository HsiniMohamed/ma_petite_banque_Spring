package org.sid.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_CPTE",discriminatorType = DiscriminatorType.STRING,length = 2)
@Data @AllArgsConstructor @NoArgsConstructor
public abstract class Compte implements Serializable {

	@Id
	private String codeCompte;
	private Date dateCreation;
	private double solde;
	@ManyToOne
	@JoinColumn(name="CODE_CLI")
	private Client  client;
	@OneToMany(mappedBy = "compte")
	private Collection<Operation> operations;
}
