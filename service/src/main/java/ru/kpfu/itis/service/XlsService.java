package ru.kpfu.itis.service;

import org.apache.poi.ss.usermodel.Workbook;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by ainurminibaev on 17.09.14.
 */
public interface XlsService {

    public boolean saveUsers(MultipartFile file);

    Workbook xlsUsersDump();
}
