package com.javaschool.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE options SET deleted=true WHERE id=?")
//@Where(clause = "deleted = false")
@Table(name = "options", uniqueConstraints={@UniqueConstraint(columnNames={"name"})})
public class Option extends AbstractModel{

    @Column(name = "name", nullable=false)
    private String name;

    @Column(name = "price", nullable=false)
    private int price;

    @Column(name = "serviceCost")
    private int serviceCost;

    @Column(name = "deleted")
    private boolean deleted = false;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "mandatoryOption_option",
            joinColumns = @JoinColumn(name = "option_id"),
            inverseJoinColumns = @JoinColumn(name = "mandatoryOption_id"))
    private Set<Option> mandatoryOptions;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "bannedOption_option",
            joinColumns = @JoinColumn(name = "option_id"),
            inverseJoinColumns = @JoinColumn(name = "bannedOption_id"))
    private Set<Option> bannedOptions;

}
