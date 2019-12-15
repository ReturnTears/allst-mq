package com.allst.kafka.local

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, Producer, ProducerRecord}

/**
  * @author YiYa
  * @since 2019-12-10 下午 09:14
  */
object ProducerDemo {
    def main(args: Array[String]): Unit = {
        val perperties = new Properties()

        perperties.setProperty("bootstrap.servers", "192.168.33.100:9092,192.168.33.102:9092")
        perperties.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
        perperties.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

        // 自定义分区器
        perperties.setProperty("partitioner.class", "com.allst.kafka.local.MyPartitioner")

        // 创建生成对象
        val producer = new KafkaProducer[String, String](perperties)
        // 创建主题topic
        val record = new ProducerRecord[String, String]("test", "love", "yangYang")
        try
            producer.send(record)
        catch {
            case e: Exception =>
                e.printStackTrace()
        } finally {
            // 关闭资源
            producer.close()
        }
    }
}
