package com.bilgeadam.commentapp.mvc;

import com.bilgeadam.commentapp.dto.request.LikeCreateRequestDto;
import com.bilgeadam.commentapp.repository.entity.Like;
import com.bilgeadam.commentapp.repository.entity.User;
import com.bilgeadam.commentapp.service.LikeService;
import com.bilgeadam.commentapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
@RequestMapping("/likemvc")
public class LikeMvcController {
    private final LikeService likeService;
    private final UserService userService;
    private final ProductMvcController productMvcController;
    @GetMapping("/tolike")
    public ModelAndView tolike(LikeCreateRequestDto dto){

        ModelAndView modelAndView=new ModelAndView();


        modelAndView.addObject("like",likeService.toLike(dto));
        modelAndView.addObject("userId",dto.getUserId());
        modelAndView.setViewName("redirect:/productmvc/getallproducts");



return  modelAndView;




    }



    @GetMapping("/delete")
    public ModelAndView deleteByLike(Long userId, Long productId){
        ModelAndView modelAndView=new ModelAndView();
        User user=userService.findById(userId).get();

        System.out.println(productId);
        user.getLikes().stream().forEach(s->s.getId());
        List<Like> likes=  user.getLikes().stream().filter(s->s.getProduct().getId()==productId).collect(Collectors.toList());
        likeService.deleteById(likes.get(0).getId());

        modelAndView.addObject("userId",userId);
        modelAndView.setViewName("redirect:/productmvc/getallproducts");
        return  modelAndView;

    }
}
