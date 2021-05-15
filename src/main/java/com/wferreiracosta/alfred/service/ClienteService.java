package com.wferreiracosta.alfred.service;

import com.wferreiracosta.alfred.domain.Cliente;

import java.util.Optional;

public interface ClienteService {

    Optional<Cliente> findById(Integer id);

}
