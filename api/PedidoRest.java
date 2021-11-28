package br.com.alura.mvc.mudi.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.mvc.mudi.dto.ReqNovoPedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;
import br.com.alura.mvc.mudi.repository.UserRepository;
import br.com.alura.mvc.mudi.service.PedidoService;

@RestController
@RequestMapping ("/api/pedido")
public class PedidoRest {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired 
	private UserRepository userRepository;
	
	@GetMapping("formulario") 
	public String formulario(ReqNovoPedido requisicao) {
		
		return "pedido/formulario";
	}
	
	@CacheEvict(value = "lista")
	@PostMapping("novo")
	public ResponseEntity<ReqNovoPedido> novo(@RequestBody@Valid ReqNovoPedido requisicao, BindingResult result,UriComponentsBuilder uriBuilder) {	
		PedidoService pedidosService = new PedidoService(pedidoRepository,userRepository);
		return pedidosService.novoPedido(requisicao, result, uriBuilder);
	}
	
	@CacheEvict(value = "lista")
	@DeleteMapping ("delete")
	public ResponseEntity<ReqNovoPedido> deletar(@RequestBody ReqNovoPedido reqNovoPedido,UriComponentsBuilder uriBuilder) {			
		PedidoService pedidosService = new PedidoService(pedidoRepository,userRepository);	
		return pedidosService.deletaPedido(reqNovoPedido, uriBuilder);		
}



	
}


