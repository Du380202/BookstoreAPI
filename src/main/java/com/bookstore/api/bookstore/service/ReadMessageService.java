package com.bookstore.api.bookstore.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bookstore.api.bookstore.code.MessageCode;

import jakarta.annotation.PostConstruct;

@Service
public class ReadMessageService {

    public static class KeyValueStore {
        private static final Map<MessageCode, String> keyValueMap = new HashMap<>();

        public static void loadFromExcel(String filePath) {
            try (FileInputStream fis = new FileInputStream(new File(filePath));
                 Workbook workbook = new XSSFWorkbook(fis)) {

                Sheet sheet = workbook.getSheetAt(0);
                for (Row row : sheet) {
                    if (row.getPhysicalNumberOfCells() >= 2) {
                        String key = row.getCell(0).getStringCellValue();
                        String value = row.getCell(1).getStringCellValue();

                        try {
                            MessageCode enumKey = MessageCode.valueOf(key);
                            keyValueMap.put(enumKey, value);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Key không hợp lệ trong enum: " +key + e.getMessage());
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static String get(MessageCode key) {
            return keyValueMap.getOrDefault(key, "Không tìm thấy giá trị");
        }
    }
}
