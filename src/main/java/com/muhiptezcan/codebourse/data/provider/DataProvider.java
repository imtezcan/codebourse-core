package com.muhiptezcan.codebourse.data.provider;

import com.hazelcast.config.Config;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.boot.autoconfigure.hazelcast.HazelcastInstanceFactory;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by imtezcan on 10.04.2016.
 */
@Repository
public class DataProvider {
    private HazelcastInstance hazelcastInstance;

    public DataProvider() {
        final Config config = new Config();
        final HazelcastInstanceFactory factory = new HazelcastInstanceFactory(config);
        hazelcastInstance = factory.getHazelcastInstance();
    }

    public Map<String, Long> getScoresMap() {
        return hazelcastInstance.getMap("scores_map");
    }
}
