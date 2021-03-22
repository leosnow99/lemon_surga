package com.sugar.chat.controller;

import com.sugar.chat.util.ChannelSocketHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author bytedance
 */
@RestController
@RequestMapping("/")
public class DemoController {
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@GetMapping
	public void consulDemo() {
		
		
		System.out.println("----------------");
		discoveryClient.getServices().forEach(System.out::println);
		final List<ServiceInstance> instances = discoveryClient.getInstances("IMSENTINEL");
		final ServiceInstance serviceInstance = instances.get(0);
		serviceInstance.getMetadata().forEach((s, s2) -> System.out.println("key: " + s + " value: " + s2));
//		final ConsulClient consulClient = new ConsulClient("218.198.180.38");
//		HealthServicesRequest.newBuilder()
//				.set
//		consulClient.getHealthServices("IMSENTINEL", );
	}

	/**
	 * 路由健康检查
	 * @return 回复目前服务器注册的服务数量
	 */
	@GetMapping("/route/health")
	public Integer checkHealth() {
		return ChannelSocketHolder.getUserCount();
	}
}
