package com.sise.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sise.server.domain.Reply;
import com.sise.server.domain.ReplyExample;
import com.sise.server.dto.PageDto;
import com.sise.server.dto.ReplyDto;
import com.sise.server.mapper.ReplyMapper;
import com.sise.server.util.CopyUtil;
import com.sise.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ReplyService {

    @Resource
    private ReplyMapper replyMapper;

    /**
     * 列表查询
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        ReplyExample replyExample = new ReplyExample();
        List<Reply> replyList = replyMapper.selectByExample(replyExample);
        PageInfo<Reply> pageInfo = new PageInfo<>(replyList);
        pageDto.setTotal(pageInfo.getTotal());
        List<ReplyDto> replyDtoList = CopyUtil.copyList(replyList, ReplyDto.class);
        pageDto.setList(replyDtoList);
    }

    /**
     * 评论回复列表查询，按回复日期正序
     */
    public List<ReplyDto> findReply(String commentid) {
        ReplyExample replyExample = new ReplyExample();
        replyExample.createCriteria().andPidEqualTo(commentid);
        replyExample.setOrderByClause("time");
        List<Reply> replyList = replyMapper.selectByExample(replyExample);
        return CopyUtil.copyList(replyList, ReplyDto.class);
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    public void save(ReplyDto replyDto) {
        Reply reply = CopyUtil.copy(replyDto, Reply.class);
        if (StringUtils.isEmpty(replyDto.getId())) {
            this.insert(reply);
        } else {
            this.update(reply);
        }
    }

    /**
     * 新增
     */
    private void insert(Reply reply) {
        reply.setId(UuidUtil.getShortUuid());
        replyMapper.insert(reply);
    }

    /**
     * 更新
     */
    private void update(Reply reply) {
        replyMapper.updateByPrimaryKey(reply);
    }

    /**
     * 删除
     */
    public void delete(String id) {
        replyMapper.deleteByPrimaryKey(id);
    }
}
