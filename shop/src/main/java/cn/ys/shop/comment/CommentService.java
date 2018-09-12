package cn.ys.shop.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author :yuanshuo
 * @date :2018/3/5
 */
@Service
public class CommentService {
    @Autowired
    CommentDao commentDao;

    public void addComment(Comment comment){

        SimpleDateFormat fmt=new SimpleDateFormat("yyyy/MM/dd HH:mm");
        String date=fmt.format(new Date()).toString();
        comment.setGoodNum(0);
        comment.setCommentDate(date);
        commentDao.save(comment);

        //将被评论者的评论数+1
        Integer id= comment.getParentID();
        Comment c= commentDao.findById(id);
        c.setReply(c.getReply()+1);
        commentDao.save(c);
    }

    public void addZan(int id) {
       Comment c= commentDao.findById(id);
       c.setGoodNum(c.getGoodNum()+1);
        commentDao.save(c);
    }
}
