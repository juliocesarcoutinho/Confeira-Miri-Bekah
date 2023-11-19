package br.com.miribekah.service;

import br.com.miribekah.config.ExcepetionJava;
import br.com.miribekah.model.PessoaJuridica;
import br.com.miribekah.model.Usuario;
import br.com.miribekah.repository.PessoaJuridicaRepository;
import br.com.miribekah.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;

@Service
public class PessoaJuridicaService {

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private SendEnvioEmailService sendEnvioEmailService;

    public PessoaJuridica adicionar(PessoaJuridica pessoaJuridica) throws ExcepetionJava {

        if (pessoaJuridica == null) {
            throw new ExcepetionJava("Pessoa Juridica não pode ser Null");
        }
        if (pessoaJuridica.getId() == null && pessoaJuridicaRepository.findByCnpj(pessoaJuridica.getCnpj()) != null) {
            throw new ExcepetionJava("Já existe uma pessoa com o Cnpj: " + pessoaJuridica.getCnpj() + " cadastrado");
        }

        pessoaJuridicaRepository.save(pessoaJuridica);
        Usuario usuarioPj = usuarioRepository.findUserByPessoa(pessoaJuridica.getId(), pessoaJuridica.getEmail());
        
        if (usuarioPj == null){
            String constraint = usuarioRepository.consultaConstranitAcesso();
            if (constraint != null){
                jdbcTemplate.execute("begin; alter table usuario_acesso drop constraint " + constraint + "; commit; ");
            }
            usuarioPj = new Usuario();
            usuarioPj.setDataAtualSenha(Calendar.getInstance().getTime());
            usuarioPj.setPessoa(pessoaJuridica);
            usuarioPj.setLogin(pessoaJuridica.getEmail());

            String senha = "" + Calendar.getInstance().getTimeInMillis();
            String senhaCript = new BCryptPasswordEncoder().encode(senha);

            usuarioPj.setSenha(senhaCript);
            usuarioPj = usuarioRepository.save(usuarioPj);

            usuarioRepository.insereAcessoUser(usuarioPj.getId());
//            usuarioRepository.insereAcessoUserPj(usuarioPj.getId(), "ROLE_ADMIN");

            /*Envio do email com dados de acesso a Loja*/
            try {
                // Vai ler o conteúdo do arquivo "template_email.html"
                String templateEmail = Files.readString(Paths.get("src/main/resources/templates/template_email.html"));

                // Substitui as variáveis de substituição no modelo HTML pelo valor real
                templateEmail = templateEmail.replace("{{email}}", pessoaJuridica.getEmail());
                templateEmail = templateEmail.replace("{{senha}}", senha);

                // Envie o e-mail usando o modelo personalizado
                sendEnvioEmailService.enviarEmailHtml("Acesso Gerado para Loja Virtual Confeitaria Miribekah", templateEmail, pessoaJuridica.getEmail());
                System.out.println("Email enviado com sucesso");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            
        }
        return pessoaJuridica;

    }

}

    
