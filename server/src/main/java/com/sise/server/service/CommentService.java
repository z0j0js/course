package com.sise.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sise.server.domain.Comment;
import com.sise.server.domain.CommentExample;
import com.sise.server.dto.CommentDto;
import com.sise.server.dto.PageDto;
import com.sise.server.mapper.CommentMapper;
import com.sise.server.util.CopyUtil;
import com.sise.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentService {

    @Resource
    private CommentMapper commentMapper;

    /**
     * 列表查询
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        CommentExample commentExample = new CommentExample();
        List<Comment> commentList = commentMapper.selectByExample(commentExample);
        PageInfo<Comment> pageInfo = new PageInfo<>(commentList);
        pageDto.setTotal(pageInfo.getTotal());
        List<CommentDto> commentDtoList = CopyUtil.copyList(commentList, CommentDto.class);
        pageDto.setList(commentDtoList);
    }

    /**
     * 评论列表查询，按创建日期倒序与课程
     */
    public List<CommentDto> findComment(String courseid) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andCourseidEqualTo(courseid);
        commentExample.setOrderByClause("time desc");
        List<Comment> commentList = commentMapper.selectByExample(commentExample);
        return CopyUtil.copyList(commentList, CommentDto.class);
    }

    /**
     * 通过回复找到对应的评论 pid 值
     * @param num
     * @return
     */
    public String getComment(int num) {
        PageHelper.startPage(num+1,1);
        CommentExample commentExample = new CommentExample();
        commentExample.setOrderByClause("time desc");
        commentExample.createCriteria().andCourseidLike("00000000");
        List<Comment> commentList = commentMapper.selectByExample(commentExample);
        PageInfo<Comment> pageInfo = new PageInfo<>(commentList);
        String pid = pageInfo.getList().get(0).getId();
        String to = pageInfo.getList().get(0).getName();
        return pid+to;
    }


    /**
     * 保存，id有值时更新，无值时新增
     */
    public void save(CommentDto commentDto) {
        Comment comment = CopyUtil.copy(commentDto, Comment.class);
        if (StringUtils.isEmpty(commentDto.getId())) {
            this.insert(comment);
        } else {
            this.update(comment);
        }
    }

    /**
     * 新增
     */
    private void insert(Comment comment) {
        comment.setId(UuidUtil.getShortUuid());
        commentMapper.insert(comment);
    }

    /**
     * 更新
     */
    private void update(Comment comment) {
        commentMapper.updateByPrimaryKey(comment);
    }

    /**
     * 删除
     */
    public void delete(String id) {
        commentMapper.deleteByPrimaryKey(id);
    }
}
