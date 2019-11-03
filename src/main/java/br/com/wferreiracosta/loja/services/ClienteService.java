package br.com.wferreiracosta.loja.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.wferreiracosta.loja.domain.Cidade;
import br.com.wferreiracosta.loja.domain.Cliente;
import br.com.wferreiracosta.loja.domain.Endereco;
import br.com.wferreiracosta.loja.domain.enums.Perfil;
import br.com.wferreiracosta.loja.domain.enums.TipoCliente;
import br.com.wferreiracosta.loja.dto.ClienteDTO;
import br.com.wferreiracosta.loja.dto.ClienteNewDTO;
import br.com.wferreiracosta.loja.repositories.ClienteRepository;
import br.com.wferreiracosta.loja.repositories.EnderecoRepository;
import br.com.wferreiracosta.loja.security.UserSS;
import br.com.wferreiracosta.loja.services.exceptions.AuthorizationException;
import br.com.wferreiracosta.loja.services.exceptions.DataIntegrityException;
import br.com.wferreiracosta.loja.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private ClienteRepository repo;
	@Autowired
	private EnderecoRepository repoEndereco;

	public Cliente find(Integer id) {
		UserSS user = UserService.authenticated();
		
		if(user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso Negado!!!");
		}
		
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = this.find(obj.getId());
		this.updateData(newObj, obj);
		return repo.save(newObj);
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

	public void delete(Integer id) {
		this.find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir porque há entidades relacionadas");
		}
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null, null);
	}

	public Cliente fromDTO(ClienteNewDTO objNewDTO) {
		Cliente cliente = new Cliente(null, objNewDTO.getNome(), objNewDTO.getEmail(), objNewDTO.getCpfOuCnpj(),
				TipoCliente.toEnum(objNewDTO.getTipo()), pe.encode(objNewDTO.getSenha()));
		Cidade cidade = new Cidade(objNewDTO.getCidadeId(), null, null);
		Endereco endereco = new Endereco(null, objNewDTO.getLogradouro(), objNewDTO.getNumero(),
				objNewDTO.getComplemento(), objNewDTO.getBairro(), objNewDTO.getCep(), cliente, cidade);
		cliente.getEnderecos().add(endereco);
		cliente.getTelefones().add(objNewDTO.getTelefone1());
		if (objNewDTO.getTelefone2() != null) {
			cliente.getTelefones().add(objNewDTO.getTelefone2());
		}
		if (objNewDTO.getTelefone3() != null) {
			cliente.getTelefones().add(objNewDTO.getTelefone3());
		}
		return cliente;
	}

	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		repoEndereco.saveAll(obj.getEnderecos());
		return obj;
	}

}