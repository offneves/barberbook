package br.com.barberbook.server.repository;

import br.com.barberbook.server.model.BarbershopModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;


@Repository
public interface BarbershopRepository extends JpaRepository<BarbershopModel, UUID> {

}
