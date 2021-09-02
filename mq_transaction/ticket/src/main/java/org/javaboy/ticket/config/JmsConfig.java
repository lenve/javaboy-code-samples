package org.javaboy.ticket.config;

import org.springframework.context.annotation.Configuration;

/**
 * Created by mavlarn on 2018/3/17.
 */
@Configuration
public class JmsConfig {
//
//    @Bean
//    public ConnectionFactory connectionFactory() {
//        ConnectionFactory cf = new ActiveMQConnectionFactory("tcp://localhost:61616");
//        TransactionAwareConnectionFactoryProxy proxy = new TransactionAwareConnectionFactoryProxy();
//        proxy.setTargetConnectionFactory(cf);
//        proxy.setSynchedLocalTransactionAllowed(true);
//        return proxy;
//    }
//
//    @Bean
//    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory, MessageConverter jacksonJmsMessageConverter) {
//        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
//        jmsTemplate.setMessageConverter(jacksonJmsMessageConverter);
//        jmsTemplate.setSessionTransacted(true);
//        return jmsTemplate;
//    }
//
//    @Bean
//    public JmsListenerContainerFactory<?> msgFactory(ConnectionFactory cf,
//                                                     PlatformTransactionManager transactionManager,
//                                                     DefaultJmsListenerContainerFactoryConfigurer configurer) {
//        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//        configurer.configure(factory, cf);
//        factory.setReceiveTimeout(10000L);
////        factory.setCacheLevelName("CACHE_CONNECTION");
//        factory.setTransactionManager(transactionManager);
//        factory.setConcurrency("10");
//        return factory;
//    }
//
//    @Bean
//    public MessageConverter jacksonJmsMessageConverter() {
//        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
//        converter.setTargetType(MessageType.TEXT);
//        converter.setTypeIdPropertyName("_type");
//        return converter;
//    }
}
