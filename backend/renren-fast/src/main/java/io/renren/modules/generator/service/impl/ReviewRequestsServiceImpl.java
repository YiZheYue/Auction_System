package io.renren.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.ReviewRequestsDao;
import io.renren.modules.generator.entity.ReviewRequestsEntity;
import io.renren.modules.generator.service.ReviewRequestsService;


@Service("reviewRequestsService")
public class ReviewRequestsServiceImpl extends ServiceImpl<ReviewRequestsDao, ReviewRequestsEntity> implements ReviewRequestsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ReviewRequestsEntity> page = this.page(
                new Query<ReviewRequestsEntity>().getPage(params),
                new QueryWrapper<ReviewRequestsEntity>()
        );

        return new PageUtils(page);
    }

}