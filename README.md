# MQ 消息队列
## Kafka
```
Kafka命令行操作
查看topic:
bin/kafka-topics.sh --list --zookeeper shizhan01:2181
创建topic:
bin/kafka-topics.sh --create --zookeeper shizhan01:2181 --topic first --partitions 2 --replication-factor 2
删除topic:
bin/kafka-topics.sh --delete --zookeeper shizhan01:2181 first
详情topic:
bin/kafka-topics.sh --describe --topic first --zookeeper shizhan01:2181
生产者:
bin/kafka-console.consumer.sh --topic first --bootstrap-server shizhan01 


kafka工作流程以及文件存储机制

Kafka事务
为了实现分区跨会话的事务, 需要引入一个全局唯一的Transaction ID(客户端提供的), 并将Producer获得的PID和Transaction ID绑定.
这样当producer重启后就可以通过正在进行的Transaction ID获得原来的PID

消息发送流程:
Kafka的producer发送消息采用的是异步发送的方式,在消息发送的过程中, 涉及到了两个线程--main线程和sender线程, 以及一个线程共享变量--RecordAccumulator,
main线程将消息发送给RecordAccumulator, sender线程不断从RecordAccumulator中拉取下下哦i发送到Kafka Broker.


```

## Code
```
分别使用
Java
Scala
两种语言对Kafka进行编码学习

运行在集群环境中

现在将专注于实现Kafka API使用的allst-kafka修改为allst-mq, 专注于各类MQ消息队列的使用，分析
```

## RabbitMQ
[RabbitMQ](allst-rocketmq/README.md)
```text

```