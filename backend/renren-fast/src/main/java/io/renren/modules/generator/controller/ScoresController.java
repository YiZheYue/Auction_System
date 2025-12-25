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

import io.renren.modules.generator.entity.ScoresEntity;
import io.renren.modules.generator.service.ScoresService;
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
@RequestMapping("generator/scores")
public class ScoresController {
    @Autowired
    private ScoresService scoresService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:scores:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = scoresService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{scoreId}")
    @RequiresPermissions("generator:scores:info")
    public R info(@PathVariable("scoreId") Integer scoreId){
		ScoresEntity scores = scoresService.getById(scoreId);

        return R.ok().put("scores", scores);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:scores:save")
    public R save(@RequestBody ScoresEntity scores){
		scoresService.save(scores);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:scores:update")
    public R update(@RequestBody ScoresEntity scores){
		scoresService.updateById(scores);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:scores:delete")
    public R delete(@RequestBody Integer[] scoreIds){
		scoresService.removeByIds(Arrays.asList(scoreIds));

        return R.ok();
    }

}
