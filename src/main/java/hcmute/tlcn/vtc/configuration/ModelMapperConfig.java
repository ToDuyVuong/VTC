package hcmute.tlcn.vtc.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        // Tạo object và cấu hình
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }


//    // Lấy User entity ra từ DB
//    User user = userRepository.findByUsername(username);
//
//    // Map thành DTO
//    UserDto userDto = mapper.map(user, UserDto.class);
}