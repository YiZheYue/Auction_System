package io.renren.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.ExamsDao;
import io.renren.modules.generator.entity.ExamsEntity;
import io.renren.modules.generator.service.ExamsService;


@Service("examsService")
public class ExamsServiceImpl extends ServiceImpl<ExamsDao, ExamsEntity> implements ExamsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ExamsEntity> page = this.page(
                new Query<ExamsEntity>().getPage(params),
                new QueryWrapper<ExamsEntity>()
        );

        return new PageUtils(page);
    }

}