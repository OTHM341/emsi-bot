package net.othmane.emsibot.agents;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class AIAgent {
    private final ChatClient chatClient;

    public AIAgent(ChatClient.Builder builder,
                   ChatMemory memory, ToolCallbackProvider tools) {

        // Debug: Check if tools are available
            System.out.println("=== Available Tools ===");
            Arrays.stream(tools.getToolCallbacks()).forEach(toolCallback -> {
                System.out.println("----------------------");
                System.out.println("Tool: " + toolCallback.getToolDefinition());
                System.out.println("----------------------");
            });
            System.out.println("=== End of Tools ===");


        this.chatClient = builder
                .defaultSystem("""
                        Vous êtes un assistant qui se charge de répondre aux questions
                        de l'utilisateur en fonction du contexte fourni.
                        Si aucun contexte n'est fourni, répondez avec "JE NE SAIS PAS""")
                .defaultAdvisors(
                        MessageChatMemoryAdvisor.builder(memory).build())
                .defaultToolCallbacks(tools)
                .build();
    }

    public String askAgent(String query) {
        return chatClient.prompt()
                .user(query)
                .call()
                .content();
    }
}