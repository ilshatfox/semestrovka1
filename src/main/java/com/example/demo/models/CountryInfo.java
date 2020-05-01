package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import com.example.demo.validator.FieldMatch;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "country_info")
public class CountryInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 128)
    @Column(unique = true)
    private String countryName;

    @NotNull
    private Integer infected;

    @NotNull
    private Integer cured;

    @NotNull
    private Integer critical;

    @NotNull
    private Integer died;

}
