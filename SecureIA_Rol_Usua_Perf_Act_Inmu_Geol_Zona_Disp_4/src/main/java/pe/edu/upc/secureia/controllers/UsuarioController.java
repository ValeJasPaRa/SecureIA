package pe.edu.upc.secureia.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.secureia.dtos.GeolocalizacionDTO;
import pe.edu.upc.secureia.dtos.UsuarioDTO;
import pe.edu.upc.secureia.entities.Usuario;
import pe.edu.upc.secureia.servicesinterfaces.IUsuarioService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioS;

    @GetMapping("/listar")
    public List<UsuarioDTO> listar() {
        return usuarioS.list().stream().map(y->{
            ModelMapper m = new ModelMapper();
            return m.map(y, UsuarioDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping("/insertar")
    public void insertar(@RequestBody UsuarioDTO u_dto) {
        ModelMapper m = new ModelMapper();
        Usuario usuario = m.map(u_dto, Usuario.class);
        usuarioS.insert(usuario);
    }

    @GetMapping("/listarid/{id}")
    public UsuarioDTO buscarId(@PathVariable("id") int id) {
        ModelMapper m=new ModelMapper();
        UsuarioDTO dto=m.map(usuarioS.listId(id),UsuarioDTO.class);
        return dto;
    }

    @PutMapping("/modificar")
    public void modificar(@RequestBody UsuarioDTO dto) {
        ModelMapper m=new ModelMapper();
        Usuario u=m.map(dto,Usuario.class);
        usuarioS.update(u);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable("id") int id) {usuarioS.delete(id);}
}
