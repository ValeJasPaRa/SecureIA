package pe.edu.upc.secureia.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.secureia.dtos.GrabacionDTO;
import pe.edu.upc.secureia.entities.Grabacion;
import pe.edu.upc.secureia.servicesinterfaces.IGrabacionService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/grabacion")
public class GrabacionController {
    @Autowired
    private IGrabacionService grabS;

    @GetMapping("/listar")
    public List<GrabacionDTO> listar() {
        return grabS.list().stream().map(y->{
            ModelMapper m = new ModelMapper();
            return m.map(y, GrabacionDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping("/insertar")
    public void insertar(@RequestBody GrabacionDTO dto) {
        ModelMapper m = new ModelMapper();
        Grabacion g = m.map(dto, Grabacion.class);
        grabS.insert(g);
    }

    @GetMapping("/listarid/{id}")
    public GrabacionDTO buscarId(@PathVariable("id") int id) {
        ModelMapper m=new ModelMapper();
        GrabacionDTO dto=m.map(grabS.listId(id),GrabacionDTO.class);
        return dto;
    }

    @PutMapping("/modificar")
    public void modificar(@RequestBody GrabacionDTO dto) {
        ModelMapper m=new ModelMapper();
        Grabacion u=m.map(dto,Grabacion.class);
        grabS.update(u);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable("id") int id) {grabS.delete(id);}

}
