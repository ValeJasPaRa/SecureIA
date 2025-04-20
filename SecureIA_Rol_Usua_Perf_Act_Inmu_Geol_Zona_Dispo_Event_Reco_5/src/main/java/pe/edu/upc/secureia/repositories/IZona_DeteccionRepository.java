package pe.edu.upc.secureia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.secureia.entities.Zona_Deteccion;

@Repository
public interface IZona_DeteccionRepository extends JpaRepository<Zona_Deteccion, Integer> {
}
