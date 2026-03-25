package utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;

@Getter
@Configuration
@PropertySource("classpath:testdata.properties")
public class TestData {
    @Value("${passcode}")
    private String passcode;
}