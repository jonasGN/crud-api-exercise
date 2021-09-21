package gn.jonas.crudapiexercise.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import gn.jonas.crudapiexercise.dto.ClientDTO;
import gn.jonas.crudapiexercise.entities.Client;
import gn.jonas.crudapiexercise.repositories.ClientRepository;
import gn.jonas.crudapiexercise.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {

	@Autowired
	public ClientRepository repository;

	public Page<ClientDTO> fetchAllPaged(PageRequest pageRequest) {
		Page<Client> entities = repository.findAll(pageRequest);

		return entities.map(c -> new ClientDTO(c));
	}

	public ClientDTO fetchById(Long id) {
		Optional<Client> entity = repository.findById(id);
		entity.orElseThrow(() -> new ResourceNotFoundException(id));

		return new ClientDTO(entity.get());
	}

	public ClientDTO add(ClientDTO client) {
		Client entity = new Client();
		dtoToEntity(client, entity);
		entity = repository.save(entity);

		return new ClientDTO(entity);
	}

	private void dtoToEntity(ClientDTO dto, Client entity) {
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());
	}
}
