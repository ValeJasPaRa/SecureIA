package pe.edu.upc.secureia.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.secureia.dtos.ContarGrabacionesxFechaDTO;
import pe.edu.upc.secureia.dtos.GrabacionDTO;
import pe.edu.upc.secureia.dtos.PromedioGrabDTO;
import pe.edu.upc.secureia.entities.Grabacion;
import pe.edu.upc.secureia.servicesinterfaces.IGrabacionService;

import java.time.LocalDate;
import java.util.ArrayList;
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

    @GetMapping("/ContarGrabacionesxFecha")
    public List<ContarGrabacionesxFechaDTO> ContarGrabxFecha(@RequestParam LocalDate fecha) {
        List<ContarGrabacionesxFechaDTO> dtoLista=new ArrayList<>();
        List<String[]> fila=grabS.contarGrabacionxFecha(fecha);
        for(String[]columna:fila){
            ContarGrabacionesxFechaDTO dto=new ContarGrabacionesxFechaDTO();
            dto.setCantidad(Double.parseDouble(columna[0]));
            dtoLista.add(dto);
        }
        return dtoLista;
    }

    @GetMapping("/PromedioTiempoGrabacionesxIdReco")
    public List<PromedioGrabDTO> ContarGrabxFecha(@RequestParam int ingresarId) {
        List<PromedioGrabDTO> dtoLista=new ArrayList<>();
        List<String[]> fila=grabS.PromedioGrabacionxId(ingresarId);
        for(String[]columna:fila){
            PromedioGrabDTO dto=new PromedioGrabDTO();
            dto.setId_reco(Integer.parseInt(columna[0]));
            dto.setDuracion_promedio(Double.parseDouble(columna[1]));
            dtoLista.add(dto);
        }
        return dtoLista;
    }
}
