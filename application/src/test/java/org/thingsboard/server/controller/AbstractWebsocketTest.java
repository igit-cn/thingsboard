/**
 * Copyright © 2016-2022 The Thingsboard Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.thingsboard.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import java.net.URI;
import java.net.URISyntaxException;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AbstractControllerTest.class, loader = SpringBootContextLoader.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@Configuration
@ComponentScan({"org.thingsboard.server"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public abstract class AbstractWebsocketTest extends AbstractWebTest {

    protected static final String WS_URL = "ws://localhost:";

    @LocalServerPort
    protected int wsPort;

    protected TbTestWebSocketClient buildAndConnectWebSocketClient() throws URISyntaxException, InterruptedException {
        TbTestWebSocketClient wsClient = new TbTestWebSocketClient(new URI(WS_URL + wsPort + "/api/ws/plugins/telemetry?token=" + token));
        Assert.assertTrue(wsClient.connectBlocking());
        return wsClient;
    }

}
