package br.com.alura.mvc.mudi.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.User;
import br.com.alura.mvc.mudi.repository.PedidoRepository;
import br.com.alura.mvc.mudi.repository.UserRepository;
import br.com.alura.mvc.mudi.service.PedidoService;

@RestController
@RequestMapping("/api/home")
public class HomeRest {

	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private UserRepository userRepository;

	@Cacheable (value = "lista")
	@GetMapping("lista")
	public List<Pedido> getHome(String username) {		
		PedidoService pedidoService = new PedidoService(pedidoRepository, userRepository);
		return pedidoService.lista(username);
	}
}
