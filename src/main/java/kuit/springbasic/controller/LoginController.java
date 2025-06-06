package kuit.springbasic.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kuit.springbasic.db.UserRepository;
import kuit.springbasic.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/user")
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserRepository userRepository;


    @GetMapping("/login")
    public String showLoginForm(){
        return "/user/login";
    }



    @RequestMapping("/loginFailed")
    public String showLoginFailed(){
         return "user/loginFailed";
    }

//    @RequestMapping("/login")
    public String loginV1(@RequestParam("userId")String userId,
                          @RequestParam("password")String password,
                          HttpServletRequest request)
    {
        User findUser = userRepository.findByUserId(userId);
        User loginUser = new User(userId,password);

        if(findUser!=null && loginUser.isSameUser(findUser)){
            HttpSession session = request.getSession();
            session.setAttribute("user", findUser); //loginUser->findUser
            return "redirect:/";
        }

        return "redirect:/user/loginFailed";
    }

//    @RequestMapping("/login")
    public String loginV2(@RequestParam String userId,
                          @RequestParam String password,
                          HttpServletRequest request)
    {
        User findUser = userRepository.findByUserId(userId);
        User loginUser = new User(userId,password);

        if(findUser!=null && loginUser.isSameUser(findUser)){
            HttpSession session = request.getSession();
            session.setAttribute("user", findUser); //loginUser->findUser
            return "redirect:/";
        }

        return "redirect:/user/loginFailed";
    }

//    @RequestMapping("/login")
    public String loginV3(String userId,
                          String password,
                          HttpServletRequest request)
    {
        User findUser = userRepository.findByUserId(userId);
        User loginUser = new User(userId,password);

        if(findUser!=null && loginUser.isSameUser(findUser)){
            HttpSession session = request.getSession();
            session.setAttribute("user", findUser); //loginUser->findUser
            return "redirect:/";
        }

        return "redirect:/user/loginFailed";
    }


    @RequestMapping("/login")
    public String loginV4(@ModelAttribute User user,
                          HttpServletRequest request)
    {
        User findUser = userRepository.findByUserId(user.getUserId());
        User loginUser = new User(user.getUserId(),user.getPassword());

        if(findUser!=null && loginUser.isSameUser(findUser)){
            HttpSession session = request.getSession();
            session.setAttribute("user", findUser); //loginUser->findUser
            return "redirect:/";
        }

        return "redirect:/user/loginFailed";
    }




    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){

        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/";
    }
}
