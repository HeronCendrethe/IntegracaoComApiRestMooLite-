package br.com.alura.mvc.mudi.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.mvc.mudi.dto.ReqNovoPedido;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.User;
import br.com.alura.mvc.mudi.repository.PedidoRepository;
import br.com.alura.mvc.mudi.repository.UserRepository;

@Repository
public class PedidoService {

	private final PedidoRepository pedidoRepository;
	private final UserRepository userRepository;

	public PedidoService(PedidoRepository pedidoRepository, UserRepository userRepository) {
		super();
		this.pedidoRepository = pedidoRepository;
		this.userRepository = userRepository;
	}
	
	public List<Pedido> lista(String username) {

//		String username  = SecurityContextHolder
//				.getContext()
//				.getAuthentication()
//				.getName();

		Pageable pageable = PageRequest.of(0, 5);
		System.out.println(pedidoRepository.findAllByUser(username, pageable));
		return pedidoRepository.findAllByUser(username, pageable);
	}

	public ResponseEntity<ReqNovoPedido> novoPedido(@Valid ReqNovoPedido requisicao, BindingResult result,
		UriComponentsBuilder uriBuilder) {
		
		System.out.println(requisicao);
		Pedido pedido = requisicao.toPedido();
		String username = "heron";

		Optional<User> usuario = userRepository.findByUsername(username);
		pedido.setUser(usuario.get());
		pedidoRepository.save(pedido);
		URI uri = uriBuilder.path("pedidos/{id}").buildAndExpand(pedido.getId()).toUri();
		return ResponseEntity.created(uri).body(new ReqNovoPedido());
	}

	public ResponseEntity<ReqNovoPedido> deletaPedido(@RequestBody ReqNovoPedido reqNovoPedido,
			UriComponentsBuilder uriBuilder) {
		
		

		URI uri = uriBuilder.path("/pedido/{id}").buildAndExpand(reqNovoPedido.getId()).toUri();

		pedidoRepository.deletarById(reqNovoPedido.getId());
		return ResponseEntity.created(uri).build();

	}

}
