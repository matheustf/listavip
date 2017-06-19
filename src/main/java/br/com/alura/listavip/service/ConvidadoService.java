package br.com.alura.listavip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.enviadorEmail.enviadorEmail.EmailService;
import br.com.alura.listavip.model.Convidado;
import br.com.alura.listavip.repository.ConvidadoRepository;

@Service
public class ConvidadoService {
	
	@Autowired
	ConvidadoRepository convidadoRepository;
	
	public Iterable<Convidado> obterTodosConvidados(){
		return convidadoRepository.findAll();
	}
	
	public void salvarConvidado(Convidado novoConvidado){
		convidadoRepository.save(novoConvidado);
	}
	
	public void enviarEmail(String nome, String email){
		new EmailService().enviar(nome, email);
	}

}
