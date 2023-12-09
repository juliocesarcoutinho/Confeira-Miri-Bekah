package br.com.miribekah.service;

import br.com.miribekah.dto.CepDTO;
import br.com.miribekah.dto.ConsultaCnpjDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PessoaUserService {

    public CepDTO consultaCep(String cep) {
        return new RestTemplate()
                .getForEntity("https://viacep.com.br/ws/" + cep + "/json/", CepDTO.class)
                .getBody();
    }

    public ConsultaCnpjDTO consultaCnpjWs(String cnpj) {
        return new RestTemplate()
                .getForEntity("https://receitaws.com.br/v1/cnpj/" + cnpj, ConsultaCnpjDTO.class)
                .getBody();
    }
    
}
