package com.allst.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

/**
 * kafka consumer
 * @author June
 * 2018-07-30
 * @version 1.0
 */
public class ConsumerDemo {

    public void kafkaConsumer() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.33.100:9092,192.168.33.102:9092");
        // group.id指定了消费者所属群组的名字
        properties.put("group.id", "CountryCounter");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // 1、创建消费者对象
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        // 2、消费者创建完成、现在开始创建订阅主题,下面创建了一个只包含单个元素的列表,主题名叫作"customerCountries"
        consumer.subscribe(Collections.singleton("customerCountries"));
        // 在调用subscribe()时可以传入一个正则表达式
        // consumer.subscribe("test.*")
        // 3、轮询
        try {
            // 3.1、无限循环
            while (true) {
                // 3.2、poll方法表示消费者持续对Kafka进行轮询，参数表示超时时间，为0会立即返回
                ConsumerRecords<String, String> records = consumer.poll(100);
                // 3.3、poll方法的参数返回一个列表
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println("topic = " + record.topic() +
                                  " partition = " + record.partition() +
                                     " offset = " + record.offset() +
                                   " customer = " + record.key() +
                                    " country = " + record.value());
                    int updateCount = 1;
//                    String custCountryMap =  ""
//                    if (custCountryMap.containsValue(record.value())) {
//                        updateCount = custCountryMap.get(record.value())
//                    }
//                    custCountryMap.put(record.value(), updateCount)
//                    JSONObject json = new JSONObject(custCountryMap)
//                    System.out.println(json.toString(4))

                    // 提交当前偏移量
                    consumer.commitAsync();
                }
            }
        } finally {
            // 4、退出应用程序之前使用close()方法来关闭消费者
            consumer.close();
        }
    }
}
