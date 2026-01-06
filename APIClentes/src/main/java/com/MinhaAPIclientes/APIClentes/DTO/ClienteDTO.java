package com.MinhaAPIclientes.APIClentes.DTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ClienteDTO {
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    @Email(message = "Email inválido")
    @NotBlank(message = "Email é obrigatório")
    private String email;
    private String telefone;
    private String endereco;

    public ClienteDTO() {
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
