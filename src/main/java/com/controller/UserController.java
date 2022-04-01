package com.controller;

import com.pojo.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService userService;

    @RequestMapping("/login")
    public String UserLogin() {
        return "pages-login";
    }

    @RequestMapping("/user")
    public String UserPage(HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if(user == null) {
            // 用户未登录直接返回登录页面
            return "pages-login";
        }
        return "user/userPage";
    }

    @RequestMapping("/checkUser")
    @ResponseBody
    public String checkUser(@RequestParam("userid")String userid,@RequestParam("password")String password, HttpSession session) {
        User user = userService.queryUserById(userid);
        if(user != null){
            if(password.equals(user.getPassword())){
//                model.addAttribute("userid",userid);
                System.out.println("登录成功" + userid + password+user.getPassword());
                // 设置session，访问页面之前检查用户是否登录
                session.setAttribute("currentUser", user);
                return "success";
            }else{
                return "fail";
            }
        }
        System.out.println(userid + " " + password);
        return "fail";
    }

    @RequestMapping("/updateCount")
    public String updateCount(@RequestParam("id") String id,@RequestParam("money") String money){
        System.out.println("成功");
        //String id = (String)model.asMap().get("userid");
        Pattern pattern = Pattern.compile("[+-]?\\d+(.\\d+)?");
        if (pattern.matcher(money).matches()){
            double moneyval = Double.parseDouble(money);
            if(userService.updateCountById(id,moneyval)){
                System.out.println("成功");
            }
            else{
                System.out.println("请重新登陆");
            }
        }
        else{
            System.out.println("不合法的金额");
        }
        return "register";
    }
//    @RequestMapping("/register")
//    public String register(@RequestParam("id") String id,@RequestParam("password") String password,@RequestParam("name") String name){
//        User user = new User(id,password,name,0);
//        if(id!=""){
//            if(userService.createUser(user)){
//                System.out.println("创建账户成功");
//            }
//            else{
//                System.out.println("账户已存在");
//            }
//        }
//        else{
//            System.out.println("用户名不能为空");
//        }
//        return "pages-login";
//    }

    // 用户注册页面
    @RequestMapping("/register")
    public String registerUserPage() {
        return "pages-register";
    }

    // 注册一个用户
    @RequestMapping("/register/check")
    @ResponseBody
    public String registerUser(@RequestParam("userid")String userid, @RequestParam("nickname")String nickname, @RequestParam("password")String password) {
        // 在数据库中插入一条用户信息
        User user = new User();
        user.setId(userid);
        user.setName(nickname);
        user.setPassword(password);
        user.setCount(0);
        boolean res = userService.createUser(user);
        if(res){
            return "success";
        }else{
            return "fail";
        }
    }

    @RequestMapping("/logout")
    @ResponseBody
    public String logOut(HttpSession session) {
        session.removeAttribute("currentUser");
        return "success";
    }

    @RequestMapping("/charge")
    public String chargePage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");
        if(user == null) {
            return "/user/login";
        }
        model.addAttribute("username", user.getName());
        java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
        String str = df.format(user.getCount());
        model.addAttribute("money",str);
        return "charge";
    }

    @RequestMapping("/dealCharge")
    @ResponseBody
    public String dealCharge(HttpSession session, @RequestParam("money")String money) {
        User user = (User) session.getAttribute("currentUser");
        if(user == null) {
            return "fail";
        }
        double count = Double.parseDouble(money);
        userService.updateCountById(user.getId(), count);
        user = userService.queryUserById(user.getId());
        session.setAttribute("currentUser", user);
        return "success";
    }
}
