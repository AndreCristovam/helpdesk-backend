package br.com.andrecristovam.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.andrecristovam.helpdesk.domain.Tecnico;

public interface ITecnicoRepository extends JpaRepository<Tecnico, Integer> {

}
