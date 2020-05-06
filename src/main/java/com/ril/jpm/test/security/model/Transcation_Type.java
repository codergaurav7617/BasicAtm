package com.ril.jpm.test.security.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Transcation_Type {
    @Id@Getter@Setter
    private String txn_type;
}
