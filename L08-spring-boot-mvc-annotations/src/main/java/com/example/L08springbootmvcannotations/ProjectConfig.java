package com.example.L08springbootmvcannotations;

import collections.BasicKeywordAnalyzer;
import collections.DummyKeywordAnalyzer;
import collections.KeywordAnalyzerInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ProjectConfig {

    @Bean("basicKeywordAnalyzer")
    // here we overriding the bean name also as basicKeywordAnalyzer,
    // by default the bean name is the method name given below as getKeywordAnalyser
    // (once we override the name then that will not be available)
    @Primary
    public KeywordAnalyzerInterface getKeywordAnalyser(){
        return new BasicKeywordAnalyzer();
    }

    @Bean("dummyKeywordAnalyzer")
    public KeywordAnalyzerInterface getDummyKeywordAnalyzer(){
        return new DummyKeywordAnalyzer();
    }
}
