package br.com.wferreiracosta.loja.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import br.com.wferreiracosta.loja.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {
	@Value("${default.sender}")
	private String sender;

	@Override
	public void sendOrderConfirmationEmail(Pedido obj) {
		SimpleMailMessage msg = this.prepareSimpleMailMessageFromPedido(obj);
		sendEmail(msg);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(obj.getCliente().getEmail());
		msg.setFrom(sender);
		msg.setSubject("Pedido Confirmado! Código: " + obj.getId());
		msg.setSentDate(new Date(System.currentTimeMillis()));
		msg.setText(obj.toString());
		return msg;
	}
}
