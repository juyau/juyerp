package au.juy.juyerp.controller;


import au.juy.juyerp.service.MaterialInputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Samuel Ju
 * @since 2024-03-24
 */
@Controller
@RequestMapping("/materialInput")
public class MaterialInputController {

    @Autowired
    MaterialInputService materialInputService;

    @PostMapping("/import")
    @ResponseBody
    public String materialImport(@RequestParam("file") MultipartFile file) throws IOException {
        boolean b = materialInputService.excelImport(file.getInputStream());
        if(b) return "OK";

        return "failed";

    }

}

