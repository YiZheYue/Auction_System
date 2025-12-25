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

import io.renren.modules.generator.entity.ExamsEntity;
import io.renren.modules.generator.service.ExamsService;
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
@RequestMapping("generator/exams")
public class ExamsController {
    @Autowired
    private ExamsService examsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:exams:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = examsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{examId}")
    @RequiresPermissions("generator:exams:info")
    public R info(@PathVariable("examId") Integer examId){
		ExamsEntity exams = examsService.getById(examId);

        return R.ok().put("exams", exams);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:exams:save")
    public R save(@RequestBody ExamsEntity exams){
		examsService.save(exams);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:exams:update")
    public R update(@RequestBody ExamsEntity exams){
		examsService.updateById(exams);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:exams:delete")
    public R delete(@RequestBody Integer[] examIds){
		examsService.removeByIds(Arrays.asList(examIds));

        return R.ok();
    }

}
