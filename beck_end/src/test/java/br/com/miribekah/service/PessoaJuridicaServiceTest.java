package br.com.miribekah.service;

import br.com.miribekah.config.ExcepetionJava;
import br.com.miribekah.controller.PessoaController;
import br.com.miribekah.enums.TipoEndereco;
import br.com.miribekah.enums.TipoPessoa;
import br.com.miribekah.model.Endereco;
import br.com.miribekah.model.PessoaJuridica;
import br.com.miribekah.repository.PessoaFisicaRepository;
import br.com.miribekah.repository.PessoaJuridicaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PessoaJuridicaServiceTest {
    
    @Autowired
    private PessoaJuridicaService pessoaJuridicaService;
    
    @Autowired
    private PessoaController pessoaController;

    @Test
    void adicionar() throws ExcepetionJava {
        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        pessoaJuridica.setNome("Priscila Aquino Coutinho");
        pessoaJuridica.setRazaoSocial("Priscila Aquino Coutinho - ME");
        pessoaJuridica.setNomeFantasia("BelaModa");
        pessoaJuridica.setCnpj("74.586.136/0001-61");
        pessoaJuridica.setInscEstadual("520.138.322.669");
        pessoaJuridica.setInscMunicipal("02987");
        pessoaJuridica.setEmail("juliocesar.coutinho@outlook.com");
        pessoaJuridica.setTelefone("(16)2261-4596");
        pessoaJuridica.setTipoPessoa(TipoPessoa.JURIDICA);

        Endereco enderecoCobranca = new Endereco();
        enderecoCobranca.setBairro("Centro");
        enderecoCobranca.setCep("18950-039");
        enderecoCobranca.setComplemento("Casa");
        enderecoCobranca.setLogradouro("Rua do Lindos");
        enderecoCobranca.setNumero("1256");
        enderecoCobranca.setUf("SP");
        enderecoCobranca.setTipoEndereco(TipoEndereco.COBRANCA);
        enderecoCobranca.setPessoa(pessoaJuridica);
        enderecoCobranca.setCidade("Iparssu");


        Endereco enderecoEntrega = new Endereco();
        enderecoEntrega.setBairro("Vila Garrocino");
        enderecoEntrega.setCep("18950-009");
        enderecoEntrega.setComplemento("Casa");
        enderecoEntrega.setLogradouro("Rua dos Gatos");
        enderecoEntrega.setNumero("1256");
        enderecoEntrega.setUf("SP");
        enderecoEntrega.setTipoEndereco(TipoEndereco.ENTREGA);
        enderecoEntrega.setPessoa(pessoaJuridica);
        enderecoEntrega.setCidade("Iparssu");

        pessoaJuridica.getEnderecos().add(enderecoEntrega);
        pessoaJuridica.getEnderecos().add(enderecoCobranca);

        pessoaController.adicionar(pessoaJuridica);
        System.out.println("Pessoa Juridica Salva com ID: " + pessoaJuridica.getId() + 
                " e com nome de: " + pessoaJuridica.getNome());

        assertEquals(true, pessoaJuridica.getId() > 0);

        for (Endereco endereco : pessoaJuridica.getEnderecos()){
            assertEquals(true, endereco.getId() > 0);
        }
        assertEquals(2, pessoaJuridica.getEnderecos().size());
        
    }
}