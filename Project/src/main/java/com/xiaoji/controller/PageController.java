package com.xiaoji.controller;

import com.alibaba.fastjson.JSONObject;
import com.xiaoji.service.HttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class PageController {
    @Autowired
    private HttpService httpauth;
    @Value("${zuul.authorize.url}")
    private URL authurl;
    @Value("${zuul.authorize.path}")
    private String authpath;

    @RequestMapping("/index")
    public String report(HttpServletRequest req, Model model){
        String token = req.getParameter("token");
        String openid = req.getParameter("openid");
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                switch (cookie.getName()) {
                    case "authorized_user":
                        token = cookie.getValue();
                        break;
                    case "authorized_openid":
                        openid = cookie.getValue();
                        break;
                    default:
                        break;
                }
            }
        }

        if (token != null && !"".equals(token) && openid != null && !"".equals(openid)) {
            Map<String, String[]> data = new HashMap<String, String[]>();
            Map<String, Object> res = httpauth.https(data, authurl + "/" + openid + "/info");
            if (res != null && res.get("data") != null) {
                JSONObject userinfo = (JSONObject) res.get("data");
                model.addAttribute("userinfo", userinfo);
            }
        }
        return "/index";
    }
}
