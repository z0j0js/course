package com.sise.business.controller.web;

import com.sise.server.dto.ReplyDto;
import com.sise.server.dto.PageDto;
import com.sise.server.dto.ResponseDto;
import com.sise.server.service.CommentService;
import com.sise.server.service.ReplyService;
import com.sise.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/web/reply")
public class ReplyController {

    private static final Logger LOG = LoggerFactory.getLogger(ReplyController.class);
    public static final String BUSINESS_NAME = "评论回复";

    @Resource
    private ReplyService replyService;

    @Resource
    private CommentService commentService;

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        replyService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody ReplyDto replyDto) {
        // 保存校验
        ValidatorUtil.length(replyDto.getFrom(), "", 1, 255);
        ValidatorUtil.length(replyDto.getFromheadimg(), "", 1, 255);
        ValidatorUtil.length(replyDto.getComment(), "", 1, 255);
        ValidatorUtil.length(replyDto.getTime(), "", 1, 255);

        int num = Integer.parseInt(replyDto.getPid());
        String pid = commentService.getComment(num).substring(0,8);
        String to = commentService.getComment(num).substring(8);
        replyDto.setPid(pid);
        replyDto.setTo(to);
        ResponseDto responseDto = new ResponseDto();
        replyService.save(replyDto);
        responseDto.setContent(replyDto);
        return responseDto;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        replyService.delete(id);
        return responseDto;
    }
}
