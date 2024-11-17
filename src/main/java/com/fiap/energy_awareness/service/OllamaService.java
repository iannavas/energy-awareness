package com.fiap.energy_awareness.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.api.OllamaModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OllamaService {

    @Autowired
    private ChatModel chatModel;

    @Autowired
    private HttpSession session;


    public String gerarRecomendacao(Double quantidadeDeConsumo) throws Exception {
        String prompt = "Com base no consumo de energia em kWh informado: " + quantidadeDeConsumo +
                ", forneça recomendações personalizadas para melhorar minha eficiência energética e reduzir os custos." +
                "Inclua informações sobre o potencial de economia financeira e o impacto positivo que essas ações podem ter no meio ambiente.";

        try {
            ChatResponse chatResponse = chatModel.call(new Prompt(
                    prompt,
                    OllamaOptions.builder()
                            .withModel(OllamaModel.LLAMA3_2)
                            .withTemperature(0.7)
                            .build()
                    )
            );

            String responseText = chatResponse.getResult().getOutput().getContent();

            session.setAttribute("recomendacaoIA", responseText);
            return responseText;
        }catch (Exception e){
            throw new Exception("Ocorreu um erro ao chamar o modelo de IA generativa " + e.getMessage());
        }
    }

}
