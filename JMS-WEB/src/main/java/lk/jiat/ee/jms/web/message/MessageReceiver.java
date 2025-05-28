package lk.jiat.ee.jms.web.message;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Topic"),
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "activeMqTopic"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "activeMqTopic"), //Server-Specific
        @ActivationConfigProperty(propertyName = "resourceAdapter", propertyValue = "activemq-rar-6.1.6") //Server-Specific
})

public class MessageReceiver implements MessageListener {

    @PostConstruct
    public void init() {
        System.out.println("MessageReceiver Init....");
    }

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println(this + " " + "Received Message: " + message.getBody(String.class));
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
