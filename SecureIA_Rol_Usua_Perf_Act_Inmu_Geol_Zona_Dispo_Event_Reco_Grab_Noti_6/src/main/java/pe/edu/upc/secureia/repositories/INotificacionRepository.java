package pe.edu.upc.secureia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.secureia.entities.Notificacion;

@Repository
public interface INotificacionRepository extends JpaRepository<Notificacion, Integer> {
}
