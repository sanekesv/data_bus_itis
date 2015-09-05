package ru.kpfu.itis.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.controller.errors.ResourceNotFoundException;
import ru.kpfu.itis.service.XlsService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by sanekesv on 05.09.15.
 */
@Controller
@RequestMapping("/download")
@Api(value = "Download controller", description = "")
public class DownloadController {

    private static final String CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    @Autowired
    private XlsService xlsService;

    @ApiImplicitParams(value = {@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "query")})
    @ApiOperation(httpMethod = "GET", value = "Fid all users in group")
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public void getUsersAsXls(HttpServletResponse response) {
        String filename = "user_passwords_" + DateTime.now().toString("dd_MM_yyyy");
        writeToResponse(response, xlsService.xlsUsersDump(), filename);
    }

    private Workbook writeToResponse(HttpServletResponse response, Workbook workbook, String filename) {
        filename = filename + ".xlsx";
        response.setContentType(CONTENT_TYPE);
        response.setHeader("Content-disposition", "attachment; filename=" + filename);
        try (OutputStream out = response.getOutputStream()) {
            workbook.write(out);
            out.flush();
            return workbook;
        } catch (IOException e) {
            throw new ResourceNotFoundException(e, "Some error occurred while writing file!");
        }
    }
}
