package edu.pnu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.annotation.Nonnull;


@Configuration
public class CutomConfig implements WebMvcConfigurer{
	
	// 1. 기본 형태
//	@Override
//	public void addCorsMappings(@Nonnull CorsRegistry registry) {
//		registry.addMapping("/**") // 모든 주소에 대해서
//				.allowedMethods(CorsConfiguration.ALL) // 모든 Method에 대해서
//				.allowedOrigins(CorsConfiguration.ALL); // 모든 Origin에 대해서
//	}
////	"http://localhost:3000", // CORS 요청을 허용할 출처 지정
////	"http://127.0.0.1:3000"
	
	// 2. 확장 응용 형태
//	@Override
//	public void addCorsMappings(@NonNull CorsRegistry registry) {
//		registry.addMapping("/board/**") // board 포함 하부 모든 주소에 대해서
//				.allowedMethods(HttpMethod.GET.name(),
//								HttpMethod.POST.name()) // Get & Post Method에 대해서
//				.allowedOrigins("http://localhost:3000",  "http://127.0.0.1:3000");
//		// 해당 Origin만 접속이 허용
//		
//		registry.addMapping("/member/**") // member 포함 하부 모든 주소에 대해서
//				.allowedMethods(HttpMethod.GET.name(), // Get & Put Method에 대해서
//								HttpMethod.PUT.name()) 
//				.allowedOrigins("http://localhost:3000");
//	}
	
	// 3. Authorization이 필요한 경우
	@Override
	public void addCorsMappings(@Nonnull CorsRegistry registry) {
		registry.addMapping("/board/**")
//				.allowCredentials(true) // 클라이언트가 자격증명(쿠키/인증헤더)을 포함하도록 허용
//				.allowedHeaders(HttpHeaders.AUTHORIZATION)
				// 클라이언트가 요청 시 사용 할 수 있는 헤더 지정
//				.exposedHeaders(HttpHeaders.CONTENT_TYPE, "application/json")
				// 클라이언트가 응답에 접근 할 수 있는 헤더 지정
				.allowedMethods(HttpMethod.GET.name(), // 클라이언트가 요청 시 사용할 수 있는 Method 지정
								HttpMethod.POST.name(),
								HttpMethod.PUT.name(),
								HttpMethod.DELETE.name())
				.allowedOrigins("http://localhost:3000", "http://127.0.0.1:3000");
	}
	
	
	
	
	

}
