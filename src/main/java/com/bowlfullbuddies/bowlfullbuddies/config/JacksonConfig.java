// package com.bowlfullbuddies.bowlfullbuddies.config;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// public class JacksonConfig {

//     @Bean
//     public ObjectMapper objectMapper() {
//         ObjectMapper mapper = new ObjectMapper();

//         Hibernate6Module hibernateModule = new Hibernate6Module();
//         // Do not force lazy-loaded fields to load automatically
//         hibernateModule.disable(Hibernate6Module.Feature.USE_TRANSIENT_ANNOTATION);

//         mapper.registerModule(hibernateModule);
//         return mapper;
//     }
// }
