package theerapat.JBot;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.linecorp.bot.client.LineMessagingServiceBuilder;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import retrofit2.Response;

@SpringBootApplication
@LineMessageHandler
public class App 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    }
    
    @EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) throws IOException {
    	String accessToken = "Bra2+2U13hEbgOlU6lOa1+z1ZIThZzJ0Fuyv9/IzcE0EEyfHMK1CrBfxbNOIBVXn0hJkuZtFRAEChRtgePSW4PzOL7Gg3i3CHlqRbYPLALxTod7j7CWssmbDWFTeq3BS7Ar3sAi38rO2sqMZN4fPjgdB04t89/1O/w1cDnyilFU=";
    	TextMessage textMessage = new TextMessage("Hello");
    	ReplyMessage replyMessage = new ReplyMessage(event.getReplyToken(), textMessage);
    	Response<BotApiResponse> response = LineMessagingServiceBuilder
    	.create(accessToken)
    	.build()
    	.replyMessage(replyMessage)
    	.execute();
        System.out.println(response.code() + " " + response.message());
        return new TextMessage(event.getMessage().getText());
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }
}
