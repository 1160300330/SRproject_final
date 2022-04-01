package com.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pojo.StockIDName;
import com.pojo.StockInfo;
import com.pojo.StockNameID;
import com.service.StockService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/stock")
public class StockController {
    @Autowired
    private StockService stockService;
    @Autowired
    private StockNameID stockNameID;
    @Autowired
    private StockIDName stockIDName;

    @RequestMapping("/queryPage")
    public String queryPage(){
        return "ui-cards";
    }

    @RequestMapping(value="/stockHistory")
    public String listStockHistory(HttpSession session, Model model){
        // 获取查询参数
        List<StockInfo> list = (List<StockInfo>) session.getAttribute("list");
        String id = (String) session.getAttribute("id");
        String Name = (String) session.getAttribute("Name");
        // 传到前端去的
        model.addAttribute("list", list);
        model.addAttribute("id",id);
        model.addAttribute("Name", Name);
        return "tables-datatable";
    }

    @RequestMapping(value = "/queryByIDDate")
    public String QueryByIDDate(HttpSession session, @RequestParam("StockID")String StockID, @RequestParam("StartDate")String t1,
                                @RequestParam("EndDate")String t2)
    {
        List<StockInfo> list;
        list = stockService.queryByTime(StockID,t1,t2);
        session.setAttribute("list", list);
        session.setAttribute("id",StockID);
        session.setAttribute("Name",stockIDName.getMap().get(StockID));
        return "success";
    }

    @RequestMapping(value = "/queryByIDNum")
    public String QueryByIDNum(HttpSession session,@RequestParam("StockID")String StockID, @RequestParam("Rows")int limit)
    {
        List<StockInfo> list;
        list = stockService.queryByNum(StockID,limit);//参数
        session.setAttribute("list", list);
        session.setAttribute("id",StockID);
        session.setAttribute("Name",stockIDName.getMap().get(StockID));
        return "success";
    }
    @RequestMapping(value = "/queryByNameDate")
    public String QueryByNameDate(HttpSession session,@RequestParam("StockName")String StockName, @RequestParam("StartDate") String t1,
                                  @RequestParam("EndDate")String t2)
    {
        List<StockInfo> list;
        list = stockService.queryByTime(stockNameID.getMap().get(StockName),t1,t2);
        session.setAttribute("list", list);
        session.setAttribute("id",stockNameID.getMap().get(StockName));
        session.setAttribute("Name",StockName);
        return "success";
    }

    @RequestMapping(value = "/queryByNameNum")
    public String QueryByNameNum(HttpSession session,@RequestParam("StockName")String StockName, @RequestParam("Rows")int limit)
    {
        List<StockInfo> list;
        list = stockService.queryByNum(stockNameID.getMap().get(StockName),limit);//参数
        session.setAttribute("list", list);
        session.setAttribute("Name",StockName);
        session.setAttribute("limit",limit);
        session.setAttribute("id",stockNameID.getMap().get(StockName));
        return "success";
    }

    @RequestMapping("/realtimeStock/{StockID}")
    public String listRealtimeStock(Model model,@PathVariable String StockID) throws IOException {
        String url = "http://qt.gtimg.cn/q="+StockID;
        String str = getRequestFromUrl(url);
        String[] strs = str.split("~");
        model.addAttribute("date",strs[30]);
        model.addAttribute("id",strs[2]);
        model.addAttribute("name",strs[1]);
        model.addAttribute("dangqianjiage",strs[3]);
        model.addAttribute("zuigaojia",strs[33]);
        model.addAttribute("zuidijia",strs[34]);
        model.addAttribute("kaipanjia",strs[5]);
        model.addAttribute("qianshoupan",strs[4]);
        model.addAttribute("zhangdiee",strs[31]);
        model.addAttribute("zhangdiefu",strs[32]);
        model.addAttribute("huanshoulv",strs[38]);
        model.addAttribute("chengjiaoliang",strs[36]);
        model.addAttribute("chengjiaojine",strs[37]);
        model.addAttribute("zongshizhi",strs[45]);
        model.addAttribute("liutongshizhi",strs[44]);
        return "realtimeStock";
    }

    public static String getRequestFromUrl(String url) throws IOException, JSONException {
        URL realUrl = new URL(url); //新建参数为url的URL对象realUrl
        URLConnection conn = realUrl.openConnection();
        //同上
        try (InputStream instream = conn.getInputStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(instream));
            return readAll(rd);
        }
    }
    private static String readAll(BufferedReader rd) throws IOException {
        StringBuilder sb = new StringBuilder(); //新建StringBuilder对象sb
        //每个字节读取rd对象，若读到最后就会返回-1，来判断是否文件读完
        int ss;
        while ((ss = rd.read()) != -1) {
            sb.append((char) ss);
        }
        return sb.toString(); //返回String格式的sb对象
    }

    @RequestMapping("/realtimeStockPicDaily/{StockID}")
    public ModelAndView toPicDaily(@PathVariable("StockID")String StockID) {
        return  new ModelAndView(new RedirectView("http://image.sinajs.cn/newchart/daily/n/"+StockID+".gif"));
    }

    @RequestMapping("/realtimeStockPicWeek/{StockID}")
    public ModelAndView toPicWeek(@PathVariable("StockID")String StockID) {
        return  new ModelAndView(new RedirectView("http://image.sinajs.cn/newchart/weekly/n/"+StockID+".gif"));
    }

    @RequestMapping("/realtimeStockPicMonth/{StockID}")
    public ModelAndView toPicMonth(@PathVariable("StockID")String StockID) {
        return  new ModelAndView(new RedirectView("http://image.sinajs.cn/newchart/monthly/n/"+StockID+".gif"));
    }

    @RequestMapping("/realtimeStockPicMin/{StockID}")
    public ModelAndView toPicMin(@PathVariable("StockID")String StockID) {
        return  new ModelAndView(new RedirectView("http://image.sinajs.cn/newchart/min/n/"+StockID+".gif"));
    }

    @RequestMapping("/stock")
    public String toStockPage() {
        return "ui-cards";
    }
}
