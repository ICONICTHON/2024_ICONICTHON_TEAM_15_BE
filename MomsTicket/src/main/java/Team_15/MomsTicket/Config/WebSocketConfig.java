package Team_15.MomsTicket.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 메세지 브로드캐스팅 경로 - 메시지 브로커가 /topic으로 시작하는 메시지를 클라이언트로 라우팅
        config.enableSimpleBroker("/sub");
        // 전송 기본 경로 - 클라이언트에서 메시지를 송신할 때 /app으로 시작하는 메시지를 받아들임
        config.setApplicationDestinationPrefixes("/pub");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 클라이언트가 /ws/chat 경로로 접속하도록 웹 소켓을 등록
        registry.addEndpoint("/ws/chat").setAllowedOrigins("*").withSockJS();
    }
}
