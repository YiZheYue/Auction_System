package io.renren.modules.generator.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.generator.entity.StudentsEntity;
import io.renren.modules.generator.service.StudentsService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2024-11-16 12:01:41
 */
@RestController
@RequestMapping("generator/students")
public class StudentsController {
    @Autowired
    private StudentsService studentsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:students:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = studentsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{studentId}")
    @RequiresPermissions("generator:students:info")
    public R info(@PathVariable("studentId") Integer studentId){
		StudentsEntity students = studentsService.getById(studentId);

        return R.ok().put("students", students);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:students:save")
    public R save(@RequestBody StudentsEntity students){
		studentsService.save(students);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:students:update")
    public R update(@RequestBody StudentsEntity students){
		studentsService.updateById(students);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:students:delete")
    public R delete(@RequestBody Integer[] studentIds){
		studentsService.removeByIds(Arrays.asList(studentIds));

        return R.ok();
    }

}
