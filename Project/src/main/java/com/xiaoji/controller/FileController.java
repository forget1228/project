package com.xiaoji.controller;

import com.xiaoji.configuration.config.YmlConfig;
import com.xiaoji.service.ProjectService;
import com.xiaoji.util.MessageResult;
import com.xiaoji.util.ResultResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/project")
public class FileController {
    @Autowired
    private ProjectService projectService;

    Logger logger = LoggerFactory.getLogger(FileController.class);

    @RequestMapping("/file")
    @ResponseBody
    public void initialize(HttpServletRequest request) {

    }


    @RequestMapping("/download")
    public void download(HttpServletRequest request,HttpServletResponse response) {

    }
}
