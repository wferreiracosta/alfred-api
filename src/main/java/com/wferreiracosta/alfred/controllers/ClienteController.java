package com.wferreiracosta.alfred.controllers;

import com.wferreiracosta.alfred.controllers.exception.ObjectNotFoundException;
import com.wferreiracosta.alfred.dto.ClienteDTO;
import com.wferreiracosta.alfred.service.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clientes")
@RequiredArgsConstructor
@Slf4j
public class ClienteController {

    private final ClienteService service;

    @GetMapping(value = "/{id}")
    public ClienteDTO findById(@PathVariable Integer id) {
        log.info("[GET] Fazendo a busca de cliente com o id: {}", id);
        return this.service.findById(id).map(cliente -> {
            return ClienteDTO.builder()
                    .id(cliente.getId())
                    .nome(cliente.getNome())
                    .email(cliente.getEmail())
                    .cpfOuCnpj(cliente.getCpfOuCnpj())
                    .tipo(cliente.getTipo())
                    .telefones(cliente.getTelefones())
                    .enderecos(cliente.getEnderecos())
                    .build();
        }).orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! id: " + id + " Tipo:" + ClienteDTO.class.getName()
        ));
    }
}
