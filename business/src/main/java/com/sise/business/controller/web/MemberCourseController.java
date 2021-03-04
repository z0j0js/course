package com.sise.business.controller.web;

import com.sise.server.domain.MemberCourse;
import com.sise.server.dto.CourseDto;
import com.sise.server.dto.MemberCourseDto;
import com.sise.server.dto.ResponseDto;
import com.sise.server.service.CourseService;
import com.sise.server.service.MemberCourseService;
import com.sise.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController("webMemberCourseController")
@RequestMapping("/web/member-course")
public class MemberCourseController {

    private static final Logger LOG = LoggerFactory.getLogger(MemberCourseController.class);
    public static final String BUSINESS_NAME = "会员课程报名";

    @Resource
    private MemberCourseService memberCourseService;

    @Resource
    private CourseService courseService;

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/enroll")
    public ResponseDto enroll(@RequestBody MemberCourseDto memberCourseDto) {
        // 保存校验
        ValidatorUtil.require(memberCourseDto.getMemberId(), "会员id");
        ValidatorUtil.require(memberCourseDto.getCourseId(), "课程id");

        ResponseDto responseDto = new ResponseDto();
        memberCourseDto = memberCourseService.enroll(memberCourseDto);
        responseDto.setContent(memberCourseDto);
        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/get-enroll")
    public ResponseDto getEnroll(@RequestBody MemberCourseDto memberCourseDto) {
        ResponseDto responseDto = new ResponseDto();
        memberCourseDto = memberCourseService.getEnroll(memberCourseDto);
        responseDto.setContent(memberCourseDto);
        return responseDto;
    }


    /**
     * 已购课程查询
     */
    @PostMapping("/purchased")
    public ResponseDto purchased(@RequestBody MemberCourseDto memberCourseDto) {
        ResponseDto responseDto = new ResponseDto();
        // 用memberid拿到会员购买的所有课程id
        // 遍历课程id返回一个课程列表
        List<MemberCourse> memberCourseList = memberCourseService.purchased(memberCourseDto.getMemberId());
        List<CourseDto> courseDtoList = courseService.purchased(memberCourseList);
        responseDto.setContent(courseDtoList);
        return responseDto;
    }
}
