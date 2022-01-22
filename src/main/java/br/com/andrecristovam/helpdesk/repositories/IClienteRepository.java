package br.com.andrecristovam.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.andrecristovam.helpdesk.domain.Cliente;

public interface IClienteRepository extends JpaRepository<Cliente, Integer> {

}
