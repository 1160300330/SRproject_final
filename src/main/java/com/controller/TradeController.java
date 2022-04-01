package com.controller;

import com.pojo.StockPrice;
import com.pojo.Trade;
import com.pojo.User;
import com.service.StockPriceService;
import com.service.TradeService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user/trade")
public class TradeController {
    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService userService;
    @Autowired
    @Qualifier("TradeServiceImpl")
    private TradeService tradeService;
    @Autowired
    @Qualifier("StockPriceServiceImpl")
    private StockPriceService stockPriceService;
    @RequestMapping("/showstocks")
    //展示所有price数据库中的股票价格
    public String showStock(Model model){
        List<StockPrice> prices = stockPriceService.getStockPriceList();
        System.out.println(prices.get(1).getId());
        model.addAttribute("list", prices);
        return "purchase";
    }

    @RequestMapping("/enter")
    //进入购买界面
    public String enterTrade(Model model){
        //List<StockPrice> prices = stockPriceService.getStockPriceList();
//        System.out.println(prices.get(1).getId());
//        model.addAttribute("list", prices);
        return "trade";
    }
    //购买股票 传递过来股票id和购买数量 自动获取价格
    @RequestMapping("/buy")
    @ResponseBody
    public String buyStock( @RequestParam("stockid")String stockid, @RequestParam("amount")String amountstr, HttpSession session) {
//        String id = (String)session.getAttribute("userid");
        User user = (User) session.getAttribute("currentUser");
        String id = user.getId();
        System.out.println("开户");
        double amount = Double.parseDouble(amountstr);
//        User user = userService.queryUserById(id);
        if(user != null){
            System.out.println("stockid = " + stockid);
            double price = stockPriceService.getStockPriceById(stockid).getPrice();
            double cost = price * amount;
            if(user.getCount() > cost){
                double cnt = user.getCount() - cost;
                userService.updateCountById(id, cnt);
                user = userService.queryUserById(id);
                session.setAttribute("currentUser", user);
                Trade t = tradeService.getUserTradeById(id,stockid);
                if(t==null){
                    System.out.println("开户");
                    tradeService.openTrade(id,stockid,(double) amount);
                }
                else{
                    System.out.println("更新");
                    amount = amount + t.getAmount();
                    tradeService.updateTrade(id,stockid,(double) amount);
                }
                return "success";
            }
            else{
                System.out.println("余额不足");
                return "fail";
            }
        }
        else{
            System.out.println("用户不存在");
            return "fail";
        }
//        return "alluserstocks";
    }

    @RequestMapping("/mystocks")
    public String myStock(HttpSession session, Model model){//显示所有该用户购买的股票
        User user = (User) session.getAttribute("currentUser");
        String id = user.getId();
        List<Trade> trades= tradeService.getUserTradeList(id);
        model.addAttribute("trades",trades);
        return "record";
    }
    @RequestMapping("/back")
    //进入购买界面
    public String backTrade(Model model){
        //List<StockPrice> prices = stockPriceService.getStockPriceList();
//        System.out.println(prices.get(1).getId());
//        model.addAttribute("list", prices);
        return "sell";
    }


    @RequestMapping("/sell")
    @ResponseBody
    public String SellStock(HttpSession session,@RequestParam("stockid")String stockid,@RequestParam("amount")String amountstr){//出售股票
        User user = (User) session.getAttribute("currentUser");
        String id = user.getId();
        double amount = Double.parseDouble(amountstr);
        System.out.println("11111");
//        User user = userService.queryUserById(id);
        System.out.println("11111");
        if(user!=null){
            System.out.println("11111");
                Trade t = tradeService.getUserTradeById(id,stockid);
                if(t!=null){
                    if(t.getAmount() >= amount){
                        double price = stockPriceService.getStockPriceById(stockid).getPrice();
                        double cost = price * amount;
                        double cnt = user.getCount()+cost;
                        System.out.println("price = " + price + " amount = " + amount + " money = " + cnt);
                        userService.updateCountById(id,cnt);
                        user = userService.queryUserById(id);
                        session.setAttribute("currentUser", user);
                        System.out.println("user money = " + user.getCount());
                        System.out.println("出售成功");
                        if(t.getAmount() == amount) {
                            tradeService.deleteTrade(id,stockid);
                        }else{
                            tradeService.updateTrade(id,stockid,t.getAmount()-amount);
                        }
                        return "success";
                    }
                    else{
                        System.out.println("您持有的股票不足");
                        return "fail";
                    }
                }
                else{
                    System.out.println("您未持有该股票");
                    return "fail";
                }
            }
        else{
            System.out.println("用户不存在");
            return "fail";
        }
//        return "user/jump";
    }

//    @Autowired
//    @Qualifier("StockServiceImpl")
//    private StockService tradeService;
}
