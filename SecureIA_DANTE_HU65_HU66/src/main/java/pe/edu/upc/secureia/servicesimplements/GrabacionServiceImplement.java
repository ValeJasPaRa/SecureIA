package pe.edu.upc.secureia.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.secureia.entities.Grabacion;
import pe.edu.upc.secureia.repositories.IGrabacionRepository;
import pe.edu.upc.secureia.servicesinterfaces.IGrabacionService;

import java.util.List;

@Service
public class GrabacionServiceImplement implements IGrabacionService {

    @Autowired
    private IGrabacionRepository grabR;

    @Override
    public List<Grabacion> list() {
        return grabR.findAll();
    }

    @Override
    public void insert(Grabacion grab) {
        grabR.save(grab);
    }

    @Override
    public Grabacion listId(int id) {
        return grabR.findById(id).orElse(new Grabacion());
    }

    @Override
    public void update(Grabacion grab) {
        grabR.save(grab);
    }

    @Override
    public void delete(int id) {
        grabR.deleteById(id);
    }
}
