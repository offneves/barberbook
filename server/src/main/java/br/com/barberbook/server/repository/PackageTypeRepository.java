package br.com.barberbook.server.repository;

import br.com.barberbook.server.model.PackageTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PackageTypeRepository extends JpaRepository<PackageTypeModel, Integer> {

}
