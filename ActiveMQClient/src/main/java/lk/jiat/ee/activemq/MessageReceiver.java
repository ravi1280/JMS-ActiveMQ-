package lk.jiat.ee.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class MessageReceiver {
    public static void main(String[] args) {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        try {
            Connection connection = connectionFactory.createConnection();
            connection.setClientID("ClientAPP3");
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic("News");

          MessageConsumer messageConsumer =   session.createConsumer(topic);
          messageConsumer.setMessageListener(new MessageListener() {
              @Override
              public void onMessage(Message message) {
                  try {
                      System.out.println("Received message: " + message.getBody(String.class));
                  } catch (JMSException e) {
                      throw new RuntimeException(e);
                  }

              }
          });

          while (true) {

          }

//            messageConsumer.close();
//            session.close();
//            connection.close();

        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
