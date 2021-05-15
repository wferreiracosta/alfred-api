package com.wferreiracosta.alfred.service.impl;

import com.wferreiracosta.alfred.domain.Cliente;
import com.wferreiracosta.alfred.repositories.ClienteRepository;
import com.wferreiracosta.alfred.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private final ClienteRepository repository;

    @Override
    public Optional<Cliente> findById(Integer id) {
        return this.repository.findById(id);
    }
}
