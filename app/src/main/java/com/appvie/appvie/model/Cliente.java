package com.appvie.appvie.model;


import com.appvie.appvie.Building;

import java.io.Serializable;

public class Cliente implements Serializable {

    public String nome;
    public String cpf;
    public String email;
    public String ddd;
    public String naturaliadde; //
    public String sexo; //
    public String estadoCivil; //
    public String tipoSanguineo;
    public String telephone;
    public String integratorId;
    public String clienteId;
    public String dateCreation;



    public Building building = null;


    public Cliente(String nome, String cpf, String email, String ddd, String telephone,String clienteId, String integratorId,
                   String dateCreation,
                   Building building) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.ddd = ddd;
        this.telephone = telephone;
        this.integratorId = integratorId;
        this.dateCreation = dateCreation;
        this.building = building;
        this.clienteId = clienteId;

    }

    public Cliente(String nome, String email, String ddd, String telephone, String clienteId, String integratorId, String dateCreation) {
        this.nome = nome;
        this.email = email;
        this.ddd = ddd;
        this.telephone = telephone;
        this.integratorId = integratorId;
        this.dateCreation = dateCreation;
        this.clienteId = clienteId;
    }


    public String getNome() {
        return nome;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getDdd() {
        return ddd;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getIntegratorId() {
        return integratorId;
    }

    public String getNaturaliadde() {
        return naturaliadde;
    }

    public String getSexo() {
        return sexo;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public String getTipoSanguineo() {
        return tipoSanguineo;
    }
}
