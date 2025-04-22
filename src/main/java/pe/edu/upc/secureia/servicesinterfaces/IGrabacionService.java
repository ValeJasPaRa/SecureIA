package pe.edu.upc.secureia.servicesinterfaces;

import pe.edu.upc.secureia.entities.Grabacion;

import java.util.List;

public interface IGrabacionService {

    public List<Grabacion> list();

    public void insert(Grabacion grab);

    public Grabacion listId(int id);

    public void update(Grabacion grab);

    public void delete(int id);
}
