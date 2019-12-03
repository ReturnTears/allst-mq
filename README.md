#Kafka
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



```

## Code
```
分别使用
Java
Scala
两种语言对Kafka进行编码学习

运行在集群环境中
```