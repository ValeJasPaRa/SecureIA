package pe.edu.upc.secureia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.secureia.entities.Evento_Dispositivo;

@Repository
public interface IEvento_DispositivoRepository extends JpaRepository<Evento_Dispositivo, Integer> {
}
