package com.allst.kafka.local

import java.util.Properties

import org.apache.kafka.clients.producer._
import sun.plugin2.jvm.RemoteJVMLauncher.CallBack

/**
  * 带回调函数的生产者
  *
  * @author YiYa
  * @since 2019-12-10 下午 10:28
  */
object ProducerCallBack {
    def main(args: Array[String]): Unit = {
        // 创建配置信息
        val properties = new Properties()
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.33.100:9092")
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
        // 创建生产者对象
        val kafkaProducer = new KafkaProducer[String, String](properties)

        // 发送数据
        for (i <- 10) {
            kafkaProducer.send(new ProducerRecord[String, String]("test", "test msg - " + i), new Callback() {
                override def onCompletion(recordMetadata: RecordMetadata, e: Exception): Unit = {
                    if (e == null) {
                        println(recordMetadata.partition())
                    }
                }
            })
        }

        // 关闭资源
        kafkaProducer.close()
    }
}
