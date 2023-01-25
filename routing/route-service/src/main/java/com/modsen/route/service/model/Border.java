package com.modsen.route.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "borders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Border implements Serializable {

    @EmbeddedId
    private BorderId id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "country_code",
            insertable = false,
            updatable = false
    )
    private Country country;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "border_country_code",
            insertable = false,
            updatable = false
    )
    private Country borderCountry;

    @Override
    public String toString() {
        return "Border{" +
                "id=" + id +
                '}';
    }
}
