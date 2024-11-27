package imb.lh_p3.clinica.controller;

import imb.lh_p3.clinica.entity.Pedido;
import imb.lh_p3.clinica.exceptions.ElementeYaExisteException;
import imb.lh_p3.clinica.exceptions.ElementoNoExisteException;
import imb.lh_p3.clinica.service.IPedidoService;
import imb.lh_p3.clinica.util.DTOResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class PedidoController {

    @Autowired
    private IPedidoService service;

    @GetMapping("/pedido")
    public ResponseEntity<DTOResponse<List<Pedido>>> mostrarTodosLosPedidos(){
        List<Pedido> lista = service.mostrarTodos();
        if(lista.isEmpty()){
            throw new ElementoNoExisteException("No hay pedidos registrados");
        }else{
            DTOResponse<List<Pedido>> dto = new DTOResponse<>(200, "Lista de pedidos", lista);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        }
    }

    @GetMapping("/pedido/{id}")
    public ResponseEntity<DTOResponse<Pedido>> mostrarPedidoPorId(@PathVariable("id") Long id){
        Pedido pedido = service.mostrarPorId(id);
        if(pedido == null){
            throw new ElementoNoExisteException("El pedido con el id "+ id + " no existe");
        }else{
            DTOResponse<Pedido> dto = new DTOResponse<>(200, "Pedido:", pedido);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        }
    }

    @PostMapping("/pedido")
    public ResponseEntity<DTOResponse<Pedido>> guardarPedido(@Valid @RequestBody Pedido pedido){
        if(service.existe(pedido.getIdPedido())){
            throw new ElementeYaExisteException("Ya hay guardado un pedido");
        }else{
            DTOResponse<Pedido> dto = new DTOResponse<>(201, "Pedido guardado correctamente", service.guardar(pedido));
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        }
    }

    @PutMapping("/pedido")
    public ResponseEntity<DTOResponse<Pedido>> actualizarPedido(@Valid @RequestBody Pedido pedido){
        if(service.existe(pedido.getIdPedido())){
            DTOResponse<Pedido> dto = new DTOResponse<>(200, "Pedido actualizado correctamente", service.guardar(pedido));
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        }else{
            throw new ElementoNoExisteException("El pedido que esta tratando de actualizar no existe");
        }
    }

    @DeleteMapping("/pedido/{id}")
    public ResponseEntity<DTOResponse<?>> eliminarPedido(@PathVariable("id")Long id){
        if(service.existe(id)){
            service.eliminar(id);
            DTOResponse<?> dto = new DTOResponse<>(200, "Pedido eliminado correctamente", null);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        }else{
            throw new ElementoNoExisteException("El pedido a eliminar no existe");
        }
    }


}
