package com.lunshi.matchmaking.web.application;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;

import javax.jms.Topic;
import java.util.ArrayList;
import java.util.Arrays;

@EnableJms
@Configuration
public class ActiveMqConfig {

    @Bean("cuohe.topic.product")
    public Topic preOrder(){return  new ActiveMQTopic("matchmaking.topic.product");}

    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory(@Value("${spring.activemq.broker-url}") String url, @Value("${spring.activemq.user}") String user, @Value("${spring.activemq.password}") String pass, RedeliveryPolicy redeliveryPolicy) {
        ActiveMQConnectionFactory activeMQConnectionFactory =
                new ActiveMQConnectionFactory(
                        user,
                        pass,
                        url);
        activeMQConnectionFactory.setRedeliveryPolicy(redeliveryPolicy());
        activeMQConnectionFactory.setTrustAllPackages(true);
        activeMQConnectionFactory.setTrustedPackages(new ArrayList(Arrays.asList("com.nidtrade".split(","))));
        return activeMQConnectionFactory;
    }

    @Bean
    public RedeliveryPolicy redeliveryPolicy() {
        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
        //是否在每次尝试重新发送失败后,增长这个等待时间
        redeliveryPolicy.setUseExponentialBackOff(true);
        //重发次数,默认为6次   这里设置为10次
        redeliveryPolicy.setMaximumRedeliveries(10);
        //重发时间间隔,默认为10秒
        redeliveryPolicy.setInitialRedeliveryDelay(10);
        //第一次失败后重新发送之前等待500毫秒,第二次失败再等待500 * 2毫秒,这里的2就是value
        redeliveryPolicy.setBackOffMultiplier(2);
        //是否避免消息碰撞
        redeliveryPolicy.setUseCollisionAvoidance(false);
        //设置重发最大拖延时间-1 表示没有拖延只有UseExponentialBackOff(true)为true时生效
        redeliveryPolicy.setMaximumRedeliveryDelay(-1);

        return redeliveryPolicy;
    }

    @Bean
    public JmsMessagingTemplate jmsMessagingTemplate(ActiveMQConnectionFactory factory) {
        return new JmsMessagingTemplate(factory);
    }


    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ActiveMQConnectionFactory factory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setPubSubDomain(true);
        bean.setConnectionFactory(factory);
        return bean;
    }


}
