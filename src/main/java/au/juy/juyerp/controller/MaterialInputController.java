package au.juy.juyerp.controller;


import au.juy.juyerp.form.MaterialInputSearchForm;
import au.juy.juyerp.service.MaterialInputService;
import au.juy.juyerp.service.SupplierService;
import au.juy.juyerp.utils.CustomCellWriteHandler;
import au.juy.juyerp.utils.ImportResult;
import au.juy.juyerp.utils.MaterialInputExportModel;
import au.juy.juyerp.utils.PageObject;
import com.alibaba.excel.EasyExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

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
    private MaterialInputService materialInputService;

    @Autowired
    private SupplierService supplierService;

    @PostMapping("/import")
    public String materialImport(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        ImportResult excelImport = materialInputService.excelImport(file.getInputStream());
        if(excelImport.getCode() == 0) return "redirect:/materialInput/list";
        model.addAttribute("errorMsg", excelImport.getMeg());
        return "materialInput";
    }

    @GetMapping("/export")
    public void exportData(HttpServletResponse response) {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("UTF-8");
            String fileName = URLEncoder.encode("Purchase Data", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            //read data from db
            List<MaterialInputExportModel> list = this.materialInputService.getExportList();
            EasyExcel.write(response.getOutputStream(), MaterialInputExportModel.class)
                    .registerWriteHandler(new CustomCellWriteHandler())
                    .sheet("Purchase Data")
                    .doWrite(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping ("/list")
    public String materialInputList(Model model, PageObject pageObject, MaterialInputSearchForm materialInputSearchForm){
        PageObject resultPage = materialInputService.materialInputList(pageObject, materialInputSearchForm);
        model.addAttribute("page", resultPage);
        model.addAttribute("supplierList", supplierService.list());
        model.addAttribute("form", materialInputSearchForm);
        return "materialInputList";
    }

    @PostMapping("/verify")
    @ResponseBody
    public String verify(Integer status, String idArray){
        boolean result = materialInputService.verifyOrStockin(status, idArray);
        if (result) return "success";
        return "fail";
    }

    @PostMapping("/stockin")
    @ResponseBody
    public String stockin(Integer status, String idArray){
        boolean result = materialInputService.verifyOrStockin(status, idArray);
        if (result) return "success";
        return "fail";
    }

    @PostMapping("/delete")
    @ResponseBody
    public String delete(String idArray){
        boolean deleted = materialInputService.delete(idArray);
        if(deleted) return "success";
        return "fail";
    }

}

