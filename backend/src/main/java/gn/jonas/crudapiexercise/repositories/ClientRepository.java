package gn.jonas.crudapiexercise.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gn.jonas.crudapiexercise.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
