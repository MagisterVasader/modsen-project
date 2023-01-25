package com.modsen.route.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BorderId implements Serializable {

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "border_country_code")
    private String borderCountryCode;

    @Override
    public String toString() {
        return "BorderId{" +
                "countryCode='" + countryCode + '\'' +
                ", borderCountryCode='" + borderCountryCode + '\'' +
                '}';
    }
}
