package com.winter.app.rest;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/api/*")
@Slf4j
public class RestTestController {
	
	@GetMapping("list")
	public void m1() throws Exception{
//		log.info("api list");
		
		// RestTemplate 사용
//		RestTemplate restTemplate = new RestTemplate();
		
//		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(null);
		
		//파싱 필요없음
//		ResponseEntity<String> result = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/photos/1", String.class, request);
//		ResponseEntity<PhotoVO> result = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/photos/1", PhotoVO.class, request); 1개만 받을때
//		ResponseEntity<PhotoVO> result = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/photos", PhotoVO.class, request); 5000개 오류
//		List<PhotoVO> result = restTemplate.getForObject("https://jsonplaceholder.typicode.com/photos", List.class, request); //5000개 받을때
		
//		log.info("{}", result.getBody());
//		log.info("{}", result);
		
		// WebClient 사용 (비동기)
		WebClient webClient = WebClient.builder()
										.baseUrl("https://jsonplaceholder.typicode.com/photos/1")
										.build();
		Mono<ResponseEntity<PhotoVO>> res = webClient.get()
													.retrieve()
													.toEntity(PhotoVO.class);
		PhotoVO photoVO = res.block().getBody();
		
		log.info("{}", photoVO);
		
//		this.m2();
		this.m3();
	}
	
	
	private void m2() throws Exception{
		WebClient webClient = WebClient.builder()
										.baseUrl("https://jsonplaceholder.typicode.com")
										.build();
		Flux<UserVO> res = 
		webClient.get()
				.uri("/users")
				.retrieve()
				.bodyToFlux(UserVO.class);
		
		res.subscribe(u->{		//반복문돌려서, 매개변수받아서 리턴은없다
			UserVO userVO = u;
			log.info("{}", userVO); //리스트생성해서 받는 작업도 추가하면됨
		});
		
		log.info("{}", res.blockFirst());
		
	}
	
	private void m3() throws Exception{
		PostVO postVO = new PostVO();
		postVO.setTitle("title");
		postVO.setBody("contents");
		postVO.setUserId(1L);
		
//		WebClient webClient = WebClient.builder() //create 써도됨
//										.baseUrl("https://jsonplaceholder.typicode.com")
//										.build();
//		
//		Mono<PostVO> response = webClient.post()
//				.uri("/posts")
//				.bodyValue(postVO)
//				.retrieve()
//				.bodyToMono(PostVO.class);
//		
//		PostVO updatedPost = response.block();
//		log.info("{}", updatedPost);
		
		WebClient webClient = WebClient.create();
		
		Mono<PostVO> res = 
		webClient.post() //put으로 바꾸면 update가 되고, 아이디(pk)만 추가로 넣어주면됨 / delete 로 바꾸면 삭제
					.uri("https://jsonplaceholder.typicode.com/posts")
					.bodyValue(postVO)
					.accept(MediaType.APPLICATION_JSON)
					.retrieve()
					.bodyToMono(PostVO.class)
					;
		
		log.info("{}", res.block());
		
		
		
	}
	
	
}
