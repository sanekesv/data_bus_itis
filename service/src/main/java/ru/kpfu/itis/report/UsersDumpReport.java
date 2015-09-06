package ru.kpfu.itis.report;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.kpfu.itis.model.AcademicGroup;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.repository.UserRepository;
import ru.kpfu.itis.service.AcademicGroupService;
import ru.kpfu.jbl.auth.util.PasswordHelper;

import java.util.List;

/**
 * Created by sanekesv on 05.09.15.
 */
public class UsersDumpReport {

    protected UserRepository userRepository;
    protected AcademicGroupService groupService;

    public UsersDumpReport(UserRepository userRepository, AcademicGroupService groupService) {
        this.userRepository=userRepository;
        this.groupService=groupService;
    }

    public Workbook build() {
        throwExIfNull(userRepository);
        throwExIfNull(groupService);
        List<AcademicGroup> groups = groupService.getAll();
        XSSFWorkbook workbook = new XSSFWorkbook();

        for (AcademicGroup group : groups) {
            if (group.getId()>0) {
                XSSFSheet sheet = workbook.createSheet(group.getTitle());
                List<User> users = group.getUsers();
                createUsersDumpHeaderRow(sheet.createRow(0));//table header
                for (int i = 0; i < users.size(); i++) {
                    User user = users.get(i);
                    createUserEventInfoRow(sheet.createRow(i + 1), user);
                }
            }
        }

        return workbook;
    }

    private void createUsersDumpHeaderRow(XSSFRow row) {
        row.createCell(0, Cell.CELL_TYPE_STRING).setCellValue("ФИО");
        row.createCell(1, Cell.CELL_TYPE_STRING).setCellValue("Логин");
        row.createCell(2, Cell.CELL_TYPE_STRING).setCellValue("Пароль");
    }

    private void createUserEventInfoRow(XSSFRow row, User user) {
        row.createCell(0).setCellValue(user.getName());
        row.createCell(1).setCellValue(user.getLogin());
        if (PasswordHelper.isPasswordGenerated(user.getPassword(), user.getSalt())) {
            row.createCell(2).setCellValue(PasswordHelper.getGeneratedPasswordFromHash(user.getPassword()));
        } else {
            row.createCell(2).setCellValue("Пароль изменен пользователем");
        }
    }

    private void throwExIfNull(Object object) {
        if (object == null) throw new NullPointerException();
    }

}
