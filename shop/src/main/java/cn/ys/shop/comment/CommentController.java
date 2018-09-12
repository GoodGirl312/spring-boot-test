package cn.ys.shop.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author :yuanshuo
 * @date :2018/3/5
 */
@Controller
public class CommentController {


    @Autowired
    CommentService commentService;

     @RequestMapping(value = "/addComment",method = RequestMethod.POST)
     public void addComment(Comment comment){
        commentService.addComment(comment);
     }

     @RequestMapping(value = "/toClick",method = RequestMethod.POST)
     public void toClick(int id){
        commentService.addZan(id);
     }

}
