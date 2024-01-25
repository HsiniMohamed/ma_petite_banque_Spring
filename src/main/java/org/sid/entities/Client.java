package org.sid.entities;

import java.io.Serializable;
import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor @NoArgsConstructor @Data @ToString
public class Client implements Serializable {

	@Id @GeneratedValue
	private Long code;
	private String nom;
	private String email;
	@OneToMany(mappedBy = "client",fetch=FetchType.LAZY)
	private Collection<Compte> comptes;
	
	public Client(String nom, String email) {
		super();
		this.nom = nom;
		this.email = email;
	}
	
	
}
