package com.allst.kafka.local

import java.time.Duration
import java.util
import java.util.Properties
import java.util.concurrent.atomic.AtomicBoolean

import org.apache.kafka.clients.consumer.{ConsumerRecord, ConsumerRecords, KafkaConsumer}

/**
  * 消费者
  *
  * @author YiYa
  * @since 2019-12-11 下午 10:00
  */
object ConsumerDemo {

    final val brokerList: String = "192.168.33.100:9092"
    final val topic: String = "topic-demo"
    final val groupId: String = "group.demo"

    final val isRunning: AtomicBoolean = new AtomicBoolean(true)

    def initConfig(): Properties = {
        val prop: Properties = new Properties()
        prop.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
        prop.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
        prop.put("bootstrap.servers", brokerList)
        prop.put("group.id", groupId)
        prop.put("client.id", "consumer.client.id.demo")
        prop
    }

    def main(args: Array[String]): Unit = {
        val prop: Properties = initConfig()
        val consumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](prop)
        consumer.subscribe(util.Arrays.asList(topic))
        try
            while (isRunning.get()) {
                val records: ConsumerRecords[String, String] = consumer.poll(Duration.ofMillis(1000))
                for (record: ConsumerRecord[String, String] <- records) {
                    println("record : " + record.topic())
                }
            }
        catch {
            case e: Exception =>
                e.printStackTrace()
        } finally {
            consumer.close()
        }
    }
}
