package pe.edu.upc.secureia.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.secureia.dtos.ActividadDTO;
import pe.edu.upc.secureia.entities.Actividad;
import pe.edu.upc.secureia.servicesinterfaces.IActividadService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/actividad")
public class ActividadController {

    @Autowired
    private IActividadService actS;

    @GetMapping("/listar")
    public List<ActividadDTO> listar() {
        return actS.list().stream().map(y->{
            ModelMapper m = new ModelMapper();
            return m.map(y, ActividadDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping("/insertar")
    public void insertar(@RequestBody ActividadDTO u_dto) {
        ModelMapper m = new ModelMapper();
        Actividad usuario = m.map(u_dto, Actividad.class);
        actS.insert(usuario);
    }

    @GetMapping("/listarid/{id}")
    public ActividadDTO buscarId(@PathVariable("id") int id) {
        ModelMapper m=new ModelMapper();
        ActividadDTO dto=m.map(actS.listId(id),ActividadDTO.class);
        return dto;
    }

    @PutMapping("/modificar")
    public void modificar(@RequestBody ActividadDTO dto) {
        ModelMapper m=new ModelMapper();
        Actividad u=m.map(dto,Actividad.class);
        actS.update(u);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable("id") int id) {actS.delete(id);}

}
