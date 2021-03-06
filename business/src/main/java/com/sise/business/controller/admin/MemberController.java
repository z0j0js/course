package com.sise.business.controller.admin;

import com.sise.server.dto.PageDto;
import com.sise.server.dto.ResponseDto;
import com.sise.server.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/member")
public class MemberController {

    private static final Logger LOG = LoggerFactory.getLogger(MemberController.class);
    public static final String BUSINESS_NAME = "会员";

    @Resource
    private MemberService memberService;

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        memberService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    @GetMapping("/getTotal")
    public ResponseDto getTotal() {
        ResponseDto responseDto = new ResponseDto();
        int total = memberService.getTotal();
        responseDto.setContent(total);
        return responseDto;
    }
}
