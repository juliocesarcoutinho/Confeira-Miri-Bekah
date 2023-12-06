package br.com.miribekah;

import br.com.miribekah.config.ExcepetionJava;
import br.com.miribekah.controller.AcessoController;
import br.com.miribekah.model.Acesso;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BeckEndApplicationTests {
	
	@Autowired
	AcessoController acessoController;

	@Test
	public void testCadastraAcesso() throws ExcepetionJava {

		String descAcesso = "ROLE_ADMIN " + Calendar.getInstance().getTimeInMillis();

		Acesso acesso = new Acesso();
		acesso.setDescricao(descAcesso);

		assertEquals(true, acesso.getId() == null);

		acesso = acessoController.adicionar(acesso).getBody();

		assertEquals(true, acesso.getId() > 0);

		assertEquals(descAcesso, acesso.getDescricao());

		System.out.println("Acesso cadastrado com Sucesso e o id é: " + acesso.getId() + "\nCom descrição: " + acesso.getDescricao());

	}
	
	

	@Test 
	void contextLoads() {
	}

}
