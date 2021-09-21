package gn.jonas.crudapiexercise.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import gn.jonas.crudapiexercise.dto.ClientDTO;
import gn.jonas.crudapiexercise.services.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

	@Autowired
	private ClientService service;

	@GetMapping
	public ResponseEntity<Page<ClientDTO>> fetchAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "linesPerPage", defaultValue = "5") Integer linerPerPage,
			@RequestParam(name = "direction", defaultValue = "ASC") String direction,
			@RequestParam(name = "orderBy", defaultValue = "id") String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linerPerPage, Direction.valueOf(direction), orderBy);

		Page<ClientDTO> result = service.fetchAllPaged(pageRequest);

		return ResponseEntity.ok(result);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> fetchById(@PathVariable Long id) {
		ClientDTO result = service.fetchById(id);

		return ResponseEntity.ok(result);
	}

	@PostMapping
	public ResponseEntity<ClientDTO> add(@RequestBody ClientDTO client) {
		client = service.add(client);
		
		UriComponentsBuilder uribuiBuilder = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}");
		URI uri = uribuiBuilder.buildAndExpand(client.getId()).toUri();

		return ResponseEntity.created(uri).body(client);
	}

}
