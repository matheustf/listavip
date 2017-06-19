package br.com.alura.listavip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.alura.enviadorEmail.enviadorEmail.EmailService;
import br.com.alura.listavip.model.Convidado;
import br.com.alura.listavip.repository.ConvidadoRepository;
import br.com.alura.listavip.service.ConvidadoService;

@Controller
public class ConvidadoController {
	
	@Autowired
	ConvidadoService convidadoService;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("listaconvidados")
	public String listaConvidados(Model model) {

		Iterable<Convidado> convidados = convidadoService.obterTodosConvidados();

		model.addAttribute("convidados", convidados);

		return "listaconvidados";
	}

	@RequestMapping(value = "salvar", method = RequestMethod.POST)
	public String salvar(@RequestParam("nome") String nome, @RequestParam("email") String email,
			@RequestParam("telefone") String telefone, Model model) {
		
		Convidado novoConvidado = new Convidado(nome, email, telefone);
		
		convidadoService.salvarConvidado(novoConvidado);
		
		convidadoService.enviarEmail(nome, email);

		Iterable<Convidado> convidados = convidadoService.obterTodosConvidados();

		model.addAttribute("convidados", convidados);

		return "listaconvidados";
	}

}
