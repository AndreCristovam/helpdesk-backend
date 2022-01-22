package br.com.andrecristovam.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.andrecristovam.helpdesk.domain.Chamado;

public interface IChamadoRepository extends JpaRepository<Chamado, Integer> {

}
