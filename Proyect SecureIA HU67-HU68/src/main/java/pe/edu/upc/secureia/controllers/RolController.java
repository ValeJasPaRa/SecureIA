package pe.edu.upc.secureia.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.secureia.dtos.CantidadUsuariosxTipoRolDTO;
import pe.edu.upc.secureia.dtos.RolDTO;
import pe.edu.upc.secureia.entities.Rol;
import pe.edu.upc.secureia.servicesinterfaces.IRolService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rol")
public class RolController {

    @Autowired
    private IRolService rolS;

    @GetMapping("/listar")
    public List<RolDTO> listar() {
       return rolS.list().stream().map(x->{
           ModelMapper m=new ModelMapper();
           return m.map(x,RolDTO.class);
       }).collect(Collectors.toList());
    }

    @PostMapping("/insertar")
    public void insertar(@RequestBody RolDTO dto) {
        ModelMapper m=new ModelMapper();
        Rol r=m.map(dto,Rol.class);
        rolS.insert(r);
    }

    @GetMapping("/listarid/{id}")
    public RolDTO buscarId(@PathVariable("id") int id) {
        ModelMapper m=new ModelMapper();
        RolDTO dto=m.map(rolS.listId(id),RolDTO.class);
        return dto;
    }

    @PutMapping("/modificar")
    public void modificar(@RequestBody RolDTO dto) {
        ModelMapper m=new ModelMapper();
        Rol r=m.map(dto,Rol.class);
        rolS.update(r);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable("id") int id) {rolS.delete(id);}

    //Cantidad de Usuarios por TipoRol
    @GetMapping("/cantidadUsersxTypeRol")
    public List<CantidadUsuariosxTipoRolDTO> CantUsuariosxTRol(){
        List<CantidadUsuariosxTipoRolDTO> CantidadUsuariosxTipoRolDTO =new ArrayList<>();
        List<String[]> fila=rolS.ContarUsuariosxTipoRol();
        for(String[]columna:fila){
            CantidadUsuariosxTipoRolDTO dto=new CantidadUsuariosxTipoRolDTO();
            dto.setTipoRol(columna[0]);
            dto.setCantidad_de_Usuarios(Double.parseDouble(columna[1]));
            CantidadUsuariosxTipoRolDTO.add(dto);
        }
        return CantidadUsuariosxTipoRolDTO;
    }

}
