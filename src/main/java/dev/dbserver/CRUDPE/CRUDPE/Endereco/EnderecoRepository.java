package dev.dbserver.CRUDPE.CRUDPE.Endereco;

import dev.dbserver.CRUDPE.CRUDPE.Endereco.Domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
