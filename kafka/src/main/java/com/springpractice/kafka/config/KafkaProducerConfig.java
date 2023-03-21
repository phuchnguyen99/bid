//package com.springpractice.kafka.config;
//
//import com.springpractice.client.notification.NotificationRequest;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//import org.springframework.kafka.support.serializer.JsonDeserializer;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class KafkaProducerConfig {
//    @Value("${spring.kafka.bootstrap-servers}")
//    private String bootstrapServer;
//
//    public Map<String, Object> producerConfig(){
//        final Map<String, Object> props = new HashMap<>();
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//        return props;
//    }
//
//    @Bean
//    public ProducerFactory<String, NotificationRequest> producerFactory(){
//        return new DefaultKafkaProducerFactory<>(producerConfig());
//    }
//
//    @Bean
//    public KafkaTemplate<String, NotificationRequest> kafkaTemplate(
//            final ProducerFactory<String, NotificationRequest> producerFactory){
//        return new KafkaTemplate<>(producerFactory);
//    }
//}
