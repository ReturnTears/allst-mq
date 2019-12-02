package com.allst.kafka.producer;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * 使用一般的Avro对象而非生成的Avro对象，只需要提供schema就可以了
 * @author June
 * 2018-09-27
 * @version 1.0
 */
public class ProducerCommAvro {

    /**
     * schema注册表的位置
     */
    private String url = "";

    public void kafkaProducer() {
        Properties props = new Properties();
        // 指定broker地址清单
        props.put("bootstrap.servers", "shizhan:9092");
        // 使用KafkaAvroSerializer
        props.put("key.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
        props.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
        // 指定schema注册表URL
        props.put("schema.registry.url", url);

        // 提供 Avro schema, 因为我们没有使用Avro生成对象
        String schemaStr = "{\"namespace\":\"customerManagement.avro\"," +
                "\"type\":\"record\"," +
                "\"name\":\"Customer\"," +
                "\"fields\": [" +
                "{\"name\":\"id\", \"type\":\"int\"}," +
                "{\"name\":\"name\", \"type\":\"string\"}," +
                "{\"name\":\"email\", \"type\":[\"null\",\"string\"],\"default\":\"null\"}" +
                "]}";

        // 对象类型是Avro Generic, 我们通过schema和需要写入的数据来初始化它
        Producer<String, GenericRecord> producer = new KafkaProducer<>(props);
        Schema.Parser parser = new Schema.Parser();
        Schema schema = parser.parse(schemaStr);

        for (int nCustomers = 0; nCustomers < 6; nCustomers++) {
            String name = "exampleCustomers" + nCustomers;
            String email = "example" + nCustomers + "@example.com";

            // ProducerRecord的值就是一个GenericRecord对象，它包含了schema和数据。序列化器知道如何从记录里获取schema，把他保存到注册表里，并用它序列化对象数据。
            GenericRecord customer = new GenericData.Record(schema);
            customer.put("id", nCustomers);
            customer.put("name", name);
            customer.put("email", email);
            ProducerRecord<String, GenericRecord> data = new ProducerRecord<>("customerContacts", name, customer);
            producer.send(data);
        }
    }
}
