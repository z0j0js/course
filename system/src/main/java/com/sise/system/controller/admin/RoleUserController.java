package com.sise.system.controller.admin;

import com.sise.server.dto.RoleUserDto;
import com.sise.server.dto.PageDto;
import com.sise.server.dto.ResponseDto;
import com.sise.server.service.RoleUserService;
import com.sise.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/roleUser")
public class RoleUserController {

    private static final Logger LOG = LoggerFactory.getLogger(RoleUserController.class);
    public static final String BUSINESS_NAME = "角色用户关联";

    @Resource
    private RoleUserService roleUserService;

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        roleUserService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody RoleUserDto roleUserDto) {
        // 保存校验
        ValidatorUtil.require(roleUserDto.getRoleId(), "角色");
        ValidatorUtil.require(roleUserDto.getUserId(), "用户");

        ResponseDto responseDto = new ResponseDto();
        roleUserService.save(roleUserDto);
        responseDto.setContent(roleUserDto);
        return responseDto;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        roleUserService.delete(id);
        return responseDto;
    }
}
