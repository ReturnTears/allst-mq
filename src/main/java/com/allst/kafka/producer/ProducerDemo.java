package com.allst.kafka.producer;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;
/**
 * kafka producer
 * @author June
 * 2018-07-28
 * @version 1.0
 */
public class ProducerDemo {

    public static void main(String[] args) {
        new ProducerDemo().kafkaProducer();
    }

    public void kafkaProducer() {
        // kafka生产者有三个必备的属性
        Properties kafkaProps = new Properties();
        // bootstrap.servers属性指定broker的地址清单,清单里不必包含所有的broker地址：192.168.33.102:9092,192.168.33.103:9092
        kafkaProps.put("bootstrap.servers", "192.168.33.100:9092,192.168.33.102:9092");
        // key.serializer
        kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // value.serializer
        kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");


        // 添加分区器
        kafkaProps.put("partitioner.class", "com.allst.kafka.partition.MyPartitioner");

        // 如何把生成的Avro对象发送到Kafka
        /**
        kafkaProps.put("key.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer")
        kafkaProps.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer")
         // 参数schema.registry.url指向schema的存储位置
        kafkaProps.put("schema.registry.url","schemaUrl")
        */
        // 定义Producer对象
        Producer<String, String> producer = new KafkaProducer<>(kafkaProps);
        // 实例化好生产者对象后 接下来开始开发发送消息 发送消息的三种方式
        ProducerRecord<String, String> record = new ProducerRecord<>("test", "love", "yangYang");
        // 发送并忘记(fire-and-forget):把消息发送给服务器，但是并不关心他是否正常到达
        try {
            producer.send(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 同步发送:使用send()方法发送消息 他会返回一个Future对象 调用get方法进行等待 就会知道消息是否发送成功
//        try {
//            producer.send(record).get();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        // 异步发送:调用send()方法 并指定一个回调函数 服务器在返回响应时调用该函数
        // producer.send(record, new DemoProducerCallback());
    }

    /**
     * 该内部类配合异步发送一同使用
     */
//    private class DemoProducerCallback implements Callback {
//        @Override
//        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
//            if (e != null) {
//                e.printStackTrace();
//            }
//        }
//    }
}
