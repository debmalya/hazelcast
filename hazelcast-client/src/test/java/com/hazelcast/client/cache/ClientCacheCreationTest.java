/*
 * Copyright (c) 2008-2017, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.client.cache;

import com.hazelcast.cache.CacheCreationTest;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.cache.impl.HazelcastClientCachingProvider;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.junit.Ignore;
import org.junit.Test;

import javax.cache.spi.CachingProvider;
import java.util.Arrays;

public class ClientCacheCreationTest extends CacheCreationTest {


    @Test
    @Ignore("Only applicable for member-side HazelcastInstance")
    @Override
    public void createInvalidCache_fromDeclarativeConfig_throwsException_fromHazelcastInstanceCreation() {

    }

    @Override
    protected CachingProvider createCachingProvider(Config hzConfig) {
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(hzConfig);
        ClientConfig clientConfig = null;
        if (hzConfig != null) {
            clientConfig = new ClientConfig();
            clientConfig.getGroupConfig().setName(hzConfig.getGroupConfig().getName());
            clientConfig.getGroupConfig().setPassword(hzConfig.getGroupConfig().getPassword());
            clientConfig.getNetworkConfig().setAddresses(Arrays.asList("127.0.0.1"));
        }
        return HazelcastClientCachingProvider.createCachingProvider(HazelcastClient.newHazelcastClient(clientConfig));
    }
}
