package com.allst.kafka.partition;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.record.InvalidRecordException;
import org.apache.kafka.common.utils.Utils;

import java.util.List;
import java.util.Map;

/**
 * 自定义分区器
 * @author June
 * 2018-09-27
 * @version 1.0
 */
public class BananaPatitioner implements Partitioner {
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
        int numPartitions = partitions.size();
        // 这里只接受字符串作为键，如果不是字符串就抛出异常
        if ((keyBytes == null) || (!(key instanceof String))) {
            throw new InvalidRecordException("we expect all messages to have customer name as key");
        }

        // Banana总是被分配到最好一个分区,最好的方式是应该通过configure方法传进来
        if ("Banana".equals(key)) {
            return numPartitions;
        } else {
            return (Math.abs(Utils.murmur2(keyBytes)) % (numPartitions - 1));
        }
    }

    @Override
    public void close() {}

    @Override
    public void configure(Map<String, ?> map) {}
}
