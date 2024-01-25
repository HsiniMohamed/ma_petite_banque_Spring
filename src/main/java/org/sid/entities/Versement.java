package org.sid.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("V")
@NoArgsConstructor
public class Versement extends Operation{

}
