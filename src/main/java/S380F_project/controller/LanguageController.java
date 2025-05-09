package S380F_project.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;

@Controller
public class LanguageController {
    @GetMapping("/changeLanguage")
    public String changeLanguage(@RequestParam("lang") String lang,
                                 HttpSession session,
                                 HttpServletRequest request) {
        // 記低原本個URL
        String referer = request.getHeader("Referer");
        String returnUrl = (referer != null) ? referer : "/index";

        // 清除同設定新locale
        session.removeAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE");

        Locale newLocale;
        switch(lang) {
            case "zh_HK":
                newLocale = new Locale("zh", "HK");
                break;
            case "zh_CN":
                newLocale = new Locale("zh", "CN");
                break;
            default:
                newLocale = new Locale("en", "US");
        }

        session.setAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE", newLocale);

        // 返回原本嗰頁
        return "redirect:" + returnUrl;
    }
}
