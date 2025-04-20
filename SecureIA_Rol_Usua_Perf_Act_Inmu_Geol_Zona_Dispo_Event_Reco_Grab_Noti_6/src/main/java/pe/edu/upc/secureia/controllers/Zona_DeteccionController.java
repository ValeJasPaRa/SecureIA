package pe.edu.upc.secureia.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.secureia.dtos.Zona_DeteccionDTO;
import pe.edu.upc.secureia.entities.Zona_Deteccion;
import pe.edu.upc.secureia.servicesinterfaces.IZona_DeteccionService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/zona_deteccion")
public class Zona_DeteccionController {

    @Autowired
    private IZona_DeteccionService zonaS;

    @GetMapping("/listar")
    public List<Zona_DeteccionDTO> listar() {
        return zonaS.list().stream().map(y->{
            ModelMapper m = new ModelMapper();
            return m.map(y, Zona_DeteccionDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping("/insertar")
    public void insertar(@RequestBody Zona_DeteccionDTO dto) {
        ModelMapper m = new ModelMapper();
        Zona_Deteccion zon = m.map(dto, Zona_Deteccion.class);
        zonaS.insert(zon);
    }

    @GetMapping("/listarid/{id}")
    public Zona_DeteccionDTO buscarId(@PathVariable("id") int id) {
        ModelMapper m=new ModelMapper();
        Zona_DeteccionDTO dto=m.map(zonaS.listId(id),Zona_DeteccionDTO.class);
        return dto;
    }

    @PutMapping("/modificar")
    public void modificar(@RequestBody Zona_DeteccionDTO dto) {
        ModelMapper m=new ModelMapper();
        Zona_Deteccion u=m.map(dto,Zona_Deteccion.class);
        zonaS.update(u);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable("id") int id) {zonaS.delete(id);}

}
