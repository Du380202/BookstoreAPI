package com.bookstore.api.bookstore.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

import java.io.IOException;
import java.nio.file.*;

@Component
public class FileWatcherService {

    private final ReadMessageService.KeyValueStore keyValueStore;

    @Value("${message.file.path}")
    private String filePath;

    public FileWatcherService(ReadMessageService.KeyValueStore keyValueStore) {
        this.keyValueStore = keyValueStore;
    }

    @PostConstruct
    public void init() {
        watchFileChanges();
    }

    public void watchFileChanges() {
        new Thread(() -> {
            try {
                Path path = Paths.get(filePath).getParent();
                WatchService watchService = FileSystems.getDefault().newWatchService();
                path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);


                while (true) {
                    WatchKey key = watchService.take();

                    for (WatchEvent<?> event : key.pollEvents()) {
                        Path changed = (Path) event.context();

                        if (changed.endsWith(Paths.get(filePath).getFileName())) {
                            ReadMessageService.KeyValueStore.loadFromExcel(filePath);
                        }
                    }

                    boolean valid = key.reset();
                    if (!valid) {
                        break;
                    }
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
