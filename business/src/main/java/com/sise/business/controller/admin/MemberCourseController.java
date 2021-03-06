package com.sise.business.controller.admin;

import com.sise.server.dto.MemberCourseDto;
import com.sise.server.dto.PageDto;
import com.sise.server.dto.ResponseDto;
import com.sise.server.service.CourseService;
import com.sise.server.service.MemberCourseService;
import com.sise.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/memberCourse")
public class MemberCourseController {

    private static final Logger LOG = LoggerFactory.getLogger(MemberCourseController.class);
    public static final String BUSINESS_NAME = "会员课程报名";

    @Resource
    private MemberCourseService memberCourseService;

    @Resource
    private CourseService courseService;

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        memberCourseService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 总收益查询
     */
    @GetMapping("/getReceipts")
    public ResponseDto getReceipts() {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setContent(memberCourseService.getReceipts());
        return responseDto;
    }

    /**
     * 订单总数查询
     * @return
     */
    @GetMapping("/getTotal")
    public ResponseDto getTotal() {
        ResponseDto responseDto = new ResponseDto();
        int total = memberCourseService.getTotal();
        responseDto.setContent(total);
        return responseDto;
    }

    /**
     * 统计图表
     * @return
     */
    @GetMapping("/statistics")
    public ResponseDto statistics() {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setContent(memberCourseService.statistics());
        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody MemberCourseDto memberCourseDto) {
        // 保存校验
        ValidatorUtil.require(memberCourseDto.getMemberId(), "会员id");
        ValidatorUtil.require(memberCourseDto.getCourseId(), "课程id");
        ValidatorUtil.require(memberCourseDto.getAt(), "报名时间");

        ResponseDto responseDto = new ResponseDto();
        memberCourseService.save(memberCourseDto);
        courseService.updateEnroll(memberCourseDto.getCourseId());
        responseDto.setContent(memberCourseDto);
        return responseDto;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        memberCourseService.delete(id);
        return responseDto;
    }
}
