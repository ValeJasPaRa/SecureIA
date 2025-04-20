package pe.edu.upc.secureia.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.secureia.dtos.InmuebleDTO;
import pe.edu.upc.secureia.entities.Inmueble;
import pe.edu.upc.secureia.servicesinterfaces.IInmuebleService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/inmueble")
public class InmuebleController {

    @Autowired
    private IInmuebleService inmuS;


    @GetMapping("/listar")
    public List<InmuebleDTO> listar() {
        return inmuS.list().stream().map(y->{
            ModelMapper m = new ModelMapper();
            return m.map(y, InmuebleDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping("/insertar")
    public void insertar(@RequestBody InmuebleDTO i_dto) {
        ModelMapper m = new ModelMapper();
        Inmueble inmueb = m.map(i_dto, Inmueble.class);
        inmuS.insert(inmueb);
    }

    @GetMapping("/listarid/{id}")
    public InmuebleDTO buscarId(@PathVariable("id") int id) {
        ModelMapper m=new ModelMapper();
        InmuebleDTO dto=m.map(inmuS.listId(id),InmuebleDTO.class);
        return dto;
    }

    @PutMapping("/modificar")
    public void modificar(@RequestBody InmuebleDTO dto) {
        ModelMapper m=new ModelMapper();
        Inmueble i=m.map(dto,Inmueble.class);
        inmuS.update(i);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable("id") int id) {inmuS.delete(id);}

}

