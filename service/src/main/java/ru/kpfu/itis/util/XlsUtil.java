package ru.kpfu.itis.util;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.UnsupportedDataTypeException;
import java.io.IOException;

/**
 * Created on 17.09.2014.
 */
public class XlsUtil {

    public static Workbook getWorkbook(MultipartFile file) throws IOException {
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        Workbook workbook;
        switch (ext) {
            case "xls":
                workbook = new HSSFWorkbook(file.getInputStream());
                break;
            case "xlsx":
                workbook = new XSSFWorkbook(file.getInputStream());
                break;
            default:
                throw new UnsupportedDataTypeException("unsupported file extension " + ext);
        }
        return workbook;
    }

    public static boolean isXlsFile(MultipartFile file) {
        if (file == null) {
            return false;
        }
        String fileName = file.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        if ("xls".equalsIgnoreCase(ext) || "xlsx".equalsIgnoreCase(ext)) {
            return true;
        }
        return false;
    }

    public static String readCell(Cell cell) {
        if (cell == null) {
            return null;
        }
        String result;
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            Double value = cell.getNumericCellValue();
            result = String.valueOf(value.intValue());
        } else {
            result = cell.getStringCellValue();
        }
        if (result.length()==0)
            return null;
        return result;
    }
}
