package com.allst.kafka.producer;

import com.allst.kafka.customer.Customer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * 如何把生成的Avro对象发送到Kafka
 * @author June
 * 2018-09-27
 * @version 1.0
 */
public class ProducerAvro {

    public void kafkaProducer() {
        // kafka生产者有三个必备的属性
        Properties kafkaProps = new Properties();
        // bootstrap.servers属性指定broker的地址清单,清单里不必包含所有的broker地址：192.168.33.102:9092,192.168.33.103:9092
        kafkaProps.put("bootstrap.servers", "shizhan:9092,shizhan2:9092");
        // key.serializer,使用Avro的KafkaAvroSerializer来序列化对象
        kafkaProps.put("key.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
        // value.serializer，同上
        kafkaProps.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
        // 参数schema.registry.url指向schema的存储位置
        kafkaProps.put("schema.registry.url","schemaUrl");

        String topic = "customerContacts";

        // 定义Producer对象, Customer是生成的对象。我们会告诉生产者Customer对象就是记录的值。
        Producer<Integer, Customer> producer = new KafkaProducer<>(kafkaProps);
        // 不断生成事件
        while (true) {
            // Customer customer = CustomerGenerator.getNext()
            Customer customer = null;
            System.out.println("Customer customer " + customer.toString());
            // 实例化一个包含键的ProducerRecord对象，并指定Customer为值的类型，然后再传给他一个Customer对象
            ProducerRecord<Integer, Customer> record = new ProducerRecord<>(topic, customer.getID(), customer);
            // 床一个不包含键的ProducerRecord的对象, 键被设置成了null值
            ProducerRecord<Integer, Customer> noRecord = new ProducerRecord<>(topic, customer);
            // 把Customer对象作为记录发送出去，kafkaAvroSerializer会处理剩下的事情
            producer.send(record);
        }
    }
}
