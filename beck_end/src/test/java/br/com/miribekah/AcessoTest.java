package br.com.miribekah;

import br.com.miribekah.model.Acesso;
import br.com.miribekah.repository.AcessoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AcessoTest {
    
    @Autowired
    private WebApplicationContext wac;
    
    @Autowired
    private AcessoRepository acessoRepository;
    
    /*Teste de cadastro de acesso usando o Mock*/
    @Test
    public void testCadastrarAcesso(){
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        MockMvc mockMvc = builder.build();

        Acesso acesso = new Acesso();
        acesso.setDescricao("ROLE_ADMIN");

        ObjectMapper objectMapper =new ObjectMapper();
        try {
            ResultActions retornoApi = mockMvc
                    .perform(MockMvcRequestBuilders.post("/acessos")
                            .content(objectMapper.writeValueAsString(acesso))
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON));

            System.out.println("Retorno Api: " + retornoApi.andReturn().getResponse().getContentAsString());

            /*Coverte retorno da api em um objeto*/
            Acesso objetoRetorno = objectMapper
                    .readValue(retornoApi.andReturn()
                            .getResponse()
                            .getContentAsString(), Acesso.class);

            assertEquals(acesso.getDescricao(), objetoRetorno.getDescricao());
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
    }

    @Test /*Teste end-point Delete Mockito*/
    public void testRestApiDeleteAcesso() throws JsonProcessingException {
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        MockMvc mockMvc = builder.build();

        

        Acesso acesso = new Acesso();
        acesso.setDescricao("ROLE_TESTE_DELETE");
        acesso = acessoRepository.save(acesso);

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            ResultActions retornoApi = mockMvc.
                    perform(MockMvcRequestBuilders.delete("/acessos/{id}", acesso.getId())
                            .content(objectMapper.writeValueAsString(acesso))
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON));

            System.out.println("Retorno Api: " + retornoApi.andReturn().getResponse().getContentAsString());
            System.out.println("Status: " + retornoApi.andReturn().getResponse().getStatus());

            assertEquals("Acesso Removido", retornoApi.andReturn().getResponse().getContentAsString().trim());
            assertEquals(200, retornoApi.andReturn().getResponse().getStatus());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    
    
}
