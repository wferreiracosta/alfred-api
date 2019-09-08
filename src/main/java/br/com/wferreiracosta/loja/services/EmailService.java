package br.com.wferreiracosta.loja.services;

import org.springframework.mail.SimpleMailMessage;

import br.com.wferreiracosta.loja.domain.Pedido;

public interface EmailService {
	void sendOrderConfirmationEmail(Pedido obj);
	void sendEmail(SimpleMailMessage msg);
}
