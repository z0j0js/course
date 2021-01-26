package com.sise.business.controller.web;

import com.sise.server.dto.CommentDto;
import com.sise.server.dto.PageDto;
import com.sise.server.dto.ResponseDto;
import com.sise.server.service.CommentService;
import com.sise.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/web/comment")
public class CommentController {

    private static final Logger LOG = LoggerFactory.getLogger(CommentController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private CommentService commentService;

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        commentService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }


    @GetMapping("/find/{courseid}")
    public ResponseDto findComment(@PathVariable String courseid) {
        LOG.info("查找课程所有评论开始：{}", courseid);
        ResponseDto responseDto = new ResponseDto();
        List<CommentDto> commentDtoList = commentService.findComment(courseid);
        responseDto.setContent(commentDtoList);
        LOG.info("查找课程所有评论结束：{}", responseDto);
        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody CommentDto commentDto) {
        // 保存校验
        ValidatorUtil.length(commentDto.getName(), "用户名", 1, 255);
        ValidatorUtil.length(commentDto.getHeadimg(), "头像", 1, 255);
        ValidatorUtil.length(commentDto.getComment(), "评论内容", 1, 255);
        ValidatorUtil.length(commentDto.getTime(), "时间", 1, 255);

        ResponseDto responseDto = new ResponseDto();
        commentService.save(commentDto);
        responseDto.setContent(commentDto);
        return responseDto;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        commentService.delete(id);
        return responseDto;
    }
}
