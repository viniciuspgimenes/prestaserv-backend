package br.com.prestaserv.backend.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ClienteId;
    private String Nome;
    private String Cpf;
    @OneToOne(
        orphanRemoval = true,
        cascade = CascadeType.ALL
    )
    private Endereco Endereco;

    public Cliente() {
    }

    public Cliente(Long clienteId, String nome, String cpf, br.com.prestaserv.backend.model.Endereco endereco) {
        ClienteId = clienteId;
        Nome = nome;
        Cpf = cpf;
        Endereco = endereco;
    }

    public Long getClienteId() {
        return ClienteId;
    }

    public void setClienteId(Long clienteId) {
        ClienteId = clienteId;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getCpf() {
        return Cpf;
    }

    public void setCpf(String cpf) {
        Cpf = cpf;
    }

    public br.com.prestaserv.backend.model.Endereco getEndereco() {
        return Endereco;
    }

    public void setEndereco(br.com.prestaserv.backend.model.Endereco endereco) {
        Endereco = endereco;
    }
}
