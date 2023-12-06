package br.com.miribekah.service;

import br.com.miribekah.config.ExcepetionJava;
import br.com.miribekah.enums.TipoPessoa;
import br.com.miribekah.model.PessoaFisica;
import br.com.miribekah.model.PessoaJuridica;
import br.com.miribekah.model.Usuario;
import br.com.miribekah.repository.PessoaFisicaRepository;
import br.com.miribekah.repository.UsuarioRepository;
import br.com.miribekah.util.ValidaCPF;
import br.com.miribekah.util.ValidadorCnpj;
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
public class PessoaFisicaService {
    
    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private SendEnvioEmailService sendEnvioEmailService;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public PessoaFisica adicionar(PessoaFisica fisica) throws ExcepetionJava {

        if (fisica == null) {
            throw new ExcepetionJava("Pessoa Fisica não pode ser null");
        }
        if (fisica.getTipoPessoa() == null) {
            fisica.setTipoPessoa(TipoPessoa.valueOf(TipoPessoa.FISICA.name()));
        }
        if (fisica.getId() == null && pessoaFisicaRepository.existeCpf(fisica.getCpf()) != null) {
            throw new ExcepetionJava("Ja existe um cadastro com o CPF: " + fisica.getCpf());
        }
        if (!ValidaCPF.isCPF(fisica.getCpf())) {
            throw new ExcepetionJava("CPF: " + fisica.getCpf() + "não é um CPF válido, verifique! ");
        }

        for (int i = 0; i < fisica.getEnderecos().size(); i++) {
            fisica.getEnderecos().get(i).setPessoa(fisica);
//            fisica.getEnderecos().get(i).setEmpresa(fisica);
        }
        fisica = pessoaFisicaRepository.save(fisica);

        Usuario usuarioPf = usuarioRepository.findUserByPessoa(fisica.getId(), fisica.getEmail());

        if (usuarioPf == null) {

            String constraint = usuarioRepository.consultaConstranitAcesso();
            if (constraint != null) {
                jdbcTemplate.execute("begin; alter table usuario_acesso drop constraint " + constraint + "; commit; ");
            }
            usuarioPf = new Usuario();
            usuarioPf.setDataAtualSenha(Calendar.getInstance().getTime());
            usuarioPf.setPessoa(fisica);
            usuarioPf.setLogin(fisica.getEmail());

            String senha = "" + Calendar.getInstance().getTimeInMillis();
            String senhaCript = new BCryptPasswordEncoder().encode(senha);

            usuarioPf.setSenha(senhaCript);
            usuarioPf = usuarioRepository.save(usuarioPf);

            usuarioRepository.insereAcessoUser(usuarioPf.getId());

            try {
                // Leia o conteúdo do arquivo "template_email.html"
                String templateEmail = Files.readString(Paths.get("src/main/resources/templates/template_email.html"));

                // Substitui as variáveis de substituição no modelo HTML pelo valor real
                templateEmail = templateEmail.replace("{{email}}", fisica.getEmail());
                templateEmail = templateEmail.replace("{{senha}}", senha);

                // Envie o e-mail usando o modelo personalizado
                sendEnvioEmailService.enviarEmailHtml("Acesso Gerado para Loja Virtual Confeitaria Miribekah", templateEmail, fisica.getEmail());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }


        }
        return fisica;
    }
    
}
