package br.com.andrecristovam.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.andrecristovam.helpdesk.domain.Pessoa;

public interface IPessoaRepository extends JpaRepository<Pessoa, Integer> {

}
