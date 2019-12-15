package com.allst.kafka.local

import java.util

import org.apache.kafka.clients.producer.Partitioner
import org.apache.kafka.common.Cluster

/**
  * 分区器
  *
  * @author YiYa
  * @since 2019-12-15 下午 10:54
  */
object MyPartitioner extends Partitioner {

    override def partition(s: String, o: Any, bytes: Array[Byte], o1: Any, bytes1: Array[Byte], cluster: Cluster): Int = {
        1
    }

    override def close(): Unit = ???

    override def configure(map: util.Map[String, _]): Unit = ???
}
