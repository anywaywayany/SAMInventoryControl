package com.samic.samic;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.OracleContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
class TestContainerConfiguration {

//    @Bean
////    @RestartScope
//    @ServiceConnection
//    OracleContainer oracleContainer() {
//        return new OracleContainer(DockerImageName.parse("gvenzl/oracle-xe:21-slim-faststart"))
//                .withEnv("ORACLE_PASSWORD", "oracle")
//                .withEnv("APP_USER", "oracle")
//                .withEnv("APP_USER_PASSWORD", "oracle")
//                //.withUsername("oracle")
//                //.withPassword("oracle")
//                .withExposedPorts(1521)
//                .withCreateContainerCmdModifier(cmd -> {
//                    cmd.withName("samic-oracle");
//                    cmd.withHostConfig(new HostConfig()
//                            .withPortBindings(new PortBinding(Ports.Binding.bindPort(1521), new ExposedPort(1521))));
//                });
//    }

    @Bean
    @RestartScope
    @ServiceConnection
    OracleContainer oracleContainer(){
        final int exposedPort = 1521;
        final int localPort = 15432;
        return new OracleContainer(DockerImageName.parse("gvenzl/oracle-xe:21"))
                       .withExposedPorts(exposedPort)
                       .withCreateContainerCmdModifier(cmd ->{
                           cmd.withName("samic-oracle");
                           cmd.withHostConfig(new HostConfig()
                                                      .withPortBindings(new PortBinding(Ports.Binding.bindPort(1521), new ExposedPort(1521))));
                       }).withReuse(true);
    }
}
