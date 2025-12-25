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

import io.renren.modules.generator.entity.ReviewRequestsEntity;
import io.renren.modules.generator.service.ReviewRequestsService;
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
@RequestMapping("generator/reviewrequests")
public class ReviewRequestsController {
    @Autowired
    private ReviewRequestsService reviewRequestsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:reviewrequests:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = reviewRequestsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{requestId}")
    @RequiresPermissions("generator:reviewrequests:info")
    public R info(@PathVariable("requestId") Integer requestId){
		ReviewRequestsEntity reviewRequests = reviewRequestsService.getById(requestId);

        return R.ok().put("reviewRequests", reviewRequests);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:reviewrequests:save")
    public R save(@RequestBody ReviewRequestsEntity reviewRequests){
		reviewRequestsService.save(reviewRequests);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:reviewrequests:update")
    public R update(@RequestBody ReviewRequestsEntity reviewRequests){
		reviewRequestsService.updateById(reviewRequests);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:reviewrequests:delete")
    public R delete(@RequestBody Integer[] requestIds){
		reviewRequestsService.removeByIds(Arrays.asList(requestIds));

        return R.ok();
    }

}
