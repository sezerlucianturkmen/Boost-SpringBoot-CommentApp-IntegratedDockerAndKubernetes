package com.bilgeadam.commentapp.mvc;


import com.bilgeadam.commentapp.dto.request.LoginRequesDto;
import com.bilgeadam.commentapp.dto.request.UserCreateRequestDto;
import com.bilgeadam.commentapp.repository.entity.EUserType;
import com.bilgeadam.commentapp.repository.entity.User;
import com.bilgeadam.commentapp.service.ProductService;
import com.bilgeadam.commentapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class LoginMvcController {

  private  final  UserService userService;
  private  final ProductService productService;

  private final ProductMvcController productMvcController;

    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("login");

        return modelAndView;

    }

    @PostMapping("/dologin")
    public ModelAndView doLogin(LoginRequesDto dto){
        ModelAndView modelAndView=new ModelAndView();
        Optional<User> user=userService.login(dto.getEmail(),dto.getPassword());
        boolean isUser=true;
        boolean isProduct=false;
        if (user.isPresent()){

            if (user.get().getUserType().equals(EUserType.ADMIN)){
              return users();



//               return admin(); //postmappingle admin metodunu ?a??r?yoruz

            }else {
                modelAndView.addObject("userId",user.get().getId());
                modelAndView.setViewName("redirect:/productmvc/getallproducts");
//           return     productMvcController.getAllproducts(user.get());
            }

        }else {
            modelAndView.setViewName("redirect:login");
        }

        return  modelAndView;

    }

    @PostMapping("/products")
    public  ModelAndView  postproducts(Boolean isUser,Boolean isProduct){
        ModelAndView modelAndView=new ModelAndView();

        modelAndView.addObject("isproducts",isProduct);
        modelAndView.addObject("isuser",isUser);
        modelAndView.setViewName("redirect:dologin");
        return  modelAndView;
    }
    @GetMapping("/products")
    public  ModelAndView  products(){
        ModelAndView modelAndView=new ModelAndView();

       modelAndView.addObject("products" ,productService.findAll());
        modelAndView.addObject("isproducts",true);
        modelAndView.addObject("isuser",false);
       modelAndView.setViewName("admin");
        return  modelAndView;
    }
    @GetMapping("/users")
    public  ModelAndView  users(){
        ModelAndView modelAndView=new ModelAndView();

        modelAndView.addObject("userlist",userService.findAll());
        modelAndView.addObject("isproducts",false);
        modelAndView.addObject("isuser",true);
        modelAndView.setViewName("admin");
        return  modelAndView;
    }


    @GetMapping("/register")
    public ModelAndView register(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("register");
        modelAndView.addObject("userType", EUserType.values());

        return modelAndView;
    }
    @PostMapping("/register")
    public ModelAndView index(UserCreateRequestDto dto){

        boolean isregister = userService.register(dto);
        ModelAndView model = new ModelAndView();
        if(isregister){
            model.setViewName("redirect:/login");
        }else{
            model.addObject("error",
                    "Kullanici daha once olusturulmustur");
            model.setViewName("user/register");
        }
        return model;
    }

}
