package com.bookstore.api.bookstore.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bookstore.api.bookstore.service.FileWatcherService;
import com.bookstore.api.bookstore.service.ReadMessageService;

@Configuration
public class AppConfig {

    @Value("${message.file.path}")
    private String filePath;

    @Bean
    public ReadMessageService.KeyValueStore keyValueStore() {
        ReadMessageService.KeyValueStore keyValueStore = new ReadMessageService.KeyValueStore();
        ReadMessageService.KeyValueStore.loadFromExcel(filePath);
        return keyValueStore;
    }

    @Bean
    public ReadMessageService readMessageService() {
        return new ReadMessageService();
    }

    @Bean
    public FileWatcherService fileWatcherService(ReadMessageService.KeyValueStore keyValueStore) {
        return new FileWatcherService(keyValueStore);
    }
}
