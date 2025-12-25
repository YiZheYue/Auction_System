package io.renren.modules.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.generator.entity.ReviewRequestsEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2024-11-16 12:01:41
 */
public interface ReviewRequestsService extends IService<ReviewRequestsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

