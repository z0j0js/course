package com.sise.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sise.server.domain.*;
import com.sise.server.dto.MemberCourseDto;
import com.sise.server.dto.PageDto;
import com.sise.server.mapper.CourseMapper;
import com.sise.server.mapper.MemberCourseMapper;
import com.sise.server.util.CopyUtil;
import com.sise.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class MemberCourseService {

    @Resource
    private MemberCourseMapper memberCourseMapper;

    @Resource
    private CourseMapper courseMapper;

    /**
     * 列表查询
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        MemberCourseExample memberCourseExample = new MemberCourseExample();
        List<MemberCourse> memberCourseList = memberCourseMapper.selectByExample(memberCourseExample);
        PageInfo<MemberCourse> pageInfo = new PageInfo<>(memberCourseList);
        pageDto.setTotal(pageInfo.getTotal());
        List<MemberCourseDto> memberCourseDtoList = CopyUtil.copyList(memberCourseList, MemberCourseDto.class);
        pageDto.setList(memberCourseDtoList);
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    public void save(MemberCourseDto memberCourseDto) {
        MemberCourse memberCourse = CopyUtil.copy(memberCourseDto, MemberCourse.class);
        if (StringUtils.isEmpty(memberCourseDto.getId())) {
            this.insert(memberCourse);
        } else {
            this.update(memberCourse);
        }
    }

    /**
     * 新增
     */
    private void insert(MemberCourse memberCourse) {
        Date now = new Date();
        memberCourse.setId(UuidUtil.getShortUuid());
        memberCourse.setAt(now);
        memberCourseMapper.insert(memberCourse);
    }

    /**
     * 更新
     */
    private void update(MemberCourse memberCourse) {
        memberCourseMapper.updateByPrimaryKey(memberCourse);
    }

    /**
     * 删除
     */
    public void delete(String id) {
        memberCourseMapper.deleteByPrimaryKey(id);
    }

    /**
     * 报名，先判断是否已报名
     * @param memberCourseDto
     */
    public MemberCourseDto enroll(MemberCourseDto memberCourseDto) {
        MemberCourse memberCourseDb = this.select(memberCourseDto.getMemberId(), memberCourseDto.getCourseId());
        if (memberCourseDb == null) {
            MemberCourse memberCourse = CopyUtil.copy(memberCourseDto, MemberCourse.class);
            this.insert(memberCourse);
            // 将数据库信息全部返回，包括id, at
            return CopyUtil.copy(memberCourse, MemberCourseDto.class);
        } else {
            // 如果已经报名，则直接返回已报名的信息
            return CopyUtil.copy(memberCourseDb, MemberCourseDto.class);
        }
    }

    /**
     * 根据memberId和courseId查询记录
     */
    public MemberCourse select(String memberId, String courseId) {
        MemberCourseExample example = new MemberCourseExample();
        example.createCriteria()
                .andCourseIdEqualTo(courseId)
                .andMemberIdEqualTo(memberId);
        List<MemberCourse> memberCourseList = memberCourseMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(memberCourseList)) {
            return null;
        } else {
            return memberCourseList.get(0);
        }
    }

    /**
     * 计算总收益
     * @return
     */
    public int getReceipts() {
        MemberCourseExample memberCourseExample = new MemberCourseExample();
        List<MemberCourse> memberCourseList = memberCourseMapper.selectByExample(memberCourseExample);
        int total = 0;
        for (int i = 0;  i < memberCourseList.size(); i++) {
            CourseExample courseExample = new CourseExample();
            courseExample.createCriteria().andIdEqualTo(memberCourseList.get(i).getCourseId());
            List<Course> courses = courseMapper.selectByExample(courseExample);
            int price = courses.get(0).getPrice().intValue();
            total = total + price;
        }
        return total;
    }

    /**
     * 统计图表
     * @return
     */
    public int[][] statistics() {
        //ArrayList arrayList = new ArrayList();
        int[][] arr = new int[30][2];
        for (int i = 1; i <= 30; i++) {
            int result = memberCourseMapper.statistics(i);
            arr[i-1][0] = i;
            arr[i-1][1] = result;
            //arrayList.add(result);
        }
        return arr;
        //return arrayList;
    }

    /**
     * 获取报名信息
     */
    public MemberCourseDto getEnroll(MemberCourseDto memberCourseDto) {
        MemberCourse memberCourse = this.select(memberCourseDto.getMemberId(), memberCourseDto.getCourseId());
        return CopyUtil.copy(memberCourse, MemberCourseDto.class);
    }

    /**
     * 会员购买课程
     * @param memberCourseDto
     */
    public void enrolls(MemberCourseDto memberCourseDto) {
        MemberCourse memberCourse = CopyUtil.copy(memberCourseDto, MemberCourse.class);
        this.insert(memberCourse);
    }

    public List<MemberCourse> purchased(String memberId) {
        MemberCourseExample example = new MemberCourseExample();
        example.createCriteria().andMemberIdEqualTo(memberId);
        List<MemberCourse> courseList = memberCourseMapper.selectByExample(example);
        return courseList;
    }

    /**
     * 统计总数
     * @return
     */
    public int getTotal() {
        List<MemberCourse> memberCourseList = memberCourseMapper.selectByExample(null);
        int total = memberCourseList.size();
        return total;
    }
}
