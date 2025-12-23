package com.MinhaAPIclientes.APIClentes.controller;

import com.MinhaAPIclientes.APIClentes.Model.Cliente;
import com.MinhaAPIclientes.APIClentes.Repository.ClienteRepository;
import com.MinhaAPIclientes.APIClentes.service.ClienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController: Sua classe do Controller é anotada com @RestController, indicando que ela lida com requisições web, serializando as respostas para JSON.
@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PostMapping("")
    public Cliente salvar(@RequestBody Cliente cliente) {
        return service.salvar(cliente);

    }

    @GetMapping("")
    public List<Cliente> buscarTodos() {
        return service.buscarTodos();

    }

    @GetMapping("/{id}")
    public Cliente buscarporID(@PathVariable("id") Long id) {
        Cliente cliente = service.buscarPorID(id);
        return cliente;


    }
    @PutMapping("/{id}")
    public Cliente atualizarCliente(@PathVariable("id") Long id, @RequestBody Cliente clienteNovo ) {
        Cliente clientedoBanco = service.buscarPorID(id);
        clientedoBanco.setNome(clienteNovo.getNome());
        clientedoBanco.setEmail(clienteNovo.getEmail());
        return service.atualizarCliente(id, clientedoBanco);



    }
    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable("id") Long id){
        service.deletarUsuario(id);
        System.out.println("Deletado");



    }
}




